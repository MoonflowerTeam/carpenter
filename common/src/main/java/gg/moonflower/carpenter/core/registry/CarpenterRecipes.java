package gg.moonflower.carpenter.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import gg.moonflower.carpenter.common.recipe.LegacyChestRecipe;
import gg.moonflower.carpenter.core.Carpenter;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;

import java.util.function.Supplier;

public class CarpenterRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(Carpenter.MOD_ID, Registry.RECIPE_SERIALIZER_REGISTRY);

    public static final Supplier<SimpleRecipeSerializer<LegacyChestRecipe>> LEGACY_CHEST = REGISTRY.register("legacy_chest", () -> new SimpleRecipeSerializer<>(LegacyChestRecipe::new));
}
