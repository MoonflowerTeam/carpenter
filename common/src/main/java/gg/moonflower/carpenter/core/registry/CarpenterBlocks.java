package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.common.block.CarpenterBookshelfBlock;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.entity.CarpenterChestBlockEntity;
import gg.moonflower.carpenter.common.block.CarpenterTrappedChestBlock;
import gg.moonflower.carpenter.common.block.entity.CarpenterTrappedChestBlockEntity;
import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class CarpenterBlocks {
    public static final PollinatedBlockRegistry REGISTRY = PollinatedRegistry.createBlock(CarpenterItems.REGISTRY);
    public static final PollinatedRegistry<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY = PollinatedRegistry.create(Registry.BLOCK_ENTITY_TYPE, Carpenter.MOD_ID);

    // In reverse order for tab ordering
    public static final Supplier<Block> WARPED_BOOKSHELF = registerBookshelf("warped");
    public static final Supplier<Block> CRIMSON_BOOKSHELF = registerBookshelf("crimson");
    public static final Supplier<Block> DARK_OAK_BOOKSHELF = registerBookshelf("dark_oak");
    public static final Supplier<Block> ACACIA_BOOKSHELF = registerBookshelf("acacia");
    public static final Supplier<Block> JUNGLE_BOOKSHELF = registerBookshelf("jungle");
    public static final Supplier<Block> BIRCH_BOOKSHELF = registerBookshelf("birch");
    public static final Supplier<Block> SPRUCE_BOOKSHELF = registerBookshelf("spruce");

    public static final Supplier<Block> WARPED_CHEST = registerChest("warped_chest");
    public static final Supplier<Block> TRAPPED_WARPED_CHEST = registerTrappedChest("warped_chest");
    public static final Supplier<Block> CRIMSON_CHEST = registerChest("crimson_chest");
    public static final Supplier<Block> TRAPPED_CRIMSON_CHEST = registerTrappedChest("crimson_chest");
    public static final Supplier<Block> DARK_OAK_CHEST = registerChest("dark_oak_chest");
    public static final Supplier<Block> TRAPPED_DARK_OAK_CHEST = registerTrappedChest("dark_oak_chest");
    public static final Supplier<Block> ACACIA_CHEST = registerChest("acacia_chest");
    public static final Supplier<Block> TRAPPED_ACACIA_CHEST = registerTrappedChest("acacia_chest");
    public static final Supplier<Block> JUNGLE_CHEST = registerChest("jungle_chest");
    public static final Supplier<Block> TRAPPED_JUNGLE_CHEST = registerTrappedChest("jungle_chest");
    public static final Supplier<Block> BIRCH_CHEST = registerChest("birch_chest");
    public static final Supplier<Block> TRAPPED_BIRCH_CHEST = registerTrappedChest("birch_chest");
    public static final Supplier<Block> SPRUCE_CHEST = registerChest("spruce_chest");
    public static final Supplier<Block> TRAPPED_SPRUCE_CHEST = registerTrappedChest("spruce_chest");
    public static final Supplier<Block> OAK_CHEST = registerChest("oak_chest");
    public static final Supplier<Block> TRAPPED_OAK_CHEST = registerTrappedChest("oak_chest");

    public static final Supplier<BlockEntityType<CarpenterChestBlockEntity>> CARPENTER_CHEST_BE = BLOCK_ENTITY_REGISTRY.register("carpenter_chest", () -> BlockEntityType.Builder.of(CarpenterChestBlockEntity::new, Registry.BLOCK.stream().filter(block -> block instanceof CarpenterChestBlock).toArray(CarpenterChestBlock[]::new)).build(null));
    public static final Supplier<BlockEntityType<CarpenterTrappedChestBlockEntity>> CARPENTER_TRAPPED_CHEST_BE = BLOCK_ENTITY_REGISTRY.register("carpenter_trapped_chest", () -> BlockEntityType.Builder.of(CarpenterTrappedChestBlockEntity::new, Registry.BLOCK.stream().filter(block -> block instanceof CarpenterChestBlock).toArray(CarpenterChestBlock[]::new)).build(null));

    private static Supplier<Block> registerChest(String chestType) {
        // register chest type alongside the block
        Supplier<CarpenterChestType> chestTypeSupplier = buildChestType(chestType);

        return REGISTRY.registerWithItem(chestType, () -> new CarpenterChestBlock(chestTypeSupplier, BlockBehaviour.Properties.copy(Blocks.CHEST), () -> CarpenterBlocks.CARPENTER_CHEST_BE.get()), (block) -> new TabInsertBlockItem(block, Items.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    private static Supplier<Block> registerTrappedChest(String chestType) {
        // register chest type alongside the block
        Supplier<CarpenterChestType> chestTypeSupplier = () -> CarpenterChests.REGISTRY.get(Carpenter.carpenter(chestType));


        return REGISTRY.registerWithItem("trapped_" + chestType, () -> new CarpenterTrappedChestBlock(chestTypeSupplier, BlockBehaviour.Properties.copy(Blocks.TRAPPED_CHEST), () -> CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE.get()), (block) -> new TabInsertBlockItem(block, Items.TRAPPED_CHEST, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    }

    private static Supplier<CarpenterChestType> buildChestType(String chestType) {
        return CarpenterChests.REGISTRY.register(chestType, () -> new CarpenterChestType(
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_knob")
        ));
    }

    private static Supplier<Block> registerBookshelf(String wood) {
        return REGISTRY.registerWithItem(wood + "_bookshelf", () -> new CarpenterBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(block, Items.BOOKSHELF, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }
}
