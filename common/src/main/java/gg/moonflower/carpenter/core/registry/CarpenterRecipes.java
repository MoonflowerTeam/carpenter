package gg.moonflower.carpenter.core.registry;

import gg.moonflower.carpenter.common.recipe.LegacyChestRecipe;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;

import java.util.function.Supplier;

public class CarpenterRecipes {
    public static final PollinatedRegistry<RecipeSerializer<?>> REGISTRY = PollinatedRegistry.create(Registry.RECIPE_SERIALIZER, Carpenter.MOD_ID);

    public static final Supplier<SimpleRecipeSerializer<LegacyChestRecipe>> LEGACY_CHEST = REGISTRY.register("legacy_chest", () -> new SimpleRecipeSerializer<>(LegacyChestRecipe::new));
}
