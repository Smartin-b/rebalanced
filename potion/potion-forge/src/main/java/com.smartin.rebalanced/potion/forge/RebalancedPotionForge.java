package com.smartin.rebalanced.potion.forge;

import com.smartin.rebalanced.potion.RebalancedPotion;
import net.neoforged.fml.common.Mod;

@Mod(RebalancedPotion.MOD_ID)
public class RebalancedPotionForge {
    public RebalancedPotionForge() {
        RebalancedPotion.init();
    }
}
