package L_Ender.cataclysm.client.render.layer;

import L_Ender.cataclysm.client.model.entity.ModelIgnis;
import L_Ender.cataclysm.client.render.entity.RendererIgnis;
import L_Ender.cataclysm.entity.Ignis_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class Ignis_Shield_Crack_Layer extends RenderLayer<Ignis_Entity, ModelIgnis> {

    private static final ResourceLocation IGNIS_SHIELD_CRACK1 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack1.png");

    private static final ResourceLocation IGNIS_SHIELD_CRACK2 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack2.png");

    private static final ResourceLocation IGNIS_SHIELD_CRACK3 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack3.png");

    public Ignis_Shield_Crack_Layer(RendererIgnis renderIn) {
        super(renderIn);
    }
    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Ignis_Entity ignis, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (ignis.getShieldDurability() == 1) {
            renderColoredCutoutModel(this.getParentModel(), IGNIS_SHIELD_CRACK1, matrixStackIn, bufferIn, packedLightIn, ignis, 1.0F, 1.0F, 1.0F);
        }
        if (ignis.getShieldDurability() == 2) {
            renderColoredCutoutModel(this.getParentModel(), IGNIS_SHIELD_CRACK2, matrixStackIn, bufferIn, packedLightIn, ignis, 1.0F, 1.0F, 1.0F);
        }
        if (ignis.getShieldDurability() >= 3) {
            renderColoredCutoutModel(this.getParentModel(), IGNIS_SHIELD_CRACK3, matrixStackIn, bufferIn, packedLightIn, ignis, 1.0F, 1.0F, 1.0F);
        }
    }
}