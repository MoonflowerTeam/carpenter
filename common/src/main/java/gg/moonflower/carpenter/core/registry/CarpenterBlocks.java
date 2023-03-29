package gg.moonflower.carpenter.core.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gg.moonflower.carpenter.api.v1.registry.ChestRegistry;
import gg.moonflower.carpenter.common.block.CarpenterBookshelfBlock;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.entity.CarpenterChestBlockEntity;
import gg.moonflower.carpenter.common.block.entity.CarpenterTrappedChestBlockEntity;
import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.carpenter.impl.registry.ChestRegistryImpl;
import gg.moonflower.pollen.api.registry.wrapper.v1.PollinatedBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CarpenterBlocks {

    public static final PollinatedBlockRegistry REGISTRY = PollinatedBlockRegistry.create(CarpenterItems.REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY = DeferredRegister.create(Carpenter.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    public static final ChestRegistry CHEST_REGISTRY = new ChestRegistryImpl(CarpenterItems.REGISTRY, CarpenterBlocks.REGISTRY, CarpenterChests.REGISTRY);

    public static final RegistrySupplier<BlockEntityType<CarpenterChestBlockEntity>> CARPENTER_CHEST_BE = BLOCK_ENTITY_REGISTRY.register("carpenter_chest", () -> createBlockEntity(CarpenterChestBlockEntity::new));
    public static final RegistrySupplier<BlockEntityType<CarpenterTrappedChestBlockEntity>> CARPENTER_TRAPPED_CHEST_BE = BLOCK_ENTITY_REGISTRY.register("carpenter_trapped_chest", () -> createBlockEntity(CarpenterTrappedChestBlockEntity::new));

    public static final Supplier<Block> WARPED_CHEST = CHEST_REGISTRY.registerChest("warped_chest");
    public static final Supplier<Block> TRAPPED_WARPED_CHEST = CHEST_REGISTRY.registerTrappedChest("warped_chest");
    public static final Supplier<Block> CRIMSON_CHEST = CHEST_REGISTRY.registerChest("crimson_chest");
    public static final Supplier<Block> TRAPPED_CRIMSON_CHEST = CHEST_REGISTRY.registerTrappedChest("crimson_chest");
    public static final Supplier<Block> DARK_OAK_CHEST = CHEST_REGISTRY.registerChest("dark_oak_chest");
    public static final Supplier<Block> TRAPPED_DARK_OAK_CHEST = CHEST_REGISTRY.registerTrappedChest("dark_oak_chest");
    public static final Supplier<Block> ACACIA_CHEST = CHEST_REGISTRY.registerChest("acacia_chest");
    public static final Supplier<Block> TRAPPED_ACACIA_CHEST = CHEST_REGISTRY.registerTrappedChest("acacia_chest");
    public static final Supplier<Block> JUNGLE_CHEST = CHEST_REGISTRY.registerChest("jungle_chest");
    public static final Supplier<Block> TRAPPED_JUNGLE_CHEST = CHEST_REGISTRY.registerTrappedChest("jungle_chest");
    public static final Supplier<Block> BIRCH_CHEST = CHEST_REGISTRY.registerChest("birch_chest");
    public static final Supplier<Block> TRAPPED_BIRCH_CHEST = CHEST_REGISTRY.registerTrappedChest("birch_chest");
    public static final Supplier<Block> SPRUCE_CHEST = CHEST_REGISTRY.registerChest("spruce_chest");
    public static final Supplier<Block> TRAPPED_SPRUCE_CHEST = CHEST_REGISTRY.registerTrappedChest("spruce_chest");
    public static final Supplier<Block> OAK_CHEST = CHEST_REGISTRY.registerChest("oak_chest");
    public static final Supplier<Block> TRAPPED_OAK_CHEST = CHEST_REGISTRY.registerTrappedChest("oak_chest");

    // In reverse order for tab ordering
    public static final RegistrySupplier<Block> WARPED_BOOKSHELF = registerBookshelf("warped");
    public static final RegistrySupplier<Block> CRIMSON_BOOKSHELF = registerBookshelf("crimson");
    public static final RegistrySupplier<Block> DARK_OAK_BOOKSHELF = registerBookshelf("dark_oak");
    public static final RegistrySupplier<Block> ACACIA_BOOKSHELF = registerBookshelf("acacia");
    public static final RegistrySupplier<Block> JUNGLE_BOOKSHELF = registerBookshelf("jungle");
    public static final RegistrySupplier<Block> BIRCH_BOOKSHELF = registerBookshelf("birch");
    public static final RegistrySupplier<Block> SPRUCE_BOOKSHELF = registerBookshelf("spruce");

    private static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(BlockEntityType.BlockEntitySupplier<T> factory) {
        Supplier<Set<Block>> validBlocks = Suppliers.memoize(() -> Registry.BLOCK.stream().filter(block -> block instanceof CarpenterChestBlock).collect(Collectors.toSet()));
        return new BlockEntityType<>(factory, Collections.emptySet(), null) {
            @Override
            public boolean isValid(BlockState state) {
                return validBlocks.get().contains(state.getBlock());
            }
        };
    }

    private static RegistrySupplier<Block> registerBookshelf(String wood) {
        return REGISTRY.registerWithItem(wood + "_bookshelf", () -> new CarpenterBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(block, Items.BOOKSHELF, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }






}
