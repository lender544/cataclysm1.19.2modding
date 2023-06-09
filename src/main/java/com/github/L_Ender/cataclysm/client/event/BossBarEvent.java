package com.github.L_Ender.cataclysm.client.event;


import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import static net.minecraft.client.gui.GuiComponent.blit;

public class BossBarEvent {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(cataclysm.MODID, "textures/gui/boss_bar_frames.png");
    private static final ResourceLocation GUI_BARS_LOCATION = new ResourceLocation("textures/gui/bars.png");
    public static final Set<Mob> BOSSES = Collections.newSetFromMap(new WeakHashMap<>());

    @SubscribeEvent
    public static void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event){
        Minecraft minecraft = Minecraft.getInstance();
        if(CMConfig.custombossbar) {
            if (!BOSSES.isEmpty()) {
                int i = minecraft.getWindow().getGuiScaledWidth();
                for (Mob boss : BOSSES) {
                    if (event.getBossEvent().getId() == boss.getUUID()) {
                        event.setCanceled(true);
                        int k = i / 2 - 94;
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        //  RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);

                        drawBar(event.getPoseStack(), k, event.getY() - 2, boss);
                        Component itextcomponent = boss.getDisplayName();
                        int l = minecraft.font.width(itextcomponent);
                        int i1 = i / 2 - l / 2;
                        minecraft.font.drawShadow(event.getPoseStack(), itextcomponent, (float) i1, (float) event.getY() - 9, 16777215);
                        if (event.getY() >= minecraft.getWindow().getGuiScaledHeight() / 3) {
                            break;
                        }
                        event.setIncrement(12 + minecraft.font.lineHeight);
                    }
                }

            }
        }
    }

    private static void drawBar(PoseStack pPoseStack, int pX, int pY, Mob pEntity) {
        float percent = pEntity.getHealth() / pEntity.getMaxHealth();
        float f2 = (pEntity.getHealth() - ((pEntity.getMaxHealth()- pEntity.getHealth()))) / pEntity.getMaxHealth();
        int i = (int) (percent * 182.0F);
        if (pEntity instanceof Netherite_Monstrosity_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            blit(pPoseStack, pX + 3, pY + 2, 0, 20, 182, 5, 256, 256);
            if (i > 0) {
                blit(pPoseStack, pX + 3, pY + 3, 0, 26, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            blit(pPoseStack, pX , pY, 0, 0, 188, 9, 256, 256);
        }

        if (pEntity instanceof Ender_Guardian_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            blit(pPoseStack, pX + 3, pY + 2, 0, 50, 182, 5, 256, 256);
            if (i > 0) {
                blit(pPoseStack, pX + 3, pY + 3, 0, 56, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            blit(pPoseStack, pX , pY, 0, 9, 188, 9, 256, 256);
        }

        if (pEntity instanceof Ignis_Entity ignis) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            if(ignis.getBossPhase() > 0){
                blit(pPoseStack, pX + 3, pY + 2, 0, 10, 182, 5, 256, 256);
                if (i > 0) {
                    blit(pPoseStack, pX + 3, pY + 3, 0, 16, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(pPoseStack, pX , pY, 0, 36, 188, 9, 256, 256);
            }else{
                blit(pPoseStack, pX + 3, pY + 2, 0, 40, 182, 5, 256, 256);
                if (i > 0) {
                    blit(pPoseStack, pX + 3, pY + 3, 0, 46, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(pPoseStack, pX , pY, 0, 27, 188, 9, 256, 256);
            }
        }

        if (pEntity instanceof The_Harbinger_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            blit(pPoseStack, pX + 3, pY + 2, 0, 20, 182, 5, 256, 256);
            if (i > 0) {
                blit(pPoseStack, pX + 3, pY + 3, 0, 26, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            blit(pPoseStack, pX , pY, 0, 18, 188, 9, 256, 256);
        }

        if (pEntity instanceof The_Leviathan_Entity leviathan) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            if(leviathan.getMeltDown()){
                blit(pPoseStack, pX + 3, pY + 6, 0, 50, 182, 5, 256, 256);
                if (i > 0) {
                    blit(pPoseStack, pX + 3, pY + 7, 0, 56, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(pPoseStack, pX , pY, 0, 62, 188, 16, 256, 256);
            }else{
                blit(pPoseStack, pX + 3, pY + 5, 0, 50, 182, 5, 256, 256);
                if (i > 0) {
                    blit(pPoseStack, pX + 3, pY + 6, 0, 56, (int) (f2 * 182.0f), 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                blit(pPoseStack, pX , pY, 0, 45, 188, 13, 256, 256);
            }
        }

    }

    public static void addBoss(Mob mob){
        BOSSES.add(mob);
    }

    public static void removeBoss(Mob mob){
        BOSSES.remove(mob);
    }

    public static Set<Mob> getBosses(){
        return BOSSES;
    }

}
