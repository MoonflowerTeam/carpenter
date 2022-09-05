package gg.moonflower.carpenter.core.datagen;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.pollen.api.datagen.provider.PollinatedLanguageProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.data.DataGenerator;

public class CarpenterLanguageProvider extends PollinatedLanguageProvider {
    public CarpenterLanguageProvider(DataGenerator generator, PollinatedModContainer container) {
        super(generator, container, "en_us");
    }

    @Override
    protected void registerTranslations() {
        this.addBlock(CarpenterBlocks.SPRUCE_BOOKSHELF, "Spruce Bookshelf");
        this.addBlock(CarpenterBlocks.BIRCH_BOOKSHELF, "Birch Bookshelf");
        this.addBlock(CarpenterBlocks.JUNGLE_BOOKSHELF, "Jungle Bookshelf");
        this.addBlock(CarpenterBlocks.ACACIA_BOOKSHELF, "Acacia Bookshelf");
        this.addBlock(CarpenterBlocks.DARK_OAK_BOOKSHELF, "Dark Oak Bookshelf");
        this.addBlock(CarpenterBlocks.CRIMSON_BOOKSHELF, "Crimson Bookshelf");
        this.addBlock(CarpenterBlocks.WARPED_BOOKSHELF, "Warped Bookshelf");

        this.addBlock(CarpenterBlocks.OAK_CHEST, "Oak Chest");
        this.addBlock(CarpenterBlocks.DARK_OAK_CHEST, "Dark Oak Chest");
        this.addBlock(CarpenterBlocks.BIRCH_CHEST, "Birch Chest");
        this.addBlock(CarpenterBlocks.SPRUCE_CHEST, "Spruce Chest");
        this.addBlock(CarpenterBlocks.CRIMSON_CHEST, "Crimson Chest");
        this.addBlock(CarpenterBlocks.WARPED_CHEST, "Warped Chest");
        this.addBlock(CarpenterBlocks.ACACIA_CHEST, "Acacia Chest");
        this.addBlock(CarpenterBlocks.JUNGLE_CHEST, "Jungle Chest");

    }
}
