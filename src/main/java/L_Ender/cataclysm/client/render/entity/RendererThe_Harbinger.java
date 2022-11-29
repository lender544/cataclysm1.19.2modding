package L_Ender.cataclysm.client.render.entity;


import L_Ender.cataclysm.client.model.entity.ModelThe_Harbinger;
import L_Ender.cataclysm.client.render.RenderUtils;
import L_Ender.cataclysm.client.render.layer.Item_Layer;
import L_Ender.cataclysm.client.render.layer.The_Harbinger_Item_Layer;
import L_Ender.cataclysm.client.render.layer.The_Harbinger_Layer;
import L_Ender.cataclysm.entity.Ignis_Entity;
import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererThe_Harbinger extends MobRenderer<The_Harbinger_Entity, ModelThe_Harbinger> {

    private static final ResourceLocation HARBINGER_TEXTURES = new ResourceLocation("cataclysm:textures/entity/harbinger/the_harbinger.png");
    private final RandomSource rnd = RandomSource.create();
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    public RendererThe_Harbinger(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelThe_Harbinger(), 1.0F);
        this.addLayer(new The_Harbinger_Layer(this));
        this.addLayer(new The_Harbinger_Item_Layer(this, getModel().nether_star, Items.NETHER_STAR.getDefaultInstance(), ItemTransforms.TransformType.GROUND));
    }

    @Override
    public ResourceLocation getTextureLocation(The_Harbinger_Entity entity) {
        return HARBINGER_TEXTURES;
    }


    public Vec3 getRenderOffset(The_Harbinger_Entity entityIn, float partialTicks) {
        if (entityIn.getAnimation() == The_Harbinger_Entity.DEATHLASER_ANIMATION && entityIn.getAnimationTick() >= 27 && entityIn.getAnimationTick() <= 48 || entityIn.getAnimation() == The_Harbinger_Entity.DEATH_ANIMATION) {
            double d0 = 0.05D;
            return new Vec3(this.rnd.nextGaussian() * d0, 0.0D, this.rnd.nextGaussian() * d0);
        } else {
            return super.getRenderOffset(entityIn, partialTicks);
        }
    }

    @Override
    public void render(The_Harbinger_Entity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        if (entity.deathTime > 0) {
            float f5 = ((float)entity.deathTime + partialTicks) / 144;
            float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
            RandomSource randomsource = RandomSource.create(432L);
            VertexConsumer vertexconsumer2 = bufferIn.getBuffer(RenderType.lightning());
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.0D, 1.8D, 0.0D);

            for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 30.0F; ++i) {
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F + f5 * 90.0F));
                float f3 = randomsource.nextFloat() * 5.0F + 5F + f7 * 10.0F;
                float f4 = randomsource.nextFloat() * 0.5F + 1F + f7 * 2.0F;
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
        p_114220_.vertex(p_114221_, 0.0F, 0.0F, 0.0F).color(255, 51, 0, p_114222_).endVertex();
    }

    private static void vertex2(VertexConsumer p_114215_, Matrix4f p_114216_, float p_114217_, float p_114218_) {
        p_114215_.vertex(p_114216_, -HALF_SQRT_3 * p_114218_, p_114217_, -0.5F * p_114218_).color(255, 51, 0, 0).endVertex();
    }

    private static void vertex3(VertexConsumer p_114224_, Matrix4f p_114225_, float p_114226_, float p_114227_) {
        p_114224_.vertex(p_114225_, HALF_SQRT_3 * p_114227_, p_114226_, -0.5F * p_114227_).color(255, 51, 0, 0).endVertex();
    }

    private static void vertex4(VertexConsumer p_114229_, Matrix4f p_114230_, float p_114231_, float p_114232_) {
        p_114229_.vertex(p_114230_, 0.0F, p_114231_, 1.0F * p_114232_).color(255, 51, 0, 0).endVertex();
    }

    protected void scale(The_Harbinger_Entity entityIn, PoseStack p_116440_, float p_116441_) {
        float f = 2.0F;
        p_116440_.scale(f, f, f);
    }

    protected int getBlockLightLevel(The_Harbinger_Entity entityIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected float getFlipDegrees(The_Harbinger_Entity entity) {
        return 0;
    }

}