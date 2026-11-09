package com.smartin.rebalanced.health;

import com.redpxnda.nucleus.config.ConfigBuilder;
import com.redpxnda.nucleus.config.ConfigManager;
import com.redpxnda.nucleus.config.ConfigType;

public class RebalancedHealth {
    public static final String MOD_ID = "rebalanced_health";
    public static HealthConfig CONFIG = new HealthConfig();

    public static void init() {
        ConfigManager.register(ConfigBuilder
                .automatic(HealthConfig.class)
                .id(MOD_ID + ":config")
                .fileLocation("rebalanced/armor.json")
                .type(ConfigType.SERVER_CLIENT_SYNCED)
                .automaticScreen()
                .creator(HealthConfig::new)
                .updateListener((c) -> {
                    CONFIG = c;
                }));
    }
}
