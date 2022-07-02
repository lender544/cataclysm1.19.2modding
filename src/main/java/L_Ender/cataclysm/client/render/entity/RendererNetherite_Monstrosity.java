package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelNetherite_Monstrosity;
import L_Ender.cataclysm.client.render.layer.Netherite_Monstrosity_Layer;
import L_Ender.cataclysm.entity.Netherite_Monstrosity_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererNetherite_Monstrosity extends MobRenderer<Netherite_Monstrosity_Entity, ModelNetherite_Monstrosity> {

    private static final ResourceLocation NETHER_MONSTROSITY_TEXTURES = new ResourceLocation("cataclysm:textures/entity/netherite_monstrosity.png");

    public RendererNetherite_Monstrosity(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelNetherite_Monstrosity(), 2.5F);
        this.addLayer(new Netherite_Monstrosity_Layer(this));

    }
    @Override
    public ResourceLocation getTextureLocation(Netherite_Monstrosity_Entity entity) {
        return NETHER_MONSTROSITY_TEXTURES;
    }

    @Override
    protected void scale(Netherite_Monstrosity_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1F, 1F, 1F);
    }

    @Override
    protected float getFlipDegrees(Netherite_Monstrosity_Entity entity) {
        return 0;
    }

}
