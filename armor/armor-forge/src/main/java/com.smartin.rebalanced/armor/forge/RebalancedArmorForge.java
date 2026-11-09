package com.smartin.rebalanced.armor.forge;

import com.smartin.rebalanced.armor.RebalancedArmor;
import net.neoforged.fml.common.Mod;

@Mod(RebalancedArmor.MOD_ID)
public class RebalancedArmorForge {
    public RebalancedArmorForge() {
        RebalancedArmor.init();
    }
}
