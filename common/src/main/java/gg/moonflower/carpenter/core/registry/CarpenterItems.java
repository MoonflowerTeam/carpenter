package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class CarpenterItems {
    public static final PollinatedRegistry<Item> REGISTRY = PollinatedRegistry.create(Registry.ITEM, Carpenter.MOD_ID);
}
