package gg.moonflower.carpenter.core;

import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.client.render.block.entity.CarpenterChestBlockEntityRenderer;
import gg.moonflower.carpenter.common.item.CarpenterChestItemRenderer;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockModelProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockTagsProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterItemTagsProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterLanguageProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockLootGenerator;
import gg.moonflower.carpenter.core.datagen.CarpenterRecipeProvider;
import gg.moonflower.carpenter.core.registry.*;
import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.datagen.provider.loot_table.PollinatedLootTableProvider;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.BlockEntityRendererRegistry;
import gg.moonflower.pollen.api.registry.client.ItemRendererRegistry;
import gg.moonflower.pollen.api.registry.client.ModelRegistry;
import gg.moonflower.pollen.api.registry.content.FlammabilityRegistry;
import gg.moonflower.pollen.api.registry.content.FurnaceFuelRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class Carpenter {
    public static final String MOD_ID = "carpenter";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> ClientLoading::onClientInit)
            .clientPostInit(() -> ClientLoading::onClientPostInit)
            .commonInit(Carpenter::onCommonInit)
            .commonPostInit(Carpenter::onCommonPostInit)
            .dataInit(Carpenter::onDataInit)
            .build();

    public static final CarpenterConfig CONFIG = ConfigManager.register(Carpenter.MOD_ID, PollinatedConfigType.COMMON, CarpenterConfig::new);

    public static ResourceLocation carpenter(String path) {
        return new ResourceLocation(Carpenter.MOD_ID, path);
    }

    public static void onCommonInit() {
        CarpenterBlocks.CHEST_REGISTRY.register();
        CarpenterBlocks.BLOCK_ENTITY_REGISTRY.register(Carpenter.PLATFORM);
        CarpenterRecipes.REGISTRY.register(Carpenter.PLATFORM);
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx) {
        FurnaceFuelRegistry.register(CarpenterBlocks.OAK_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.SPRUCE_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.BIRCH_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.JUNGLE_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.ACACIA_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.DARK_OAK_CHEST.get(), 300);

        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_OAK_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_SPRUCE_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_BIRCH_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_JUNGLE_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_ACACIA_CHEST.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.TRAPPED_DARK_OAK_CHEST.get(), 300);

        FurnaceFuelRegistry.register(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.BIRCH_BOOKSHELF.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.ACACIA_BOOKSHELF.get(), 300);
        FurnaceFuelRegistry.register(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), 300);

        FlammabilityRegistry.register(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), 30, 20);
        FlammabilityRegistry.register(CarpenterBlocks.BIRCH_BOOKSHELF.get(), 30, 20);
        FlammabilityRegistry.register(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), 30, 20);
        FlammabilityRegistry.register(CarpenterBlocks.ACACIA_BOOKSHELF.get(), 30, 20);
        FlammabilityRegistry.register(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), 30, 20);
    }

    public static class ClientLoading {
        public static void onClientInit() {
            CarpenterChests.REGISTRY.stream().forEach((chestType) -> {
                ModelRegistry.registerSpecial(chestType.body());
                ModelRegistry.registerSpecial(chestType.leftBody());
                ModelRegistry.registerSpecial(chestType.rightBody());
                ModelRegistry.registerSpecial(chestType.lid());
                ModelRegistry.registerSpecial(chestType.leftLid());
                ModelRegistry.registerSpecial(chestType.rightLid());
                ModelRegistry.registerSpecial(chestType.knob());
            });

            BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_CHEST_BE, CarpenterChestBlockEntityRenderer::new);
            BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE, CarpenterChestBlockEntityRenderer::new);
        }

        public static void onClientPostInit(Platform.ModSetupContext modSetupContext) {
            Registry.BLOCK.stream().filter((x) -> x instanceof CarpenterChestBlock).forEach(chest -> {
                ItemRendererRegistry.registerRenderer(chest.asItem(), new CarpenterChestItemRenderer((CarpenterChestBlock) chest));
            });
        }
    }

    public static void onDataInit(Platform.DataSetupContext ctx) {
        DataGenerator generator = ctx.getGenerator();
        PollinatedModContainer container = ctx.getMod();

        PollinatedModelProvider modelProvider = new PollinatedModelProvider(generator, container);
        modelProvider.addGenerator(CarpenterBlockModelProvider::new);
        generator.addProvider(modelProvider);

        PollinatedLootTableProvider lootProvider = new PollinatedLootTableProvider(generator);
        lootProvider.add(LootContextParamSets.BLOCK, new CarpenterBlockLootGenerator(container));
        generator.addProvider(lootProvider);

        CarpenterBlockTagsProvider blockTagsProvider = new CarpenterBlockTagsProvider(generator, container);
        generator.addProvider(blockTagsProvider);
        generator.addProvider(new CarpenterItemTagsProvider(generator, container, blockTagsProvider));
        generator.addProvider(new CarpenterLanguageProvider(generator, container));
        generator.addProvider(new CarpenterRecipeProvider(generator));
    }
}

