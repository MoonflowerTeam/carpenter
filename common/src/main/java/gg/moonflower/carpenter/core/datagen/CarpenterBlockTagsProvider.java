package gg.moonflower.carpenter.core.datagen;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterTags;
import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedBlockTagsProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class CarpenterBlockTagsProvider extends PollinatedBlockTagsProvider {
    public CarpenterBlockTagsProvider(DataGenerator dataGenerator, PollinatedModContainer container) {
        super(dataGenerator, container);
    }

    @Override
    protected void addTags() {
        this.tag(CarpenterTags.BOOKSHELVES)
                .add(CarpenterBlocks.SPRUCE_BOOKSHELF.get())
                .add(CarpenterBlocks.BIRCH_BOOKSHELF.get())
                .add(CarpenterBlocks.JUNGLE_BOOKSHELF.get())
                .add(CarpenterBlocks.ACACIA_BOOKSHELF.get())
                .add(CarpenterBlocks.DARK_OAK_BOOKSHELF.get())
                .add(CarpenterBlocks.CRIMSON_BOOKSHELF.get())
                .add(CarpenterBlocks.WARPED_BOOKSHELF.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(CarpenterTags.BOOKSHELVES);
    }
}
