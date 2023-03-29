//package gg.moonflower.carpenter.core.datagen;
//
//import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
//import gg.moonflower.carpenter.core.registry.CarpenterRecipes;
//import gg.moonflower.pollen.api.datagen.provider.PollinatedRecipeProvider;
//import net.minecraft.advancements.CriterionTriggerInstance;
//import net.minecraft.advancements.critereon.EntityPredicate;
//import net.minecraft.advancements.critereon.InventoryChangeTrigger;
//import net.minecraft.advancements.critereon.ItemPredicate;
//import net.minecraft.advancements.critereon.MinMaxBounds;
//import net.minecraft.core.Registry;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.data.recipes.ShapedRecipeBuilder;
//import net.minecraft.data.recipes.ShapelessRecipeBuilder;
//import net.minecraft.data.recipes.SpecialRecipeBuilder;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.ItemLike;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//
//import java.util.function.Consumer;
//
//public class CarpenterRecipeProvider extends PollinatedRecipeProvider {
//    public CarpenterRecipeProvider(DataGenerator generator) {
//        super(generator);
//    }
//
//    @Override
//    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
//        createBookshelf(Blocks.BOOKSHELF, Blocks.OAK_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), Blocks.SPRUCE_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.BIRCH_BOOKSHELF.get(), Blocks.BIRCH_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), Blocks.JUNGLE_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.ACACIA_BOOKSHELF.get(), Blocks.ACACIA_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), Blocks.DARK_OAK_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.CRIMSON_BOOKSHELF.get(), Blocks.CRIMSON_PLANKS, consumer);
//        createBookshelf(CarpenterBlocks.WARPED_BOOKSHELF.get(), Blocks.WARPED_PLANKS, consumer);
//
//        createChest(CarpenterBlocks.OAK_CHEST.get(), Blocks.OAK_PLANKS, consumer);
//        createChest(CarpenterBlocks.DARK_OAK_CHEST.get(), Blocks.DARK_OAK_PLANKS, consumer);
//        createChest(CarpenterBlocks.BIRCH_CHEST.get(), Blocks.BIRCH_PLANKS, consumer);
//        createChest(CarpenterBlocks.SPRUCE_CHEST.get(), Blocks.SPRUCE_PLANKS, consumer);
//        createChest(CarpenterBlocks.CRIMSON_CHEST.get(), Blocks.CRIMSON_PLANKS, consumer);
//        createChest(CarpenterBlocks.WARPED_CHEST.get(), Blocks.WARPED_PLANKS, consumer);
//        createChest(CarpenterBlocks.ACACIA_CHEST.get(), Blocks.ACACIA_PLANKS, consumer);
//        createChest(CarpenterBlocks.JUNGLE_CHEST.get(), Blocks.JUNGLE_PLANKS, consumer);
//
//        createTrappedChest(CarpenterBlocks.TRAPPED_OAK_CHEST.get(), CarpenterBlocks.OAK_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_DARK_OAK_CHEST.get(), CarpenterBlocks.DARK_OAK_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_BIRCH_CHEST.get(), CarpenterBlocks.BIRCH_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_SPRUCE_CHEST.get(), CarpenterBlocks.SPRUCE_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_CRIMSON_CHEST.get(), CarpenterBlocks.CRIMSON_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_WARPED_CHEST.get(), CarpenterBlocks.WARPED_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_ACACIA_CHEST.get(), CarpenterBlocks.ACACIA_CHEST.get(), consumer);
//        createTrappedChest(CarpenterBlocks.TRAPPED_JUNGLE_CHEST.get(), CarpenterBlocks.JUNGLE_CHEST.get(), consumer);
//
//        SpecialRecipeBuilder.special(CarpenterRecipes.LEGACY_CHEST.get())
//                .save(consumer, "chest");
//    }
//
//    private static void createBookshelf(Block bookshelf, Block planks, Consumer<FinishedRecipe> saveConsumer) {
//        ShapedRecipeBuilder.shaped(bookshelf)
//                .group("bookshelves")
//                .define('#', planks)
//                .define('X', Items.BOOK)
//                .pattern("###")
//                .pattern("XXX")
//                .pattern("###")
//                .unlockedBy("has_book", has(Items.BOOK))
//                .save(saveConsumer);
//    }
//
//    private static void createChest(Block chest, Block planks, Consumer<FinishedRecipe> saveConsumer) {
//        ShapedRecipeBuilder.shaped(chest)
//                .group("chests")
//                .define('#', planks)
//                .pattern("###")
//                .pattern("# #")
//                .pattern("###")
//                .unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0]))
//                .save(saveConsumer);
//    }
//
//    private static void createTrappedChest(Block chest, Block from, Consumer<FinishedRecipe> saveConsumer) {
//        ShapelessRecipeBuilder.shapeless(chest)
//                .group("trapped_chests")
//                .requires(from)
//                .requires(Blocks.TRIPWIRE_HOOK)
//                .unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK))
//                .save(saveConsumer);
//    }
//}
