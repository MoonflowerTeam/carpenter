package gg.moonflower.carpenter.core;

import dev.architectury.registry.fuel.FuelRegistry;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterRecipes;
import gg.moonflower.pollen.api.config.v1.ConfigManager;
import gg.moonflower.pollen.api.config.v1.PollinatedConfigType;
import net.minecraft.resources.ResourceLocation;

public class Carpenter {
    public static final String MOD_ID = "carpenter";

    public static final CarpenterConfig CONFIG = ConfigManager.register(Carpenter.MOD_ID, PollinatedConfigType.SERVER, CarpenterConfig::new);

    public static ResourceLocation carpenter(String path) {
        return new ResourceLocation(Carpenter.MOD_ID, path);
    }

    public static void init() {
        CarpenterBlocks.CHEST_REGISTRY.register();
        CarpenterBlocks.BLOCK_ENTITY_REGISTRY.register();
        CarpenterRecipes.REGISTRY.register();
    }

    public static void postInit() {
        FuelRegistry.register(300, CarpenterBlocks.OAK_CHEST.get(),
                CarpenterBlocks.SPRUCE_CHEST.get(),
                CarpenterBlocks.BIRCH_CHEST.get(),
                CarpenterBlocks.JUNGLE_CHEST.get(),
                CarpenterBlocks.ACACIA_CHEST.get(),
                CarpenterBlocks.DARK_OAK_CHEST.get(),
                CarpenterBlocks.TRAPPED_OAK_CHEST.get(),
                CarpenterBlocks.TRAPPED_SPRUCE_CHEST.get(),
                CarpenterBlocks.TRAPPED_BIRCH_CHEST.get(),
                CarpenterBlocks.TRAPPED_JUNGLE_CHEST.get(),
                CarpenterBlocks.TRAPPED_ACACIA_CHEST.get(),
                CarpenterBlocks.TRAPPED_DARK_OAK_CHEST.get(),
                CarpenterBlocks.SPRUCE_BOOKSHELF.get(),
                CarpenterBlocks.BIRCH_BOOKSHELF.get(),
                CarpenterBlocks.JUNGLE_BOOKSHELF.get(),
                CarpenterBlocks.ACACIA_BOOKSHELF.get(),
                CarpenterBlocks.DARK_OAK_BOOKSHELF.get());

        // TODO: pollen flammability
//        FlammabilityRegistry.register(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), 30, 20);
//        FlammabilityRegistry.register(CarpenterBlocks.BIRCH_BOOKSHELF.get(), 30, 20);
//        FlammabilityRegistry.register(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), 30, 20);
//        FlammabilityRegistry.register(CarpenterBlocks.ACACIA_BOOKSHELF.get(), 30, 20);
//        FlammabilityRegistry.register(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), 30, 20);
    }

    // TODO: pollen data generation
//    public static void onDataInit(Platform.DataSetupContext ctx) {
//        DataGenerator generator = ctx.getGenerator();
//        PollinatedModContainer container = ctx.getMod();
//
//        PollinatedModelProvider modelProvider = new PollinatedModelProvider(generator, container);
//        modelProvider.addGenerator(CarpenterBlockModelProvider::new);
//        generator.addProvider(modelProvider);
//
//        PollinatedLootTableProvider lootProvider = new PollinatedLootTableProvider(generator);
//        lootProvider.add(LootContextParamSets.BLOCK, new CarpenterBlockLootGenerator(container));
//        generator.addProvider(lootProvider);
//
//        CarpenterBlockTagsProvider blockTagsProvider = new CarpenterBlockTagsProvider(generator, container);
//        generator.addProvider(blockTagsProvider);
//        generator.addProvider(new CarpenterItemTagsProvider(generator, container, blockTagsProvider));
//        generator.addProvider(new CarpenterLanguageProvider(generator, container));
//        generator.addProvider(new CarpenterRecipeProvider(generator));
//    }
}

