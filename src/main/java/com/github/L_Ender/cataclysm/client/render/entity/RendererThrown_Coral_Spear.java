package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelCoral_Spear;
import com.github.L_Ender.cataclysm.client.model.entity.ModelVoid_Howitzer;
import com.github.L_Ender.cataclysm.entity.projectile.ThrownCoral_Spear_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Void_Howitzer_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class RendererThrown_Coral_Spear extends EntityRenderer<ThrownCoral_Spear_Entity> {

    private static final ResourceLocation VOID_HOWITZER_TEXTURES = new ResourceLocation("cataclysm:textures/entity/coral_spear.png");
    private final ModelCoral_Spear model = new ModelCoral_Spear();


    public RendererThrown_Coral_Spear(Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(ThrownCoral_Spear_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(bufferIn, this.model.renderType(this.getTextureLocation(entityIn)), false, entityIn.isFoil());
        this.model.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        
    }
    

    @Override
    public ResourceLocation getTextureLocation(ThrownCoral_Spear_Entity entity) {
        return VOID_HOWITZER_TEXTURES;
    }
}
