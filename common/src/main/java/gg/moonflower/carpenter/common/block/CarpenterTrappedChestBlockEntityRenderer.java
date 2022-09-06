package gg.moonflower.carpenter.common.block;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CarpenterTrappedChestBlockEntityRenderer extends CarpenterChestBlockEntityRenderer {
    public CarpenterTrappedChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}
