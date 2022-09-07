package gg.moonflower.carpenter.core.mixin.fabric;

import gg.moonflower.carpenter.core.registry.CarpenterTags;
import gg.moonflower.pollen.mixinextras.injector.wrapoperation.Operation;
import gg.moonflower.pollen.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EnchantmentTableBlock.class, priority = 1100)
public class EnchantmentTableBlockMixin {

    @WrapOperation(method = "isValidBookShelf", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private static boolean allowCarpenterBookshelves(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.is(CarpenterTags.BOOKSHELVES);
    }
}
