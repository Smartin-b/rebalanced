package com.smartin.rebalanced.health.fabric;

import com.smartin.rebalanced.health.RebalancedHealth;
import net.fabricmc.api.ModInitializer;

public class RebalancedHealthFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RebalancedHealth.init();
    }
}
