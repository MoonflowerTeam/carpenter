package gg.moonflower.carpenter.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class CarpenterItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Carpenter.MOD_ID, Registry.ITEM_REGISTRY);
}
