package com.smartin.rebalanced.armor.mixin.armor;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    private static final ResourceLocation ARMOR_EMPTY_SPRITE = ResourceLocation.withDefaultNamespace("hud/armor_empty");
    private static final ResourceLocation ARMOR_HALF_SPRITE = ResourceLocation.withDefaultNamespace("hud/armor_half");
    private static final ResourceLocation ARMOR_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/armor_full");

    @Inject(
            method = "Lnet/minecraft/client/gui/Gui;renderArmor(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIII)V",
            at = @At("HEAD"),
            remap = true,
            cancellable = true
    )
    private static void test(GuiGraphics guiGraphics, Player player, int y, int heartRows, int height, int x, CallbackInfo ci) {
        int armorValue = player.getArmorValue();
        if (armorValue > 0) {
            RenderSystem.enableBlend();
            int targetRows = (int) Math.floor((double) (armorValue - 1) / 10);
            for (int row = 0; row < targetRows; row++) {
                int startOffset = y - (heartRows - 1 + row) * height - 10;
                for (int collumn = 0; collumn < 10; ++collumn) {
                    int xPos = x + collumn * 8;
                    if (collumn * 2 + 1 + (row * 20) < armorValue) {
                        guiGraphics.blitSprite(ARMOR_FULL_SPRITE, xPos, startOffset, 9, 9);
                    }

                    if (collumn * 2 + 1 + (row * 20) == armorValue) {
                        guiGraphics.blitSprite(ARMOR_HALF_SPRITE, xPos, startOffset, 9, 9);
                    }

                    if (collumn * 2 + 1 + (row * 20) > armorValue) {
                        guiGraphics.blitSprite(ARMOR_EMPTY_SPRITE, xPos, startOffset, 9, 9);
                    }
                }
            }

            RenderSystem.disableBlend();
        }
    }
}
