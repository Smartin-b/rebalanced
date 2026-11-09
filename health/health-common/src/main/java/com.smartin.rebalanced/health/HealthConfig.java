package com.smartin.rebalanced.health;

import com.redpxnda.nucleus.codec.auto.AutoCodec;
import com.redpxnda.nucleus.codec.auto.ConfigAutoCodec;
import com.redpxnda.nucleus.util.Comment;

@ConfigAutoCodec.ConfigClassMarker
public class HealthConfig {
    @Comment("""
                How many ticks the player is considered in combat after being hit by a mob
                """)
    @AutoCodec.Name("in_combat_time_tick")
    public int outOfCombatTime = 20 * 20;

    @Comment("""
                How many ticks the player is considered in combat after being hit by a mob
                """)
    @AutoCodec.Name("foodRegenInCombatScale")
    public float inCombatFoodRegenPercent = 0.5f;

    @Comment("""
                How many ticks the player is considered in combat after being hit by a mob
                """)
    @AutoCodec.Name("foodRegenOutCombatScale")
    public float outCombatFoodRegenPercent = 1.0f;
}
