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

import java.util.function.Supplier;

public class CarpenterClient {

    public static void init() {
        ModelRegistry.registerFactory((resourceManager, consumer) -> {
            for (Supplier<CarpenterChestType> supplier : CarpenterChests.REGISTRY) {
                CarpenterChestType chestType = supplier.get();
                consumer.accept(chestType.body());
                consumer.accept(chestType.leftBody());
                consumer.accept(chestType.rightBody());
                consumer.accept(chestType.lid());
                consumer.accept(chestType.leftLid());
                consumer.accept(chestType.rightLid());
                consumer.accept(chestType.knob());
            }
        });
    }

    public static void postInit() {
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_CHEST_BE.get(), CarpenterChestBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE.get(), CarpenterChestBlockEntityRenderer::new);
        Registry.BLOCK.stream().filter((x) -> x instanceof CarpenterChestBlock).forEach(chest -> ItemRendererRegistry.registerRenderer(chest.asItem(), new CarpenterChestItemRenderer((CarpenterChestBlock) chest)));
    }
}
