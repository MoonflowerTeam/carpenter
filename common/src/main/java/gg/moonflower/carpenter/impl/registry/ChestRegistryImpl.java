package gg.moonflower.carpenter.impl.registry;

import dev.architectury.registry.registries.DeferredRegister;
import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.api.v1.registry.ChestRegistry;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.CarpenterTrappedChestBlock;
import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.registry.wrapper.v1.PollinatedBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Ocelot
 */
@ApiStatus.Internal
public class ChestRegistryImpl implements ChestRegistry {

    private final DeferredRegister<Item> itemRegistry;
    private final PollinatedBlockRegistry blockRegistry;
    private final DeferredRegister<CarpenterChestType> chestTypeRegistry;
    private final Set<String> registeredTypes;
    private final String modId;

    public ChestRegistryImpl(String modId) {
        this.itemRegistry = DeferredRegister.create(modId, Registry.ITEM_REGISTRY);
        this.blockRegistry = PollinatedBlockRegistry.create(this.itemRegistry); // TODO: pollen block wrapper registry
        this.chestTypeRegistry = DeferredRegister.create(modId, CarpenterChests.REGISTRY_KEY);
        this.registeredTypes = new HashSet<>();
        this.modId = modId;
    }

    public ChestRegistryImpl(DeferredRegister<Item> itemRegistry, PollinatedBlockRegistry blockRegistry, DeferredRegister<CarpenterChestType> chestTypeRegistry) {
        this.itemRegistry = itemRegistry;
        this.blockRegistry = blockRegistry;
        this.chestTypeRegistry = chestTypeRegistry;
        this.registeredTypes = new HashSet<>();
        this.modId = this.chestTypeRegistry.getRegistries().getModId();
    }

    @Override
    public void register() {
        this.blockRegistry.register();
        this.itemRegistry.register();
        this.chestTypeRegistry.register();
    }

    @Override
    public Supplier<Block> registerChest(String name, Supplier<CarpenterChestType> chestType) {
        return this.blockRegistry.registerWithItem(name, () -> new CarpenterChestBlock(chestType, BlockBehaviour.Properties.copy(Blocks.CHEST)), block -> new TabInsertBlockItem(block, Items.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    @Override
    public Supplier<Block> registerTrappedChest(String name, Supplier<CarpenterChestType> chestType) {
        return this.blockRegistry.registerWithItem("trapped_" + name, () -> new CarpenterTrappedChestBlock(chestType, BlockBehaviour.Properties.copy(Blocks.TRAPPED_CHEST)), block -> new TabInsertBlockItem(block, Items.TRAPPED_CHEST, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    }

    @Override
    public Supplier<CarpenterChestType> getOrRegisterChestType(String chestType) {
        ResourceLocation id = new ResourceLocation(this.modId, chestType);
        return this.registeredTypes.contains(chestType) ? () -> this.chestTypeRegistry.getRegistrar().get(id) : this.registerChestType(chestType);
    }

    @Override
    public Supplier<CarpenterChestType> registerChestType(String chestType) {
        this.registeredTypes.add(chestType);
        return this.chestTypeRegistry.register(chestType, () -> new CarpenterChestType(
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_base"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_base_left"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_base_right"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_lid"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_lid_left"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_lid_right"),
                new ResourceLocation(this.modId, "block/" + chestType + "/" + chestType + "_knob")
        ));
    }
}
