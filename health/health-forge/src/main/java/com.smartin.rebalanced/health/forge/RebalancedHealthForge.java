package com.smartin.rebalanced.health.forge;

import com.smartin.rebalanced.health.RebalancedHealth;
import net.neoforged.fml.common.Mod;

@Mod(RebalancedHealth.MOD_ID)
public class RebalancedHealthForge {
    public RebalancedHealthForge() {
        RebalancedHealth.init();
    }
}
