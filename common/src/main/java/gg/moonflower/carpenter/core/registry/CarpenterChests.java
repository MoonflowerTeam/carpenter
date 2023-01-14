package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.resources.ResourceLocation;

public class CarpenterChests {
    public static final PollinatedRegistry<CarpenterChestType> REGISTRY = PollinatedRegistry.createSimple(new ResourceLocation(Carpenter.MOD_ID, "chest_type"));
}
