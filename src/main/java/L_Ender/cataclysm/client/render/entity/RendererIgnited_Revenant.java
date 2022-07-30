package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelIgnited_Revenant;
import L_Ender.cataclysm.client.model.entity.ModelNameless_Sorcerer;
import L_Ender.cataclysm.client.render.CMRenderTypes;
import L_Ender.cataclysm.client.render.layer.LayerGenericGlowing;
import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import L_Ender.cataclysm.entity.Nameless_Sorcerer_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererIgnited_Revenant extends MobRenderer<Ignited_Revenant_Entity, ModelIgnited_Revenant> {

    private static final ResourceLocation IGNITED_REVENANT_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignited_revenant.png");
    private static final ResourceLocation IGNITED_REVENANT_LAYER_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignited_revenant_layer.png");

    public RendererIgnited_Revenant(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelIgnited_Revenant(), 0.5F);
        this.addLayer(new Ignited_Revenant_GlowLayer(this));

    }
    @Override
    public ResourceLocation getTextureLocation(Ignited_Revenant_Entity entity) {
        return IGNITED_REVENANT_TEXTURES;
    }

    @Override
    protected void scale(Ignited_Revenant_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.1F, 1.1F, 1.1F);
    }

    static class Ignited_Revenant_GlowLayer extends RenderLayer<Ignited_Revenant_Entity, ModelIgnited_Revenant> {
        public Ignited_Revenant_GlowLayer(RendererIgnited_Revenant p_i50928_1_) {
            super(p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Ignited_Revenant_Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(CMRenderTypes.getFlickering(IGNITED_REVENANT_LAYER_TEXTURES, 0));
            float alpha = 0.5F + (Mth.cos(ageInTicks * 0.2F) + 1F) * 0.2F;
            this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 240, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, alpha);

        }
    }

}
