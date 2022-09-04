package gg.moonflower.carpenter.core.datagen;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.pollen.api.datagen.provider.PollinatedRecipeProvider;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class CarpenterRecipeProvider extends PollinatedRecipeProvider {
    public CarpenterRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        createBookshelf(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), Blocks.SPRUCE_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.BIRCH_BOOKSHELF.get(), Blocks.BIRCH_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), Blocks.JUNGLE_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.ACACIA_BOOKSHELF.get(), Blocks.ACACIA_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), Blocks.DARK_OAK_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.CRIMSON_BOOKSHELF.get(), Blocks.CRIMSON_PLANKS).save(consumer);
        createBookshelf(CarpenterBlocks.WARPED_BOOKSHELF.get(), Blocks.WARPED_PLANKS).save(consumer);


        createChest(CarpenterBlocks.OAK_CHEST.get(), Blocks.OAK_PLANKS).save(consumer);
        createChest(CarpenterBlocks.DARK_OAK_CHEST.get(), Blocks.DARK_OAK_PLANKS).save(consumer);
        createChest(CarpenterBlocks.BIRCH_CHEST.get(), Blocks.BIRCH_PLANKS).save(consumer);
        createChest(CarpenterBlocks.SPRUCE_CHEST.get(), Blocks.SPRUCE_PLANKS).save(consumer);
        createChest(CarpenterBlocks.CRIMSON_CHEST.get(), Blocks.CRIMSON_PLANKS).save(consumer);
        createChest(CarpenterBlocks.WARPED_CHEST.get(), Blocks.WARPED_PLANKS).save(consumer);
        createChest(CarpenterBlocks.ACACIA_CHEST.get(), Blocks.ACACIA_PLANKS).save(consumer);
    }

    private static ShapedRecipeBuilder createBookshelf(Block bookshelf, Block planks) {
        return ShapedRecipeBuilder.shaped(bookshelf)
                .group("carpenter_bookshelf")
                .define('#', planks)
                .define('X', Items.BOOK)
                .pattern("###")
                .pattern("XXX")
                .pattern("###")
                .unlockedBy("has_book", has(Items.BOOK));
    }

    private static ShapedRecipeBuilder createChest(Block chest, Block planks) {
        return ShapedRecipeBuilder.shaped(chest)
                .group("carpenter_chest")
                .define('#', planks)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_item", has(planks));
    }
}
