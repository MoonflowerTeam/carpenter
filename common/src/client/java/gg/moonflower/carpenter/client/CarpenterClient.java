package gg.moonflower.carpenter.client;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import gg.moonflower.carpenter.client.render.block.entity.CarpenterChestBlockEntityRenderer;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.item.CarpenterChestItemRenderer;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import net.minecraft.core.Registry;

public class CarpenterClient {
    public static void init() {
        CarpenterChests.REGISTRAR.stream().forEach((chestType) -> {
            ModelRegistry.registerSpecial(chestType.body());
            ModelRegistry.registerSpecial(chestType.leftBody());
            ModelRegistry.registerSpecial(chestType.rightBody());
            ModelRegistry.registerSpecial(chestType.lid());
            ModelRegistry.registerSpecial(chestType.leftLid());
            ModelRegistry.registerSpecial(chestType.rightLid());
            ModelRegistry.registerSpecial(chestType.knob());
        });

        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_CHEST_BE, CarpenterChestBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE, CarpenterChestBlockEntityRenderer::new);
    }

    public static void postInit() {
        Registry.BLOCK.stream().filter((x) -> x instanceof CarpenterChestBlock).forEach(chest -> {
            ItemRendererRegistry.registerRenderer(chest.asItem(), new CarpenterChestItemRenderer((CarpenterChestBlock) chest));
        });
    }
}
