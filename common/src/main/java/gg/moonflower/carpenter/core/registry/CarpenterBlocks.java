package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.CarpenterChestBlockEntity;
import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

    public static final Supplier<CarpenterChestBlock> OAK_CHEST = registerChest("oak_chest");
    public static final Supplier<CarpenterChestBlock> DARK_OAK_CHEST = registerChest("dark_oak_chest");
    public static final Supplier<CarpenterChestBlock> BIRCH_CHEST = registerChest("birch_chest");
    public static final Supplier<CarpenterChestBlock> SPRUCE_CHEST = registerChest("spruce_chest");
    public static final Supplier<CarpenterChestBlock> CRIMSON_CHEST = registerChest("crimson_chest");
    public static final Supplier<CarpenterChestBlock> WARPED_CHEST = registerChest("warped_chest");
    public static final Supplier<CarpenterChestBlock> ACACIA_CHEST = registerChest("acacia_chest");

    public static final Supplier<BlockEntityType<CarpenterChestBlockEntity>> CARPENTER_CHEST_BE = BLOCK_ENTITY_REGISTRY.register("carpenter_chest", () -> BlockEntityType.Builder.of(CarpenterChestBlockEntity::new, Registry.BLOCK.stream().filter(block -> block instanceof CarpenterChestBlock).toArray(CarpenterChestBlock[]::new)).build(null));

    private static Supplier<CarpenterChestBlock> registerChest(String chestType) {
        // register chest type alongside the block
        Supplier<CarpenterChestType> typeSupplier = () -> new CarpenterChestType(
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_base_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_left"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_lid_right"),
                Carpenter.carpenter("block/" + chestType + "/" + chestType + "_knob")
        );
        CarpenterChests.CHEST_TYPE_REGISTRY.register(chestType, typeSupplier);

        return REGISTRY.registerWithItem(chestType, () -> new CarpenterChestBlock(typeSupplier, BlockBehaviour.Properties.copy(Blocks.CHEST), () -> CarpenterBlocks.CARPENTER_CHEST_BE.get()), (block) -> new BlockItem(block, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }

    private static Supplier<Block> registerBookshelf(String wood) {
        return REGISTRY.registerWithItem(wood + "_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(block, Blocks.BOOKSHELF.asItem(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }
}
