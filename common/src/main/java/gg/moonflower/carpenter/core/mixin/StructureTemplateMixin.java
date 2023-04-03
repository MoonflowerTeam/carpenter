package gg.moonflower.carpenter.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = StructureTemplate.class, priority = 1100)
public class StructureTemplateMixin {

    @WrapOperation(
            method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Lnet/minecraft/util/RandomSource;I)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;rotate(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;")
    )
    private BlockState blockStateModify(BlockState state, Rotation rotation, Operation<BlockState> original, ServerLevelAccessor level) {
        if (Carpenter.CONFIG.enableStructureReplacements.get())
            state = StructureInjections.getReplacement(level, state);
        return original.call(state, rotation);
    }

}
