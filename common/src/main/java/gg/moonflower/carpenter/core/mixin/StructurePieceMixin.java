package gg.moonflower.carpenter.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = StructurePiece.class, priority = 1100)
public abstract class StructurePieceMixin {

    @WrapOperation(
            method = "placeBlock",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")
    )
    private boolean replaceBlock(WorldGenLevel instance, BlockPos blockPos, BlockState blockState, int i, Operation<Boolean> original) {
        if (Carpenter.CONFIG.enableStructureReplacements.get())
            blockState = StructureInjections.getReplacement(instance, blockState);
        return original.call(instance, blockPos, blockState, i);
    }

    @WrapOperation(
            method = "createChest(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerLevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")
    )
    private boolean replaceChest(ServerLevelAccessor instance, BlockPos blockPos, BlockState blockState, int i, Operation<Boolean> original) {
        if (Carpenter.CONFIG.enableStructureReplacements.get())
            blockState = StructureInjections.getReplacement(instance, blockState);
        return original.call(instance, blockPos, blockState, i);
    }
}
