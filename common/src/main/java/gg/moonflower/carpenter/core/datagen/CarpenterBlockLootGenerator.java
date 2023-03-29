//package gg.moonflower.carpenter.core.datagen;
//
//import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
//import gg.moonflower.pollen.api.datagen.provider.loot_table.PollinatedBlockLootGenerator;
//import gg.moonflower.pollen.api.util.PollinatedModContainer;
//import net.minecraft.advancements.critereon.EnchantmentPredicate;
//import net.minecraft.advancements.critereon.ItemPredicate;
//import net.minecraft.advancements.critereon.MinMaxBounds;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.storage.loot.entries.LootItem;
//import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
//import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//import net.minecraft.world.level.storage.loot.predicates.MatchTool;
//import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
//
//public class CarpenterBlockLootGenerator extends PollinatedBlockLootGenerator {
//    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(
//            ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))
//    );
//
//    public CarpenterBlockLootGenerator(PollinatedModContainer container) {
//        super(container);
//    }
//
//    @Override
//    protected void run() {
//        this.bookshelf(CarpenterBlocks.SPRUCE_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.BIRCH_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.JUNGLE_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.ACACIA_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.DARK_OAK_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.CRIMSON_BOOKSHELF.get());
//        this.bookshelf(CarpenterBlocks.WARPED_BOOKSHELF.get());
//
//        this.dropSelf(CarpenterBlocks.OAK_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_OAK_CHEST.get());
//        this.dropSelf(CarpenterBlocks.SPRUCE_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_SPRUCE_CHEST.get());
//        this.dropSelf(CarpenterBlocks.BIRCH_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_BIRCH_CHEST.get());
//        this.dropSelf(CarpenterBlocks.JUNGLE_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_JUNGLE_CHEST.get());
//        this.dropSelf(CarpenterBlocks.ACACIA_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_ACACIA_CHEST.get());
//        this.dropSelf(CarpenterBlocks.DARK_OAK_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_DARK_OAK_CHEST.get());
//        this.dropSelf(CarpenterBlocks.CRIMSON_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_CRIMSON_CHEST.get());
//        this.dropSelf(CarpenterBlocks.WARPED_CHEST.get());
//        this.dropSelf(CarpenterBlocks.TRAPPED_WARPED_CHEST.get());
//    }
//
//    private void bookshelf(Block bookshelf) {
//        this.add(bookshelf, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))));
//    }
//}
