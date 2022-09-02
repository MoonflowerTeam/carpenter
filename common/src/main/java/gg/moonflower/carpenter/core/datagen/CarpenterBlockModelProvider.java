package gg.moonflower.carpenter.core.datagen;

import com.google.gson.JsonElement;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedBlockModelGenerator;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CarpenterBlockModelProvider extends PollinatedBlockModelGenerator {
    public CarpenterBlockModelProvider(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput) {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    @Override
    public void run() {
        this.createBookshelf(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), Blocks.SPRUCE_PLANKS);
        this.createBookshelf(CarpenterBlocks.BIRCH_BOOKSHELF.get(), Blocks.BIRCH_PLANKS);
        this.createBookshelf(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), Blocks.JUNGLE_PLANKS);
        this.createBookshelf(CarpenterBlocks.ACACIA_BOOKSHELF.get(), Blocks.ACACIA_PLANKS);
        this.createBookshelf(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), Blocks.DARK_OAK_PLANKS);
        this.createBookshelf(CarpenterBlocks.CRIMSON_BOOKSHELF.get(), Blocks.CRIMSON_PLANKS);
        this.createBookshelf(CarpenterBlocks.WARPED_BOOKSHELF.get(), Blocks.WARPED_PLANKS);
    }

    private void createBookshelf(Block bookshelf, Block planks) {
        TextureMapping textureMapping = TextureMapping.column(TextureMapping.getBlockTexture(bookshelf), TextureMapping.getBlockTexture(planks));
        ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(bookshelf, textureMapping, this.getModelOutput());
        this.getBlockStateOutput().accept(createSimpleBlock(bookshelf, resourceLocation));
    }
}
