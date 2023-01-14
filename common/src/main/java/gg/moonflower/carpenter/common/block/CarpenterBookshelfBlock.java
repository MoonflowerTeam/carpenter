package gg.moonflower.carpenter.common.block;

import gg.moonflower.carpenter.core.registry.CarpenterBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CarpenterBookshelfBlock extends Block {
    public CarpenterBookshelfBlock(Properties properties) {
        super(properties);
    }

    // This method is so bookshelves get a power bonus on Forge.
    // It'll be overridden on Forge and ignored on Fabric.
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return state.is(CarpenterBlockTags.BOOKSHELVES) ? 1.0F : 0.0F;
    }
}
