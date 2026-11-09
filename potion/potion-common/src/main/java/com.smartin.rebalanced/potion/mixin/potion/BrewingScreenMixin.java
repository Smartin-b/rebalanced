package com.smartin.rebalanced.potion.mixin.potion;

import com.smartin.rebalanced.potion.RebalancedPotion;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BrewingStandScreen.class)
public class BrewingScreenMixin {

    @ModifyConstant(
        method = "Lnet/minecraft/client/gui/screens/inventory/BrewingStandScreen;renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V",
        constant = @Constant(floatValue = 400.0f)
    )
    private float changeBrewTime(float original) {
        return RebalancedPotion.CONFIG.brewTime;
    }
}