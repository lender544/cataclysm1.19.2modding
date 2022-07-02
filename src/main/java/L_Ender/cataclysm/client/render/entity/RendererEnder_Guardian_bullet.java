package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelEnder_Guardian_Bullet;
import L_Ender.cataclysm.entity.projectile.Ender_Guardian_Bullet_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererEnder_Guardian_bullet extends EntityRenderer<Ender_Guardian_Bullet_Entity>
{
	private static final ResourceLocation ENDER_GUARDIAN_TEXTURE = new ResourceLocation("cataclysm:textures/entity/shulkerbullet.png");
	private static final RenderType ENDER_GUARDIAN_RENDER_TYPE = RenderType.entityTranslucent(ENDER_GUARDIAN_TEXTURE);
	public ModelEnder_Guardian_Bullet model;

	public RendererEnder_Guardian_bullet(EntityRendererProvider.Context manager)
	{
		super(manager);
		this.model = new ModelEnder_Guardian_Bullet();
	}
	
	@Override
	protected int getBlockLightLevel(Ender_Guardian_Bullet_Entity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public void render(Ender_Guardian_Bullet_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
	{
		matrixStackIn.pushPose();
		float f = rotLerp(entityIn.yRotO, entityIn.getYRot(), partialTicks);
		float f1 = Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot());
		float f2 = (float) entityIn.tickCount + partialTicks;
		matrixStackIn.translate(0.0D, (double) 0.15F, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(f2 * 0.1F) * 180.0F));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.cos(f2 * 0.1F) * 180.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(f2 * 0.15F) * 360.0F));
		matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
		this.model.setupAnim(entityIn, 0.0F, 0.0F, 0.0F, f, f1);
		VertexConsumer VertexConsumer = bufferIn.getBuffer(this.model.renderType(ENDER_GUARDIAN_TEXTURE));
		this.model.renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.scale(1.5F, 1.5F, 1.5F);
		VertexConsumer VertexConsumer1 = bufferIn.getBuffer(ENDER_GUARDIAN_RENDER_TYPE);
		this.model.renderToBuffer(matrixStackIn, VertexConsumer1, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.15F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Ender_Guardian_Bullet_Entity entity)
	{
		return ENDER_GUARDIAN_TEXTURE;
	}
	
	/**
	 * A helper method to do some Math Magic
	 */
	private float rotLerp(float prevRotation, float rotation, float partialTicks)
	{
		float f;
		for(f = rotation - prevRotation; f < -180.0F; f += 360.0F)
		{
			;
		}

		while(f >= 180.0F)
		{
			f -= 360.0F;
		}

		return prevRotation + partialTicks * f;
	}
}