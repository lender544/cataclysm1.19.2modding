package L_Ender.cataclysm.client.render.entity;


import L_Ender.cataclysm.client.model.entity.ModelEnder_Guardian;
import L_Ender.cataclysm.client.render.layer.Ender_Golem_Layer;
import L_Ender.cataclysm.client.render.layer.Ender_Guardian_Layer;
import L_Ender.cataclysm.client.render.layer.LayerGenericGlowing;
import L_Ender.cataclysm.entity.Ender_Golem_Entity;
import L_Ender.cataclysm.entity.Ender_Guardian_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererEnder_Guardian extends MobRenderer<Ender_Guardian_Entity, ModelEnder_Guardian> {

    private static final ResourceLocation ENDER_GUARDIAN_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ender_guardian.png");

    public RendererEnder_Guardian(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelEnder_Guardian(), 1.5F);
        this.addLayer(new Ender_Guardian_Layer(this));

    }
    @Override
    public ResourceLocation getTextureLocation(Ender_Guardian_Entity entity) {
        return ENDER_GUARDIAN_TEXTURES;
    }

    @Override
    protected void scale(Ender_Guardian_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1F, 1F, 1F);
    }

    @Override
    protected float getFlipDegrees(Ender_Guardian_Entity entity) {
        return 0;
    }

}

