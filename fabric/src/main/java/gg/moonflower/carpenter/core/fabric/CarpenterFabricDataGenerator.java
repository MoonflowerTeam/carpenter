package gg.moonflower.carpenter.core.fabric;

import gg.moonflower.carpenter.core.Carpenter;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CarpenterFabricDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Carpenter.PLATFORM.dataSetup(fabricDataGenerator);
    }
}
