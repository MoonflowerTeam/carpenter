package gg.moonflower.carpenter.common.item;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.CarpenterChestBlockEntity;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.pollen.api.client.render.DynamicItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class CarpenterChestItemRenderer implements DynamicItemRenderer {
    CarpenterChestBlock chest;
    private final CarpenterChestBlockEntity chestBE;

    public CarpenterChestItemRenderer(CarpenterChestBlock chest) {
        this.chestBE = new CarpenterChestBlockEntity(BlockPos.ZERO, chest.defaultBlockState());
        this.chest = chest;
    }

    @Override
    public void render(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack ms, MultiBufferSource buffer, int packedLight, int combinedOverlay) {

        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem((BlockEntity)chestBE, ms, buffer, packedLight, combinedOverlay);
    }
}
