package com.smartin.rebalanced.armor;

import com.redpxnda.nucleus.codec.auto.AutoCodec;
import com.redpxnda.nucleus.codec.auto.ConfigAutoCodec;
import com.redpxnda.nucleus.util.Comment;

@ConfigAutoCodec.ConfigClassMarker
public class ArmorConfig {
    @Comment("""
                Armor Calculation
                calculates the % reduction of the armor
                d = is incoming damage
                a = is raw armor points
                t = armor toughness
                vanilla = (1 - (MIN(MAX(a - d / (2 + t / 4), a * 0.2), 20) / 25))
                flatten armor curve mod = d*MAX(15/(15+a+MIN(d,t)),0.1)
                https://www.curseforge.com/minecraft/mc-mods/armor-curve
                
                default this = 1 - 1 / (1 + 4 * (a ^ 1.2 * 0.4) / (a ^ 1.2 * 0.4 + 24))
                this ensures diminishing returns past netherite
                it ensures early game armors are far more effective,
                while slightly reducing netherite and diamond
                iron is buffed
                and everything below is significantly buffed
                diamond has 8 armor toughness
                netherite has 12
                """)
    @AutoCodec.Name("armor_calculation")
    public String armorCalculation = "1 - 1 / (1 + 4 * (a ^ 1.2 * 0.4) / (a ^ 1.2 * 0.4 + 24))";
}
