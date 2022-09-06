package gg.moonflower.carpenter.common.recipe;

import gg.moonflower.carpenter.core.registry.CarpenterRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LegacyChestRecipe extends CustomRecipe {

    public LegacyChestRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public boolean matches(CraftingContainer container, @NotNull Level level) {
        ItemStack firstStack = container.getItem(0);
        boolean mixed = false;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = container.getItem(i);

            // Check if the center is empty
            if (i == 4) {
                if (!stack.isEmpty())
                    return false;
                else continue;
            }

            // Check if the item is planks
            if (stack.isEmpty() || !stack.is(ItemTags.PLANKS))
                return false;

            // We found a mixed recipe!
            if (!ItemStack.isSame(firstStack, stack))
                mixed = true;
        }

        return mixed;
    }

    @Override
    public ItemStack assemble(@NotNull CraftingContainer container) {
        return new ItemStack(Items.CHEST);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CarpenterRecipes.LEGACY_CHEST.get();
    }
}
