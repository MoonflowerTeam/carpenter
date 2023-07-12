package gg.moonflower.carpenter.core;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.client.render.block.entity.CarpenterChestBlockEntityRenderer;
import gg.moonflower.carpenter.client.render.item.CarpenterChestItemRenderer;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.registry.render.v1.ItemRendererRegistry;
import gg.moonflower.pollen.api.registry.render.v1.ModelRegistry;
import net.minecraft.core.Registry;

public class CarpenterClient {
    public static void init() {
    }

    public static void postInit() {
        CarpenterChests.REGISTRY.iterator().forEachRemaining((supplier) -> {
            CarpenterChestType chestType = supplier.get();
            ModelRegistry.registerSpecial(chestType.body());
            ModelRegistry.registerSpecial(chestType.leftBody());
            ModelRegistry.registerSpecial(chestType.rightBody());
            ModelRegistry.registerSpecial(chestType.lid());
            ModelRegistry.registerSpecial(chestType.leftLid());
            ModelRegistry.registerSpecial(chestType.rightLid());
            ModelRegistry.registerSpecial(chestType.knob());
        });
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_CHEST_BE.get(), CarpenterChestBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE.get(), CarpenterChestBlockEntityRenderer::new);
        Registry.BLOCK.stream().filter((x) -> x instanceof CarpenterChestBlock).forEach(chest -> ItemRendererRegistry.registerRenderer(chest.asItem(), new CarpenterChestItemRenderer((CarpenterChestBlock) chest)));
    }
}
