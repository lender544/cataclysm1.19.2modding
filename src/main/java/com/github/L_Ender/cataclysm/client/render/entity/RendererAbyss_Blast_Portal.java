package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelAbyss_Blast_Portal;
import com.github.L_Ender.cataclysm.client.model.entity.ModelWither_Missile;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.Abyss_Blast_Portal_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RendererAbyss_Blast_Portal extends EntityRenderer<Abyss_Blast_Portal_Entity> {

	private static final ResourceLocation PORTAL = new ResourceLocation("cataclysm:textures/entity/leviathan/portal/abyss_blast_portal.png");
	public ModelAbyss_Blast_Portal model;

	public RendererAbyss_Blast_Portal(EntityRendererProvider.Context manager)
	{
		super(manager);
		this.model = new ModelAbyss_Blast_Portal();
	}

	@Override
	protected int getBlockLightLevel(Abyss_Blast_Portal_Entity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public void render(Abyss_Blast_Portal_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.scale(-1.5F, -1.5F, 1.5F);
		float f = Mth.lerp(partialTicks, entityIn.getYRot(), entityIn.yRotO);
		float f1 = Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot());
		VertexConsumer vertexconsumer = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
		this.model.setupAnim(entityIn, 0.0F, 0.0F, entityIn.tickCount, f, f1);
		this.model.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Abyss_Blast_Portal_Entity entity)
	{
		return PORTAL;
	}
}