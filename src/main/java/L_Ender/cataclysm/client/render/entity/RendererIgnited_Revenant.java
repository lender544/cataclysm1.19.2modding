package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelIgnited_Revenant;
import L_Ender.cataclysm.client.model.entity.ModelNameless_Sorcerer;
import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import L_Ender.cataclysm.entity.Nameless_Sorcerer_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererIgnited_Revenant extends MobRenderer<Ignited_Revenant_Entity, ModelIgnited_Revenant> {

    private static final ResourceLocation IGNITED_REVENANT_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignited_revenant.png");

    public RendererIgnited_Revenant(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelIgnited_Revenant(), 0.5F);

    }
    @Override
    public ResourceLocation getTextureLocation(Ignited_Revenant_Entity entity) {
        return IGNITED_REVENANT_TEXTURES;
    }

    @Override
    protected void scale(Ignited_Revenant_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.1F, 1.1F, 1.1F);
    }

}
