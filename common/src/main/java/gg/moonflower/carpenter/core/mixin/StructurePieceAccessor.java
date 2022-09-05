package gg.moonflower.carpenter.core.mixin;

import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {

    @Invoker
    void invokePlaceBlock(WorldGenLevel level, BlockState blockstate, int x, int y, int z, BoundingBox boundingbox);

}
