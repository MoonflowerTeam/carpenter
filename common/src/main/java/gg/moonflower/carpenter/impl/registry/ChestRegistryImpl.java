package gg.moonflower.carpenter.impl.registry;

import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.api.v1.registry.ChestRegistry;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.CarpenterTrappedChestBlock;
import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
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

    private final PollinatedBlockRegistry blockRegistry;
    private final PollinatedRegistry<CarpenterChestType> chestTypeRegistry;
    private final Set<String> registeredTypes;

    public ChestRegistryImpl(PollinatedBlockRegistry blockRegistry, PollinatedRegistry<CarpenterChestType> chestTypeRegistry) {
        this.blockRegistry = blockRegistry;
        this.chestTypeRegistry = chestTypeRegistry;
        this.registeredTypes = new HashSet<>();
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
        ResourceLocation id = new ResourceLocation(this.chestTypeRegistry.getModId(), chestType);
        return this.registeredTypes.contains(chestType) ? () -> this.chestTypeRegistry.get(id) : this.registerChestType(chestType);
    }

    @Override
    public Supplier<CarpenterChestType> registerChestType(String chestType) {
        this.registeredTypes.add(chestType);
        return this.chestTypeRegistry.register(chestType, () -> new CarpenterChestType(
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_knob")
        ));
    }
}
