//package gg.moonflower.carpenter.core.datagen;
//
//import gg.moonflower.carpenter.core.registry.CarpenterBlockTags;
//import gg.moonflower.carpenter.core.registry.CarpenterItemTags;
//import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedItemTagsProvider;
//import gg.moonflower.pollen.api.util.PollinatedModContainer;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.tags.BlockTagsProvider;
//
//public class CarpenterItemTagsProvider extends PollinatedItemTagsProvider {
//    public CarpenterItemTagsProvider(DataGenerator dataGenerator, PollinatedModContainer container, BlockTagsProvider blockTagsProvider) {
//        super(dataGenerator, container, blockTagsProvider);
//    }
//
//    @Override
//    protected void addTags() {
//        this.copy(CarpenterBlockTags.BOOKSHELVES, CarpenterItemTags.BOOKSHELVES);
//        this.copy(CarpenterBlockTags.TRAPPED_CHESTS, CarpenterItemTags.TRAPPED_CHESTS);
//        this.copy(CarpenterBlockTags.CHESTS, CarpenterItemTags.CHESTS);
//    }
//}
