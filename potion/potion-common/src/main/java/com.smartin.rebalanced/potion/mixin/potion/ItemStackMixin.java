package com.smartin.rebalanced.potion.mixin.potion;

import com.smartin.rebalanced.potion.RebalancedPotion;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(
            method = "getMaxStackSize",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.is(Items.POTION) && stack.getComponentsPatch().get(DataComponents.MAX_STACK_SIZE) == null) {
            cir.setReturnValue(RebalancedPotion.CONFIG.drinkAblePotionStackSize);
        }
        if (stack.is(Items.SPLASH_POTION) && stack.getComponentsPatch().get(DataComponents.MAX_STACK_SIZE) == null) {
            cir.setReturnValue(RebalancedPotion.CONFIG.splashPotionStackSize);
        }
        if (stack.is(Items.LINGERING_POTION) && stack.getComponentsPatch().get(DataComponents.MAX_STACK_SIZE) == null) {
            cir.setReturnValue(RebalancedPotion.CONFIG.lingeringPotionStackSize);
        }
    }
}