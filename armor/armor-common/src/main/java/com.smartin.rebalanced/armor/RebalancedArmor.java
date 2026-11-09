package com.smartin.rebalanced.armor;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;
import com.redpxnda.nucleus.config.ConfigBuilder;
import com.redpxnda.nucleus.config.ConfigManager;
import com.redpxnda.nucleus.config.ConfigType;
import net.minecraft.util.Mth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RebalancedArmor {
    public static final String MOD_ID = "rebalanced_armor";
    private static Expression ARMOR_EXPRESSION = new Expression("d * (1 - (MIN(MAX(a - d / (2 + t / 4), a * 0.2), 20) / 25))");
    private static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ConfigManager.register(ConfigBuilder
                .automatic(ArmorConfig.class)
                .id(MOD_ID + ":config")
                .fileLocation("rebalanced/armor.json")
                .type(ConfigType.SERVER_CLIENT_SYNCED)
                .automaticScreen()
                .creator(ArmorConfig::new)
                .updateListener((c) -> {
                    try {
                        Expression armor_math = new Expression(c.armorCalculation);
                        armor_math.validate();
                        float reduction = armor_math.and("d", 5)
                                .with("a", 5)
                                .with("t", 5)
                                .evaluate()
                                .getNumberValue()
                                .floatValue();
                        ARMOR_EXPRESSION = armor_math;
                    } catch (RuntimeException | ParseException | EvaluationException e) {
                        LOGGER.warn("Falling back to vanilla armor math!");
                        LOGGER.error("could not parse armor calculation!", e);
                    }
                }));
    }

    public static float getArmorReduction(float damage, float armor, float armorToughness) {
        try {
            float reduction = ARMOR_EXPRESSION
                    .with("d", damage)
                    .with("a", armor)
                    .with("t", armorToughness)
                    .evaluate()
                    .getNumberValue()
                    .floatValue();
            return Math.clamp(reduction, 0.0f, 1.0f);
        } catch (EvaluationException | ParseException e) {
            float i = 2.0F + armorToughness / 4.0F;
            float j = Mth.clamp(armor - damage / i, armor * 0.2F, 20.0F);
            return j / 25.0F;
        }
    }
}
