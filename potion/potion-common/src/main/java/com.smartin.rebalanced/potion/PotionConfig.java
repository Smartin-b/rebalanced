package com.smartin.rebalanced.potion;

import com.redpxnda.nucleus.codec.auto.AutoCodec;
import com.redpxnda.nucleus.codec.auto.ConfigAutoCodec;
import com.redpxnda.nucleus.util.Comment;

@ConfigAutoCodec.ConfigClassMarker
public class PotionConfig {
    @AutoCodec.Name("linger_potion_stack_size")
    public int lingeringPotionStackSize = 4;
    @AutoCodec.Name("splash_potion_stack_size")
    public int splashPotionStackSize = 4;
    @AutoCodec.Name("drinkable_potion_stack_size")
    public int drinkAblePotionStackSize = 16;
    @Comment("""
                How long it takes to brew a Potion
                400 is vanilla (20s)
                100 is the mod default (5s)
                """)
    @AutoCodec.Name("brew_time_tick")
    public int brewTime = 100;
}
