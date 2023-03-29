package gg.moonflower.carpenter.core.forge;

import dev.architectury.platform.forge.EventBuses;
import gg.moonflower.carpenter.client.CarpenterClient;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Carpenter.MOD_ID)
public class CarpenterForge {
    public CarpenterForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Carpenter.MOD_ID, eventBus);
        eventBus.addListener(this::commonInit);
        eventBus.addListener(this::clientInit);

        Carpenter.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> CarpenterClient::init);
    }

    private void commonInit(FMLCommonSetupEvent event) {
        Carpenter.postInit();
    }

    private void clientInit(FMLClientSetupEvent event) {
        CarpenterClient.postInit();
    }
}
