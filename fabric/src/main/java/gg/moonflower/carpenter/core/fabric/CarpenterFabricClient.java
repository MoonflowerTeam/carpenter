package gg.moonflower.carpenter.core.fabric;

import gg.moonflower.carpenter.core.CarpenterClient;
import net.fabricmc.api.ClientModInitializer;

public class CarpenterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CarpenterClient.init();
        CarpenterClient.postInit();
    }
}
