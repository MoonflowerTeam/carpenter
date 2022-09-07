package gg.moonflower.carpenter.core.mixin;

import gg.moonflower.carpenter.common.worldgen.StructureInjections;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = StructureStart.class)
public class StructureStartMixin {

    @Shadow @Final private ConfiguredStructureFeature<?, ?> feature;

    @Shadow @Final private PiecesContainer pieceContainer;

    @Inject(method = "placeInChunk", at = @At("HEAD"))
    private void pushStructure(WorldGenLevel level, StructureFeatureManager structureManager, ChunkGenerator chunkGenerator, Random random, BoundingBox box, ChunkPos chunkPos, CallbackInfo ci) {
        StructureInjections.pushStructure(this.feature, this.pieceContainer);
    }

    @Inject(method = "placeInChunk", at = @At("RETURN"))
    private void popStructure(WorldGenLevel level, StructureFeatureManager structureManager, ChunkGenerator chunkGenerator, Random random, BoundingBox box, ChunkPos chunkPos, CallbackInfo ci) {
        StructureInjections.popStructure();
    }

}
