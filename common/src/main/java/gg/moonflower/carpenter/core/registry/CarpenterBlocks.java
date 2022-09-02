package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.common.item.TabInsertBlockItem;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class CarpenterBlocks {
    public static final PollinatedBlockRegistry REGISTRY = PollinatedRegistry.createBlock(CarpenterItems.REGISTRY);

    // In reverse order for tab ordering
    public static final Supplier<Block> WARPED_BOOKSHELF = registerBookshelf("warped");
    public static final Supplier<Block> CRIMSON_BOOKSHELF = registerBookshelf("crimson");
    public static final Supplier<Block> DARK_OAK_BOOKSHELF = registerBookshelf("dark_oak");
    public static final Supplier<Block> ACACIA_BOOKSHELF = registerBookshelf("acacia");
    public static final Supplier<Block> JUNGLE_BOOKSHELF = registerBookshelf("jungle");
    public static final Supplier<Block> BIRCH_BOOKSHELF = registerBookshelf("birch");
    public static final Supplier<Block> SPRUCE_BOOKSHELF = registerBookshelf("spruce");

    private static Supplier<Block> registerBookshelf(String wood) {
        return REGISTRY.registerWithItem(wood + "_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(block, Blocks.BOOKSHELF.asItem(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }
}
