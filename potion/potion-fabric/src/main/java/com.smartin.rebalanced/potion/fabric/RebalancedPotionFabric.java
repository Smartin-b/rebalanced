package com.smartin.rebalanced.potion.fabric;

import com.smartin.rebalanced.potion.RebalancedPotion;
import net.fabricmc.api.ModInitializer;

public class RebalancedPotionFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RebalancedPotion.init();
    }
}
