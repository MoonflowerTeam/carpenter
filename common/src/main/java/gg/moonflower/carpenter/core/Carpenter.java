package gg.moonflower.carpenter.core;

import gg.moonflower.carpenter.common.block.CarpenterChestBlockEntityRenderer;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockModelProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockTagsProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterLanguageProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterRecipeProvider;
import gg.moonflower.carpenter.core.registry.*;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.BlockEntityRendererRegistry;
import gg.moonflower.pollen.api.registry.client.ModelRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

public class Carpenter {
    public static final String MOD_ID = "carpenter";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Carpenter::onClientInit)
            .clientPostInit(() -> Carpenter::onClientPostInit)
            .commonInit(Carpenter::onCommonInit)
            .dataInit(Carpenter::onDataInit)
            .build();


    public static ResourceLocation carpenter(String path) {
        return new ResourceLocation(Carpenter.MOD_ID, path);
    }

    public static void onCommonInit() {
        CarpenterChests.CHEST_TYPE_REGISTRY.register(Carpenter.PLATFORM);
        CarpenterBlocks.BLOCK_ENTITY_REGISTRY.register(Carpenter.PLATFORM);
        CarpenterBlocks.REGISTRY.register(Carpenter.PLATFORM);
        CarpenterItems.REGISTRY.register(Carpenter.PLATFORM);
    }

    public static void onClientInit() {
        CarpenterChests.CHEST_TYPE_REGISTRY.stream().forEach((chestType) -> {
            ModelRegistry.registerSpecial(chestType.body());
            ModelRegistry.registerSpecial(chestType.leftBody());
            ModelRegistry.registerSpecial(chestType.rightBody());
            ModelRegistry.registerSpecial(chestType.lid());
            ModelRegistry.registerSpecial(chestType.leftLid());
            ModelRegistry.registerSpecial(chestType.rightLid());
        });
    }

    public static void onClientPostInit(Platform.ModSetupContext modSetupContext) {
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_CHEST_BE, CarpenterChestBlockEntityRenderer::new);
    }

    public static void onDataInit(Platform.DataSetupContext ctx) {
        DataGenerator generator = ctx.getGenerator();
        PollinatedModContainer container = ctx.getMod();

        PollinatedModelProvider modelProvider = new PollinatedModelProvider(generator, container);
        modelProvider.addGenerator(CarpenterBlockModelProvider::new);
        generator.addProvider(modelProvider);

        generator.addProvider(new CarpenterBlockTagsProvider(generator, container));
        generator.addProvider(new CarpenterLanguageProvider(generator, container));
        generator.addProvider(new CarpenterRecipeProvider(generator));
    }
}

