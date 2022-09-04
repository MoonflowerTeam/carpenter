package gg.moonflower.carpenter.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.carpenter.core.mixin.ModelManagerAccessor;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class CarpenterChestBlockEntityRenderer implements BlockEntityRenderer<CarpenterChestBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public CarpenterChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(CarpenterChestBlockEntity blockEntity, float partialTick, PoseStack ms, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        final Minecraft mc = Minecraft.getInstance();

        BlockState blockState = blockEntity.getBlockState();
        final Block block = blockState.getBlock();

        if(!(block instanceof CarpenterChestBlock)) return;
        final CarpenterChestBlock chest = (CarpenterChestBlock) block;

        final CarpenterChestType chestType = chest.getType().get();

        BlockRenderDispatcher blockRenderDispatcher = mc.getBlockRenderer();
        ModelManager modelManager = blockRenderDispatcher.getBlockModelShaper().getModelManager();
        ModelManagerAccessor modelManagerAccessor = (ModelManagerAccessor) modelManager;

        final Map<ResourceLocation, BakedModel> bakedRegistry = modelManagerAccessor.getBakedRegistry();

        BakedModel body = bakedRegistry.getOrDefault(chestType.body(), modelManager.getMissingModel());
        BakedModel lid = bakedRegistry.getOrDefault(chestType.lid(), modelManager.getMissingModel());

        ms.pushPose();
        ms.translate(0.5, 0.5, 0.5);
        ms.mulPose(Vector3f.YN.rotationDegrees(blockState.getValue(ChestBlock.FACING).toYRot() - 180));
        ms.translate(-0.5, -0.5, -0.5);
        blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer.getBuffer(Sheets.solidBlockSheet()), (BlockState)null, body, 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY);

        ms.pushPose();
//        ms.mulPose();
        blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer.getBuffer(Sheets.solidBlockSheet()), (BlockState)null, lid, 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY);
        ms.popPose();
        ms.popPose();
    }

}
