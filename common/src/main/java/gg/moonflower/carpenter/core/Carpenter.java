package gg.moonflower.carpenter.core;

import gg.moonflower.carpenter.core.datagen.CarpenterBlockModelProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterBlockTagsProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterLanguageProvider;
import gg.moonflower.carpenter.core.datagen.CarpenterRecipeProvider;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterItems;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.data.DataGenerator;

public class Carpenter {
    public static final String MOD_ID = "carpenter";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Carpenter::onClientInit)
            .commonInit(Carpenter::onCommonInit)
            .dataInit(Carpenter::onDataInit)
            .build();

    public static void onCommonInit() {
        CarpenterBlocks.REGISTRY.register(Carpenter.PLATFORM);
        CarpenterItems.REGISTRY.register(Carpenter.PLATFORM);
    }

    public static void onClientInit() {
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

