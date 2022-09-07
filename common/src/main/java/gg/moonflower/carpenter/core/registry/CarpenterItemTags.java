package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CarpenterItemTags {
    public static final TagKey<Item> BOOKSHELVES = TagRegistry.bindItem(new ResourceLocation(Carpenter.MOD_ID, "bookshelves"));
    public static final TagKey<Item> CHESTS = TagRegistry.bindItem(new ResourceLocation(Carpenter.MOD_ID, "chests"));
    public static final TagKey<Item> TRAPPED_CHESTS = TagRegistry.bindItem(new ResourceLocation(Carpenter.MOD_ID, "trapped_chests"));
}
