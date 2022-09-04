package gg.moonflower.carpenter.core.datagen;

import com.google.gson.JsonElement;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedBlockModelGenerator;
import net.minecraft.core.Registry;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.w3c.dom.Text;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CarpenterBlockModelProvider extends PollinatedBlockModelGenerator {
    public static final ModelTemplate chestTemplate = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_base")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate chestTemplateLeft = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_left_base")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate chestTemplateRight = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_right_base")), Optional.empty(), TextureSlot.TEXTURE);

    public static final ModelTemplate chestTemplateLid = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_lid")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate chestTemplateLeftLid = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_left_lid")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate chestTemplateRightLid = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_right_lid")), Optional.empty(), TextureSlot.TEXTURE);

    public static final ModelTemplate chestTemplateKnob = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_knob")), Optional.empty(), TextureSlot.TEXTURE);

    public static final ModelTemplate chestItemTemplate = new ModelTemplate(Optional.of(Carpenter.carpenter("item/template_chest")), Optional.empty(), TextureSlot.PARTICLE);

    public CarpenterBlockModelProvider(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput) {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    @Override
    public void run() {
        this.createBookshelf(CarpenterBlocks.SPRUCE_BOOKSHELF.get(), Blocks.SPRUCE_PLANKS);
        this.createBookshelf(CarpenterBlocks.BIRCH_BOOKSHELF.get(), Blocks.BIRCH_PLANKS);
        this.createBookshelf(CarpenterBlocks.JUNGLE_BOOKSHELF.get(), Blocks.JUNGLE_PLANKS);
        this.createBookshelf(CarpenterBlocks.ACACIA_BOOKSHELF.get(), Blocks.ACACIA_PLANKS);
        this.createBookshelf(CarpenterBlocks.DARK_OAK_BOOKSHELF.get(), Blocks.DARK_OAK_PLANKS);
        this.createBookshelf(CarpenterBlocks.CRIMSON_BOOKSHELF.get(), Blocks.CRIMSON_PLANKS);
        this.createBookshelf(CarpenterBlocks.WARPED_BOOKSHELF.get(), Blocks.WARPED_PLANKS);

        this.createChestBase(CarpenterBlocks.OAK_CHEST.get(), Blocks.OAK_PLANKS);
        this.createChestLid(CarpenterBlocks.OAK_CHEST.get());
        this.createDefaultChestKnob(CarpenterBlocks.OAK_CHEST.get());

        this.createChestBase(CarpenterBlocks.DARK_OAK_CHEST.get(), Blocks.DARK_OAK_PLANKS);
        this.createChestLid(CarpenterBlocks.DARK_OAK_CHEST.get());
        this.createDefaultChestKnob(CarpenterBlocks.DARK_OAK_CHEST.get());

        this.createChestBase(CarpenterBlocks.SPRUCE_CHEST.get(), Blocks.SPRUCE_PLANKS);
        this.createChestLid(CarpenterBlocks.SPRUCE_CHEST.get());

        this.createChestBase(CarpenterBlocks.BIRCH_CHEST.get(), Blocks.BIRCH_PLANKS);
        this.createChestLid(CarpenterBlocks.BIRCH_CHEST.get());

        this.createChestBase(CarpenterBlocks.CRIMSON_CHEST.get(), Blocks.CRIMSON_PLANKS);
        this.createChestLid(CarpenterBlocks.CRIMSON_CHEST.get());

        this.createChestBase(CarpenterBlocks.WARPED_CHEST.get(), Blocks.WARPED_PLANKS);
        this.createChestLid(CarpenterBlocks.WARPED_CHEST.get());

        this.createChestBase(CarpenterBlocks.ACACIA_CHEST.get(), Blocks.ACACIA_PLANKS);
        this.createChestLid(CarpenterBlocks.ACACIA_CHEST.get());
    }

    private void createBookshelf(Block bookshelf, Block planks) {
        TextureMapping textureMapping = TextureMapping.column(TextureMapping.getBlockTexture(bookshelf), TextureMapping.getBlockTexture(planks));
        ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(bookshelf, textureMapping, this.getModelOutput());
        this.getBlockStateOutput().accept(createSimpleBlock(bookshelf, resourceLocation));
    }


    public ResourceLocation createWithSuffix(ModelTemplate template, Block modelBlock, String modelLocationSuffix, TextureMapping textureMapping, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        ResourceLocation resourceLocation = Registry.BLOCK.getKey(modelBlock);
        String var10002 = resourceLocation.getNamespace();
        String var10003 = resourceLocation.getPath();

        return template.create(new ResourceLocation(var10002, "block/" + var10003 + "/" + var10003 + modelLocationSuffix), textureMapping, modelOutput);
    }

    public void createChestBase(CarpenterChestBlock chest, Block particleBlock) {
        final CarpenterChestType carpenterChestType = chest.getType().get();

        new BlockEntityModelGenerator(ModelLocationUtils.getModelLocation(chest), particleBlock)
                .create(chest);

        final ResourceLocation itemModelLoc = chestItemTemplate.create(ModelLocationUtils.getModelLocation(chest.asItem()), TextureMapping.particle(particleBlock), getModelOutput());

        createWithSuffix(chestTemplate, chest, "_base", TextureMapping.defaultTexture(chest), getModelOutput());
        final ResourceLocation doubleMapping = TextureMapping.getBlockTexture(chest, "_double");
        createWithSuffix(chestTemplateLeft, chest, "_base_left", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
        createWithSuffix(chestTemplateRight, chest, "_base_right", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
    }


    public void createChestLid(CarpenterChestBlock chest) {
        createWithSuffix(chestTemplateLid, chest, "_lid", TextureMapping.defaultTexture(chest), getModelOutput());
        final ResourceLocation doubleMapping = TextureMapping.getBlockTexture(chest, "_double");
        createWithSuffix(chestTemplateLeftLid, chest, "_lid_left", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
        createWithSuffix(chestTemplateRightLid, chest, "_lid_right", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
    }

    public void createDefaultChestKnob(CarpenterChestBlock chest) {
        createWithSuffix(chestTemplateKnob, chest, "_knob", TextureMapping.defaultTexture(chest), getModelOutput());
    }
}
