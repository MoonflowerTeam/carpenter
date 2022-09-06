package gg.moonflower.carpenter.common.worldgen;

import com.google.common.collect.ImmutableList;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.ShipwreckFeature;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class StructureInjections {

    private static ConfiguredStructureFeature<?, ?> feature;
    private static PiecesContainer pieceContainer;

    // TODO: These need to be configurable with a datapack reload listener. It's possible for mods to just add to this hashmap normally for compat, but it should be easier
    public static HashMap<ResourceLocation, List<StructureBlockReplacementEntry>> replacementMappings = new HashMap<>() {{
        put(new ResourceLocation("village_plains"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.OAK_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("village_desert"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.JUNGLE_BOOKSHELF.get().defaultBlockState()), new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.JUNGLE_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("village_savanna"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.ACACIA_BOOKSHELF.get().defaultBlockState()), new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.ACACIA_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("village_snowy"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.SPRUCE_BOOKSHELF.get().defaultBlockState()), new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.SPRUCE_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("village_taiga"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.SPRUCE_BOOKSHELF.get().defaultBlockState()), new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.SPRUCE_CHEST.get().defaultBlockState())));

        put(new ResourceLocation("mansion"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.DARK_OAK_BOOKSHELF.get().defaultBlockState()), new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.DARK_OAK_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("bastion_remnant"), ImmutableList.of(new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.CRIMSON_CHEST.get().defaultBlockState())));
        put(new ResourceLocation("stronghold"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.OAK_PLANKS, () -> Blocks.SPRUCE_PLANKS.defaultBlockState()),
                new StructureBlockReplacementEntry(Blocks.OAK_FENCE, () -> Blocks.SPRUCE_FENCE.defaultBlockState()),
                new StructureBlockReplacementEntry(Blocks.OAK_TRAPDOOR, () -> Blocks.SPRUCE_TRAPDOOR.defaultBlockState()),
                new StructureBlockReplacementEntry(Blocks.OAK_DOOR, () -> Blocks.SPRUCE_DOOR.defaultBlockState()),
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, () -> CarpenterBlocks.SPRUCE_BOOKSHELF.get().defaultBlockState()),
                new StructureBlockReplacementEntry(Blocks.CHEST, () -> CarpenterBlocks.SPRUCE_CHEST.get().defaultBlockState())
        ));
    }};

    public static BlockState getReplacement(ServerLevelAccessor accessor, BlockState state) {
        Optional<ResourceLocation> res = accessor.registryAccess().registry(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY).map(
                (it) -> it.getKey(feature));

        if (res.isEmpty())
            return state;

        ResourceLocation resourceLocation = res.get();
        List<StructureBlockReplacementEntry> entries = replacementMappings.get(resourceLocation);

        if (entries == null)
            return state;

        for (StructureBlockReplacementEntry entry : entries) {
            if (entry.toReplace.equals(state.getBlock())) {
                BlockState replacementUnpropped = entry.replacement.get();

                for (Property<?> property : state.getProperties()) {
                    replacementUnpropped = replacementUnpropped.setValue((Property) property, state.getValue(property));
                }

                return replacementUnpropped;
            }
        }

        return state;
    }

    public static void pushStructure(ConfiguredStructureFeature feature, PiecesContainer pieceContainer) {
        StructureInjections.feature = feature;
        StructureInjections.pieceContainer = pieceContainer;
    }

    public static void popStructure() {
        feature = null;
        pieceContainer = null;
    }

    public record StructureBlockReplacementEntry(Block toReplace, Supplier<BlockState> replacement) {}

}
