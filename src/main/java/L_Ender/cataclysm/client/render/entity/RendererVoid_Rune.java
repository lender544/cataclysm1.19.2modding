package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelVoid_Rune;
import L_Ender.cataclysm.client.render.CMRenderTypes;
import L_Ender.cataclysm.entity.projectile.Void_Rune_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RendererVoid_Rune extends EntityRenderer<Void_Rune_Entity> {
    private static final ResourceLocation VOID_RUNE = new ResourceLocation("cataclysm:textures/entity/void_rune.png");
    private final ModelVoid_Rune model = new ModelVoid_Rune();
    private final Random rnd = new Random();

    public RendererVoid_Rune(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(Void_Rune_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.scale(-2, -2, -2);
        matrixStackIn.translate(0.0D, -1.5F, 0.0D);
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F - entityIn.getYRot()));
        VertexConsumer VertexConsumer = bufferIn.getBuffer(CMRenderTypes.getBright(this.getTextureLocation(entityIn)));
        model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
        model.renderToBuffer(matrixStackIn, VertexConsumer, 210, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStackIn.popPose();
    }

    public Vec3 getRenderOffset(Void_Rune_Entity entityIn, float partialTicks) {
        if (entityIn.activateProgress == 10) {
            return super.getRenderOffset(entityIn, partialTicks);
        } else {
            double d0 = 0.02D;
            return new Vec3(this.rnd.nextGaussian() * d0, 0.0D, this.rnd.nextGaussian() * d0);
        }
    }

    protected int getBlockLightLevel(Void_Rune_Entity entityIn, BlockPos pos) {
        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(Void_Rune_Entity entity) {
        return VOID_RUNE;
    }
}
