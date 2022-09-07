package gg.moonflower.carpenter.common.block.entity;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CarpenterTrappedChestBlockEntity extends CarpenterChestBlockEntity {
    public CarpenterTrappedChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public CarpenterTrappedChestBlockEntity(BlockPos pos, BlockState state) {
        this(CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE.get(), pos, state);
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int i, int j) {
        super.signalOpenCount(level, pos, state, i, j);
        if (i != j) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }
}
