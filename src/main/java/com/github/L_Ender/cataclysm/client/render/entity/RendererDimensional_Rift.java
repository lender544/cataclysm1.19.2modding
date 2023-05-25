package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.Dimensional_Rift_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererDimensional_Rift extends EntityRenderer<Dimensional_Rift_Entity> {
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("cataclysm:textures/entity/leviathan/dimensional_rift/dimensional_rift_idle1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("cataclysm:textures/entity/leviathan/dimensional_rift/dimensional_rift_idle2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("cataclysm:textures/entity/leviathan/dimensional_rift/dimensional_rift_idle3.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation("cataclysm:textures/entity/leviathan/dimensional_rift/dimensional_rift_idle4.png");
    private static final ResourceLocation[] TEXTURE_PROGRESS = new ResourceLocation[4];


    public RendererDimensional_Rift(EntityRendererProvider.Context mgr) {
        super(mgr);
        for(int i = 0; i < 4; i++) {
            TEXTURE_PROGRESS[i] = new ResourceLocation("cataclysm:textures/entity/leviathan/dimensional_rift/dimensional_rift_grow_" + i + ".png");
        }
    }

    public void render(Dimensional_Rift_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        ResourceLocation tex;
        if(entityIn.getLifespan() < 160){
            tex = getGrowingTexture((int) ((entityIn.getLifespan() * 0.05F) % 20));
        }else if(entityIn.tickCount < 160){
            tex = getGrowingTexture((int) ((entityIn.tickCount * 0.05F) % 20));
        }else{
            tex = getIdleTexture(entityIn.tickCount % 9);
        }
        matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.scale(4, 4, 4);
        PoseStack.Pose posestack$pose = matrixStackIn.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = bufferIn.getBuffer(CMRenderTypes.getfullBright(tex));
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
        p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float)p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_114096_, (float)p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(Dimensional_Rift_Entity entity) {
        return TEXTURE_1;
    }

    public ResourceLocation getIdleTexture(int age) {
        if (age < 3) {
            return TEXTURE_1;
        } else if (age < 6) {
            return TEXTURE_2;
        } else if (age < 10) {
            return TEXTURE_3;
        } else {
            return TEXTURE_4;
        }
    }

    public ResourceLocation getGrowingTexture(int age) {
        return TEXTURE_PROGRESS[Mth.clamp(age/2, 0, 3)];
    }
}
