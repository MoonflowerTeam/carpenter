package gg.moonflower.carpenter.core.registry;

import net.minecraft.resources.ResourceLocation;

public record CarpenterChestType(ResourceLocation body, ResourceLocation leftBody, ResourceLocation rightBody, ResourceLocation lid, ResourceLocation leftLid, ResourceLocation rightLid, ResourceLocation knob) { }