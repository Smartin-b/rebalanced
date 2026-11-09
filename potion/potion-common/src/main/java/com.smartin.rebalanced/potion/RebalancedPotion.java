package com.smartin.rebalanced.potion;

import com.redpxnda.nucleus.config.ConfigBuilder;
import com.redpxnda.nucleus.config.ConfigManager;
import com.redpxnda.nucleus.config.ConfigType;

public class RebalancedPotion {
    public static final String MOD_ID = "rebalanced_potion";
    public static PotionConfig CONFIG = new PotionConfig();

    static {
        var config = ConfigManager.register(ConfigBuilder
                .automatic(PotionConfig.class)
                .id(MOD_ID + ":config")
                .fileLocation("rebalanced/potion.json")
                .type(ConfigType.SERVER_CLIENT_SYNCED)
                .automaticScreen()
                .creator(PotionConfig::new)
                .updateListener((c) -> {
                    CONFIG = c;
                }));
        CONFIG = config.getInstance();
    }

    public static void init() {
    }
}
