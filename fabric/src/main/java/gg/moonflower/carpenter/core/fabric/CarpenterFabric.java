package gg.moonflower.carpenter.core.fabric;

import gg.moonflower.carpenter.core.Carpenter;
import net.fabricmc.api.ModInitializer;

public class CarpenterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Carpenter.onCommonInit();
        Carpenter.onCommonPostInit();
    }
}
