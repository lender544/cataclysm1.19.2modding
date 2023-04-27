package com.github.L_Ender.cataclysm.client.render.layer;

import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Leviathan;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Leviathan;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class The_Leviathan_Layer extends RenderLayer<The_Leviathan_Entity, ModelThe_Leviathan> {
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation("cataclysm:textures/entity/leviathan/the_leviathan_layer.png");
    public The_Leviathan_Layer(RendererThe_Leviathan renderIn) {
        super(renderIn);

    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, The_Leviathan_Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        float alpha = entity.getLight();
        RenderType eyes = RenderType.eyes(PULSATING_SPOTS_TEXTURE_1);
        VertexConsumer VertexConsumer = bufferIn.getBuffer(eyes);
        this.getParentModel().renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, alpha, alpha, alpha, 1.0f);

    }
}


