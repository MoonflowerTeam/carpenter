package gg.moonflower.carpenter.core.mixin;

import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = StructureTemplate.class, priority = 1100)
public class StructureTemplateMixin {

    @ModifyVariable(
            method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Ljava/util/Random;I)Z",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$StructureBlockInfo;nbt:Lnet/minecraft/nbt/CompoundTag;", ordinal = 0),
            index = 23
    )
    private BlockState blockStateModify(BlockState state, ServerLevelAccessor accessor) {
        return StructureInjections.getReplacement(accessor, state);
    }

}
