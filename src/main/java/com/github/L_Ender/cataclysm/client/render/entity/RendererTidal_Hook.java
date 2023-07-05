package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelTidal_Hook;
import com.github.L_Ender.cataclysm.entity.projectile.Tidal_Hook_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class RendererTidal_Hook extends EntityRenderer<Tidal_Hook_Entity> {
	private final ModelTidal_Hook model = new ModelTidal_Hook();
	private static final ResourceLocation TEXTURE = new ResourceLocation( "cataclysm:textures/entity/tidal_hook.png");
	private static final ResourceLocation CHAIN_TEXTURE = new ResourceLocation( "cataclysm:textures/entity/tidal_hook_chain.png");
	private static final RenderType CHAIN_LAYER = RenderType.entitySmoothCutout(CHAIN_TEXTURE);

	public RendererTidal_Hook(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(Tidal_Hook_Entity entity, float yaw, float tickDelta, PoseStack matrices, MultiBufferSource provider, int light) {
		matrices.pushPose();
		matrices.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(tickDelta, entity.yRotO, entity.getYRot()) - 90.0F));
		matrices.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(tickDelta, entity.xRotO, entity.getXRot()) + 90.0F));

		VertexConsumer vertexConsumer = provider.getBuffer(this.model.renderType(getTextureLocation(entity)));
		model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrices.popPose();

		matrices.pushPose();
		Entity fromEntity = entity.getOwner();
		if(fromEntity instanceof Player player) {
			double startY = player.getY() + (player.getBbHeight() / 2.0D);
			float f2 = player.yBodyRot * Mth.DEG_TO_RAD;
			int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
			float d0 =  Mth.sin(f2);
			float d1 =  Mth.cos(f2);
			ItemStack itemstack = player.getMainHandItem();
			if (!itemstack.is(ModItems.TIDAL_CLAWS.get())) {
				i = -i;
			}
			float d2 = (float) (i * 0.25D);

			float distanceX = (float) ((float) (player.getX()) - d1 * d2 - d0 * 0.2D - entity.getX());
			float distanceY = (float) (startY - entity.getY());
			float distanceZ = (float) ((float) (player.getZ()) - d0 * d2 + d1 * 0.2D - entity.getZ());
			renderChain(distanceX, distanceY, distanceZ, tickDelta, entity.tickCount, matrices, provider, light);
		}
		matrices.popPose();

	}

	public void renderChain(float x, float y, float z, float tickDelta, int age, PoseStack stack, MultiBufferSource provider, int light) {
		float lengthXY = Mth.sqrt(x * x + z * z);
		float squaredLength = x * x + y * y + z * z;
		float length = Mth.sqrt(squaredLength);

		stack.pushPose();
		stack.mulPose(Vector3f.YP.rotation((float) (-Math.atan2(z, x)) - 1.5707964F));
		stack.mulPose(Vector3f.XP.rotation((float) (-Math.atan2(lengthXY, y)) - 1.5707964F));
		stack.mulPose(Vector3f.ZP.rotationDegrees(25));
		stack.pushPose();
		stack.translate(0.015, -0.2, 0);

		VertexConsumer vertexConsumer = provider.getBuffer(CHAIN_LAYER);
		float vertX1 = 0F;
		float vertY1 = 0.25F;
		float vertX2 = Mth.sin(6.2831855F) * 0.125F;
		float vertY2 = Mth.cos(6.2831855F) * 0.125F;
		float minU = 0F;
		float maxU = 0.1875F;
		float minV = 0.0F - ((float) age + tickDelta) * 0.01F;
		float maxV = Mth.sqrt(squaredLength) / 8F - ((float) age + tickDelta) * 0.01F;
		PoseStack.Pose entry = stack.last();
		Matrix4f matrix4f = entry.pose();
		Matrix3f matrix3f = entry.normal();

		vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0F).color(0, 0, 0, 255).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0F).color(0, 0, 0, 255).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();

		stack.popPose();
		stack.mulPose(Vector3f.ZP.rotationDegrees(90));
		stack.translate(-0.015, -0.2, 0);

		entry = stack.last();
		matrix4f = entry.pose();
		matrix3f = entry.normal();

		vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0F).color(0, 0, 0, 255).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0F).color(0, 0, 0, 255).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();

		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(Tidal_Hook_Entity entity) {
		return TEXTURE;
	}
}