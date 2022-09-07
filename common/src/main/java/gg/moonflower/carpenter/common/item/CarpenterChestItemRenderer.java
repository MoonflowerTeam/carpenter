package gg.moonflower.carpenter.common.item;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.entity.CarpenterChestBlockEntity;
import gg.moonflower.pollen.api.client.render.DynamicItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public class CarpenterChestItemRenderer implements DynamicItemRenderer {
    private final CarpenterChestBlockEntity chest;

    public CarpenterChestItemRenderer(CarpenterChestBlock chest) {
        this.chest = new CarpenterChestBlockEntity(BlockPos.ZERO, chest.defaultBlockState());
    }

    @Override
    public void render(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack ms, MultiBufferSource buffer, int packedLight, int combinedOverlay) {
        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(chest, ms, buffer, packedLight, combinedOverlay);
    }
}
