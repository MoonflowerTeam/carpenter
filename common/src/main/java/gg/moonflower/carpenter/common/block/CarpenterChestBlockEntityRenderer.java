package gg.moonflower.carpenter.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Constants;
import com.mojang.math.Vector3f;
import gg.moonflower.carpenter.core.mixin.ModelManagerAccessor;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
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
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.math.MathContext;
import java.util.Map;

public class CarpenterChestBlockEntityRenderer implements BlockEntityRenderer<CarpenterChestBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public CarpenterChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(CarpenterChestBlockEntity blockEntity, float partialTick, PoseStack ms, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        final Minecraft mc = Minecraft.getInstance();

        Level level = blockEntity.getLevel();
        boolean bl = level != null;
        BlockState blockState = bl ? blockEntity.getBlockState() : blockEntity.getBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        final Block block = blockState.getBlock();

        if(!(block instanceof CarpenterChestBlock)) return;
        final CarpenterChestBlock chest = (CarpenterChestBlock) block;

        final CarpenterChestType chestType = chest.getType().get();

        BlockRenderDispatcher blockRenderDispatcher = mc.getBlockRenderer();
        ModelManager modelManager = blockRenderDispatcher.getBlockModelShaper().getModelManager();
        ModelManagerAccessor modelManagerAccessor = (ModelManagerAccessor) modelManager;

        final Map<ResourceLocation, BakedModel> bakedRegistry = modelManagerAccessor.getBakedRegistry();

        ResourceLocation bodyLoc = chestType.body();
        ResourceLocation lidLoc = chestType.lid();
        ChestType connectionType = blockState.getValue(ChestBlock.TYPE);

        switch (connectionType) {
            case LEFT -> {
                bodyLoc = chestType.rightBody();
                lidLoc = chestType.rightLid();
                break;
            }
            case RIGHT -> {
                bodyLoc = chestType.leftBody();
                lidLoc = chestType.leftLid();
                break;
            }
        }

        BakedModel body = bakedRegistry.getOrDefault(bodyLoc, modelManager.getMissingModel());
        BakedModel lid = bakedRegistry.getOrDefault(lidLoc, modelManager.getMissingModel());
        BakedModel knob = bakedRegistry.getOrDefault(chestType.knob(), modelManager.getMissingModel());
        ms.pushPose();
        ms.translate(0.5, 0.5, 0.5);
        ms.mulPose(Vector3f.YN.rotationDegrees(blockState.getValue(ChestBlock.FACING).toYRot() - 180));
        ms.translate(-0.5, -0.5, -0.5);
        blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer.getBuffer(Sheets.solidBlockSheet()), (BlockState)null, body, 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY);

        // vanilla lid nonsense begin


        float g = 0.0f;
        if (level != null) {
            AbstractChestBlock<?> abstractChestBlock = (AbstractChestBlock<?>)block;

            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborCombineResult;

            neighborCombineResult = abstractChestBlock.combine(blockState, level, blockEntity.getBlockPos(), true);
            g = neighborCombineResult.apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTick);
            g = 1.0F - g;
            g = 1.0F - g * g * g;
            g = g * (90F * Constants.DEG_TO_RAD);
        }

        // vanilla lid nonsense end

        ms.pushPose();

        ms.translate(0.0, 9 / 16.0, 14 / 16.0);
        ms.mulPose(Vector3f.XP.rotation(g));
        ms.translate(0.0, -9 / 16.0, -14 / 16.0);
        blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null, lid, 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY);

        if(connectionType == ChestType.LEFT)
            ms.translate(0.5, 0.0, 0.0);

        if(connectionType != ChestType.RIGHT && knob != modelManager.getMissingModel())
            blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer.getBuffer(Sheets.cutoutBlockSheet()), null, knob, 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.NO_OVERLAY);
        ms.popPose();
        ms.popPose();
    }

}
