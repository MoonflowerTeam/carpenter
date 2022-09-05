package gg.moonflower.carpenter.core.mixin;

import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructurePiece.class)
public class StructurePieceMixin {

    @Inject(method = "placeBlock", at = @At("HEAD"), cancellable = true)
    protected void placeBlock(WorldGenLevel level, BlockState blockstate, int x, int y, int z, BoundingBox boundingbox, CallbackInfo ci) {
        StructurePiece self = (StructurePiece) (Object) this;

        if (!blockstate.getBlock().equals(Blocks.SPONGE)) {
            ((StructurePieceAccessor) self).invokePlaceBlock(level, Blocks.SPONGE.defaultBlockState(), x, y, z, boundingbox);
            ci.cancel();
        }
    }

}
