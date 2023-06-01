package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelDeepling;
import com.github.L_Ender.cataclysm.client.model.entity.ModelDeepling_Angler;
import com.github.L_Ender.cataclysm.client.render.layer.Deepling_Angler_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.Deepling_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.LayerDeeplingItem;
import com.github.L_Ender.cataclysm.client.render.layer.LayerDeepling_AnglerItem;
import com.github.L_Ender.cataclysm.entity.Deepling.Deepling_Angler_Entity;
import com.github.L_Ender.cataclysm.entity.Deepling.Deepling_Entity;
import com.github.L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererDeepling_Angler extends MobRenderer<Deepling_Angler_Entity, ModelDeepling_Angler> {

    private static final ResourceLocation SSAPBUG_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/deepling_angler.png");
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    public RendererDeepling_Angler(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelDeepling_Angler(), 0.7F);
        this.addLayer(new Deepling_Angler_Layer(this));
        this.addLayer(new LayerDeepling_AnglerItem(this, renderManagerIn.getItemInHandRenderer()));

    }
    @Override
    public ResourceLocation getTextureLocation(Deepling_Angler_Entity entity) {
        return SSAPBUG_TEXTURES;
    }

    @Override
    protected void scale(Deepling_Angler_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }


    @Override
    public void render(Deepling_Angler_Entity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        if (entity.getAnimation() == Deepling_Angler_Entity.DEEPLING_BLIND && entity.getAnimationTick() >18 && entity.getAnimationTick() <47) {
            float f5 = ((float)entity.getAnimationTick() + partialTicks) / 144;
            float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
            RandomSource randomsource = RandomSource.create(432L);
            VertexConsumer vertexconsumer2 = bufferIn.getBuffer(RenderType.lightning());
            matrixStackIn.pushPose();

            double theta = (entity.yBodyRot) * (Math.PI / 180);
            theta += Math.PI / 2;
            double vecX = Math.cos(theta);
            double vecZ = Math.sin(theta);
            float w =  entity.isInWater() ? 0.3f : -0.8f;
            float h =  entity.isInWater() ? 3.0f : 2.8f;

            matrixStackIn.translate(vecX * w, h, vecZ * w);

            for(int i = 0; (float)i < 4; ++i) {
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F + f5 * 90.0F));
                float f3 = 2.75F;
                float f4 = 2.75F;
                Matrix4f matrix4f = matrixStackIn.last().pose();
                int j = (int)(255.0F * (1.0F - f7));
                vertex01(vertexconsumer2, matrix4f, j);
                vertex2(vertexconsumer2, matrix4f, f3, f4);
                vertex3(vertexconsumer2, matrix4f, f3, f4);
                vertex01(vertexconsumer2, matrix4f, j);
                vertex3(vertexconsumer2, matrix4f, f3, f4);
                vertex4(vertexconsumer2, matrix4f, f3, f4);
                vertex01(vertexconsumer2, matrix4f, j);
                vertex4(vertexconsumer2, matrix4f, f3, f4);
                vertex2(vertexconsumer2, matrix4f, f3, f4);
            }

            matrixStackIn.popPose();
        }

        matrixStackIn.popPose();

        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

    }


    private static void vertex01(VertexConsumer p_114220_, Matrix4f p_114221_, int p_114222_) {
        p_114220_.vertex(p_114221_, 0.0F, 0.0F, 0.0F).color(51, 255, 255, p_114222_).endVertex();
    }

    private static void vertex2(VertexConsumer p_114215_, Matrix4f p_114216_, float p_114217_, float p_114218_) {
        p_114215_.vertex(p_114216_, -HALF_SQRT_3 * p_114218_, p_114217_, -0.5F * p_114218_).color(51, 255, 255, 0).endVertex();
    }

    private static void vertex3(VertexConsumer p_114224_, Matrix4f p_114225_, float p_114226_, float p_114227_) {
        p_114224_.vertex(p_114225_, HALF_SQRT_3 * p_114227_, p_114226_, -0.5F * p_114227_).color(51, 255, 255, 0).endVertex();
    }

    private static void vertex4(VertexConsumer p_114229_, Matrix4f p_114230_, float p_114231_, float p_114232_) {
        p_114229_.vertex(p_114230_, 0.0F, p_114231_, 1.0F * p_114232_).color(51, 255, 255, 0).endVertex();
    }


}