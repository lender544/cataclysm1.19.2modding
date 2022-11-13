package L_Ender.cataclysm.client.render.entity;


import L_Ender.cataclysm.client.model.entity.ModelThe_Harbinger;
import L_Ender.cataclysm.client.render.layer.Item_Layer;
import L_Ender.cataclysm.client.render.layer.The_Harbinger_Layer;
import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
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

    public RendererThe_Harbinger(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelThe_Harbinger(), 1.0F);
        this.addLayer(new The_Harbinger_Layer(this));
        this.addLayer(new Item_Layer<>(this, getModel().nether_star, Items.NETHER_STAR.getDefaultInstance(), ItemTransforms.TransformType.GROUND));
    }

    @Override
    public ResourceLocation getTextureLocation(The_Harbinger_Entity entity) {
        return HARBINGER_TEXTURES;
    }


    public Vec3 getRenderOffset(The_Harbinger_Entity entityIn, float partialTicks) {
        if (entityIn.getAnimation() == The_Harbinger_Entity.DEATHLASER_ANIMATION && entityIn.getAnimationTick() >= 27 && entityIn.getAnimationTick() <= 48) {
            double d0 = 0.05D;
            return new Vec3(this.rnd.nextGaussian() * d0, 0.0D, this.rnd.nextGaussian() * d0);
        } else {
            return super.getRenderOffset(entityIn, partialTicks);
        }
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