package com.github.L_Ender.cataclysm.jei;

import com.github.L_Ender.cataclysm.cataclysm;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class AltarOfAmethystDrawable implements IDrawable {

    private static final ResourceLocation TEXTURE = new ResourceLocation(cataclysm.MODID, "textures/gui/altar_of_amethyst_jei.png");
    @Override
    public int getWidth() {
        return 125;
    }

    @Override
    public int getHeight() {
        return 59;
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = xOffset;
        int j = yOffset;
        GuiComponent.blit(poseStack, i, j, 0, 0, 125, 59, 256, 256);
    }
}
