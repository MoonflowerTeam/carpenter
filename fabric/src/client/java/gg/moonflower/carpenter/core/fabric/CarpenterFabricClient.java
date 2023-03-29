package gg.moonflower.carpenter.core.fabric;

import gg.moonflower.carpenter.client.CarpenterClient;
import net.fabricmc.api.ClientModInitializer;

public class CarpenterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CarpenterClient.init();
        CarpenterClient.postInit();
    }
}
