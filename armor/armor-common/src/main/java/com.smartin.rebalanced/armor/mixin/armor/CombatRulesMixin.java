package com.smartin.rebalanced.armor.mixin.armor;

import com.smartin.rebalanced.armor.RebalancedArmor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @Inject(
            method = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterAbsorb(Lnet/minecraft/world/entity/LivingEntity;FLnet/minecraft/world/damagesource/DamageSource;FF)F",
            at = @At("HEAD"),
            remap = true,
            cancellable = true
    )
    private static void test(LivingEntity entity, float damage, DamageSource damageSource, float armorValue, float armorToughness, CallbackInfoReturnable<Float> cir) {
        float dmgRessistance = RebalancedArmor.getArmorReduction(damage, armorValue, armorToughness);
        if (entity.level() instanceof ServerLevel serverLevel) {
            ItemStack weapon = damageSource.getWeaponItem();
            if (weapon != null) {
                dmgRessistance = Mth.clamp(EnchantmentHelper.modifyArmorEffectiveness(serverLevel, weapon, entity, damageSource, dmgRessistance), 0.0F, 1.0F);
            }
        }
        cir.setReturnValue((damage * dmgRessistance));
    }
}
