package com.smartin.rebalanced.armor.fabric;

import com.smartin.rebalanced.armor.RebalancedArmor;
import net.fabricmc.api.ModInitializer;

public class RebalancedArmorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RebalancedArmor.init();
    }
}
