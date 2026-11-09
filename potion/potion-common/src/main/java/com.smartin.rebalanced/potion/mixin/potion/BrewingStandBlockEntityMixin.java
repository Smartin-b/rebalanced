package com.smartin.rebalanced.potion.mixin.potion;

import com.smartin.rebalanced.potion.RebalancedPotion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {

    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 400)
    )
    private static int changeBrewTime(int original) {
        return RebalancedPotion.CONFIG.brewTime;
    }

    @Inject(
            method = "Lnet/minecraft/world/level/block/entity/BrewingStandBlockEntity;doBrew(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/NonNullList;)V",
            at = @At("RETURN")
    )
    private static void modifyMaxStackSize(Level level, BlockPos blockPos, NonNullList<ItemStack> nonNullList, CallbackInfo ci) {
        //level.updateNeighbourForOutputSignal(blockPos, level.getBlockState(blockPos).getBlock());
        level.setBlockAndUpdate(blockPos, level.getBlockState(blockPos));
        level.blockUpdated(blockPos, level.getBlockState(blockPos).getBlock());
    }
}