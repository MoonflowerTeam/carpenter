package gg.moonflower.carpenter.core.mixin;

import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.mixinextras.injector.wrapoperation.Operation;
import gg.moonflower.pollen.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
            method = "createChest(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Ljava/util/Random;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerLevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")
    )
    private boolean replaceChest(ServerLevelAccessor instance, BlockPos blockPos, BlockState blockState, int i, Operation<Boolean> original) {
        if (Carpenter.CONFIG.enableStructureReplacements.get())
            blockState = StructureInjections.getReplacement(instance, blockState);
        return original.call(instance, blockPos, blockState, i);
    }
}
