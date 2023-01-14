package gg.moonflower.carpenter.api.v1.registry;

import net.minecraft.resources.ResourceLocation;

/**
 * Represents a basic chest type. This holds all the data required to generate the chest model in-game.
 *
 * @param body      The location of the single chest body texture
 * @param leftBody  The location of the double chest left texture
 * @param rightBody The location of the double chest right texture
 * @param lid       The location of the single chest lid texture
 * @param leftLid   The location of the double chest left lid texture
 * @param rightLid  The location of the double chest right lid texture
 * @param knob      The location of the knob texture. This is separate to be re-used between all chests
 */
public record CarpenterChestType(ResourceLocation body, ResourceLocation leftBody, ResourceLocation rightBody,
                                 ResourceLocation lid, ResourceLocation leftLid, ResourceLocation rightLid,
                                 ResourceLocation knob) {
}