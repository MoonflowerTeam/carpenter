package gg.moonflower.carpenter.common.worldgen;

import com.google.common.collect.ImmutableList;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StructureInjections {

    private static ConfiguredStructureFeature<?, ?> feature;
    private static PiecesContainer pieceContainer;

    // TODO: These need to be configurable with a datapack reload listener. It's possible for mods to just add to this hashmap normally for compat, but it should be easier
    public static final Map<ResourceLocation, List<StructureBlockReplacementEntry>> REPLACEMENTS = new HashMap<>() {{
        put(new ResourceLocation("village_plains"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.OAK_CHEST)
        ));
        put(new ResourceLocation("village_desert"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.JUNGLE_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.JUNGLE_CHEST)
        ));
        put(new ResourceLocation("village_savanna"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.ACACIA_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.ACACIA_CHEST)
        ));
        put(new ResourceLocation("village_snowy"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.SPRUCE_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.SPRUCE_CHEST)
        ));
        put(new ResourceLocation("village_taiga"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.SPRUCE_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.SPRUCE_CHEST)
        ));

        put(new ResourceLocation("mansion"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.DARK_OAK_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.DARK_OAK_CHEST)
        ));
        put(new ResourceLocation("bastion_remnant"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.CRIMSON_CHEST)
        ));
        put(new ResourceLocation("stronghold"), ImmutableList.of(
                new StructureBlockReplacementEntry(Blocks.OAK_PLANKS, () -> Blocks.SPRUCE_PLANKS),
                new StructureBlockReplacementEntry(Blocks.OAK_FENCE, () -> Blocks.SPRUCE_FENCE),
                new StructureBlockReplacementEntry(Blocks.OAK_TRAPDOOR, () -> Blocks.SPRUCE_TRAPDOOR),
                new StructureBlockReplacementEntry(Blocks.OAK_DOOR, () -> Blocks.SPRUCE_DOOR),
                new StructureBlockReplacementEntry(Blocks.BOOKSHELF, CarpenterBlocks.SPRUCE_BOOKSHELF),
                new StructureBlockReplacementEntry(Blocks.CHEST, CarpenterBlocks.SPRUCE_CHEST)
        ));
    }};

    public static BlockState getReplacement(ServerLevelAccessor accessor, BlockState state) {
        Optional<ResourceLocation> structureOptional = accessor.registryAccess().registry(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY).map(
                (it) -> it.getKey(feature));

        if (structureOptional.isEmpty())
            return state;

        ResourceLocation structure = structureOptional.get();
        List<StructureBlockReplacementEntry> entries = REPLACEMENTS.get(structure);

        if (entries == null)
            return state;

        for (StructureBlockReplacementEntry entry : entries) {
            if (state.is(entry.toReplace)) {
                return entry.replacement.get().withPropertiesOf(state);
            }
        }

        return state;
    }

    public static void pushStructure(ConfiguredStructureFeature<?, ?> feature, PiecesContainer pieceContainer) {
        StructureInjections.feature = feature;
        StructureInjections.pieceContainer = pieceContainer;
    }

    public static void popStructure() {
        feature = null;
        pieceContainer = null;
    }

    public record StructureBlockReplacementEntry(Block toReplace, Supplier<Block> replacement) {}

}
