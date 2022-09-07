package gg.moonflower.carpenter.common.block.entity;

import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CarpenterChestBlockEntity extends ChestBlockEntity {
    public CarpenterChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public CarpenterChestBlockEntity(BlockPos pos, BlockState state) {
        this(CarpenterBlocks.CARPENTER_CHEST_BE.get(), pos, state);
    }
}
