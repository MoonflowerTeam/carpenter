package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CarpenterTags {
    public static final TagKey<Block> BOOKSHELVES = TagRegistry.bindBlock(new ResourceLocation(Carpenter.MOD_ID, "bookshelves"));
}
