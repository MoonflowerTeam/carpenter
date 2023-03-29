//package gg.moonflower.carpenter.core.datagen;
//
//import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
//import gg.moonflower.carpenter.core.registry.CarpenterBlockTags;
//import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedBlockTagsProvider;
//import gg.moonflower.pollen.api.util.PollinatedModContainer;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.tags.BlockTags;
//
//public class CarpenterBlockTagsProvider extends PollinatedBlockTagsProvider {
//    public CarpenterBlockTagsProvider(DataGenerator dataGenerator, PollinatedModContainer container) {
//        super(dataGenerator, container);
//    }
//
//    @Override
//    protected void addTags() {
//        this.tag(CarpenterBlockTags.BOOKSHELVES)
//                .add(CarpenterBlocks.SPRUCE_BOOKSHELF.get())
//                .add(CarpenterBlocks.BIRCH_BOOKSHELF.get())
//                .add(CarpenterBlocks.JUNGLE_BOOKSHELF.get())
//                .add(CarpenterBlocks.ACACIA_BOOKSHELF.get())
//                .add(CarpenterBlocks.DARK_OAK_BOOKSHELF.get())
//                .add(CarpenterBlocks.CRIMSON_BOOKSHELF.get())
//                .add(CarpenterBlocks.WARPED_BOOKSHELF.get());
//        this.tag(CarpenterBlockTags.TRAPPED_CHESTS)
//                .add(CarpenterBlocks.TRAPPED_OAK_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_SPRUCE_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_BIRCH_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_JUNGLE_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_ACACIA_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_DARK_OAK_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_CRIMSON_CHEST.get())
//                .add(CarpenterBlocks.TRAPPED_WARPED_CHEST.get());
//        this.tag(CarpenterBlockTags.CHESTS)
//                .add(CarpenterBlocks.OAK_CHEST.get())
//                .add(CarpenterBlocks.SPRUCE_CHEST.get())
//                .add(CarpenterBlocks.BIRCH_CHEST.get())
//                .add(CarpenterBlocks.JUNGLE_CHEST.get())
//                .add(CarpenterBlocks.ACACIA_CHEST.get())
//                .add(CarpenterBlocks.DARK_OAK_CHEST.get())
//                .add(CarpenterBlocks.CRIMSON_CHEST.get())
//                .add(CarpenterBlocks.WARPED_CHEST.get())
//                .addTag(CarpenterBlockTags.TRAPPED_CHESTS);
//
//        this.tag(BlockTags.MINEABLE_WITH_AXE)
//                .addTag(CarpenterBlockTags.BOOKSHELVES)
//                .addTag(CarpenterBlockTags.CHESTS);
//    }
//}
