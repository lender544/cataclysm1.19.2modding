package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelLava_Bomb;
import L_Ender.cataclysm.entity.projectile.Lava_Bomb_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class RendererLava_Bomb extends EntityRenderer<Lava_Bomb_Entity> {

    private static final ResourceLocation FIRE_BOMB_TEXTURES = new ResourceLocation("cataclysm:textures/entity/fire_bomb.png");
    private final ModelLava_Bomb model = new ModelLava_Bomb();


    public RendererLava_Bomb(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(Lava_Bomb_Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(new Quaternion(new Vector3f(0, -1, 0), entityYaw, true));
        VertexConsumer VertexConsumer = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entityIn)));
        model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
        model.renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStackIn.popPose();
    }

    protected int getBlockLightLevel(Lava_Bomb_Entity entityIn, BlockPos pos) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(Lava_Bomb_Entity entity) {
        return FIRE_BOMB_TEXTURES;
    }
}
