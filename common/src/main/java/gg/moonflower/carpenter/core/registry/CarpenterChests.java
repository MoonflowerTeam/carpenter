package gg.moonflower.carpenter.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.Registries;
import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class CarpenterChests {

    public static final ResourceKey<Registry<CarpenterChestType>> REGISTRY_KEY = ResourceKey.createRegistryKey(Carpenter.carpenter("chest_type"));
    private static final Registrar<CarpenterChestType> REGISTRAR = Registries.get(Carpenter.MOD_ID).<CarpenterChestType>builder(REGISTRY_KEY.location())
            .saveToDisc()
            .syncToClients()
            .build();

    public static final DeferredRegister<CarpenterChestType> REGISTRY = DeferredRegister.create(Carpenter.MOD_ID, CarpenterChests.REGISTRY_KEY);
}
