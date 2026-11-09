package com.smartin.rebalanced.health.mixin.health;

import com.smartin.rebalanced.health.RebalancedHealth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V"))
    private void onNaturalRegen(Player instance, float v) {
        if (instance.tickCount - instance.getLastHurtByMobTimestamp() > RebalancedHealth.CONFIG.outOfCombatTime) {
            instance.heal(v * RebalancedHealth.CONFIG.outCombatFoodRegenPercent);
        } else {
            instance.heal(v * RebalancedHealth.CONFIG.inCombatFoodRegenPercent);
        }
    }
}