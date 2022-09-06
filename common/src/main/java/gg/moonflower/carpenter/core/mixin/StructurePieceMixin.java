package gg.moonflower.carpenter.core.mixin;

import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.ServerLevelAccessor;
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

@Mixin(value = StructurePiece.class, priority = 1100)
public class StructurePieceMixin {

    @Inject(method = "placeBlock", at = @At("HEAD"), cancellable = true)
    protected void placeBlock(WorldGenLevel level, BlockState blockstate, int x, int y, int z, BoundingBox boundingbox, CallbackInfo ci) {

        StructurePiece self = (StructurePiece) (Object) this;

        BlockState replacement = StructureInjections.getReplacement(level, blockstate);
        if (!replacement.getBlock().equals(blockstate.getBlock())) {
            ((StructurePieceAccessor) self).invokePlaceBlock(level, replacement, x, y, z, boundingbox);
            ci.cancel();
        }
    }

    @ModifyVariable(
            method = "createChest(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Ljava/util/Random;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At(value = "INVOKE", shift = At.Shift.BY, by = -2, target = "Lnet/minecraft/world/level/ServerLevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"),
            argsOnly = true
    )
    protected BlockState placeChest(BlockState state, ServerLevelAccessor accessor) {
        return StructureInjections.getReplacement(accessor, state);
    }


}
