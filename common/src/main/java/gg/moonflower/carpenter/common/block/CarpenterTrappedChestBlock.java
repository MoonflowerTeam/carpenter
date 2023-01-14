package gg.moonflower.carpenter.common.block;

import gg.moonflower.carpenter.api.v1.registry.CarpenterChestType;
import gg.moonflower.carpenter.common.block.entity.CarpenterTrappedChestBlockEntity;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @see gg.moonflower.carpenter.api.v1.registry.ChestRegistry
 */
public class CarpenterTrappedChestBlock extends CarpenterChestBlock {

    public CarpenterTrappedChestBlock(Supplier<CarpenterChestType> type, Properties properties) {
        super(type, properties, CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE::get);
    }

    // TODO make this protected
    @Deprecated
    public CarpenterTrappedChestBlock(Supplier<CarpenterChestType> type, Properties properties, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(type, properties, supplier);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CarpenterTrappedChestBlockEntity(pos, state);
    }

    @Override
    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return Mth.clamp(ChestBlockEntity.getOpenCount(level, pos), 0, 15);
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? state.getSignal(level, pos, direction) : 0;
    }
}
