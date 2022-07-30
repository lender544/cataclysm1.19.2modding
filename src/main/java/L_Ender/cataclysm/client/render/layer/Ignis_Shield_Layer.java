package L_Ender.cataclysm.client.render.layer;

import L_Ender.cataclysm.client.model.entity.ModelIgnis;
import L_Ender.cataclysm.client.render.entity.RendererIgnis;
import L_Ender.cataclysm.entity.Ignis_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.DyeableHorseArmorItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Ignis_Shield_Layer extends RenderLayer<Ignis_Entity, ModelIgnis> {

    private final ModelIgnis model = new ModelIgnis();

    private static final ResourceLocation IGNIS_SHIELD = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield.png");

    private static final ResourceLocation IGNIS_SOUL_SHIELD = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul_shield.png");

    private static final ResourceLocation IGNIS_SHIELD_CRACK1 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack1.png");

    private static final ResourceLocation IGNIS_SHIELD_CRACK2 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack2.png");

    private static final ResourceLocation IGNIS_SHIELD_CRACK3 = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_shield_crack3.png");

    public Ignis_Shield_Layer(RendererIgnis renderIgnis) {
        super(renderIgnis);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Ignis_Entity ignis, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation lvt_12_3_;
        if (ignis.getBossPhase() < 1) {
            lvt_12_3_ = IGNIS_SHIELD;
        } else {
            lvt_12_3_ = IGNIS_SOUL_SHIELD;
        }
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.setupAnim(ignis, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        VertexConsumer lvt_13_1_ = bufferIn.getBuffer(RenderType.entityCutoutNoCull(lvt_12_3_));
        this.model.renderToBuffer(matrixStackIn, lvt_13_1_, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        VertexConsumer lvt_13_2_ = ignis.getShieldDurability() >= 3 ? bufferIn.getBuffer(RenderType.entityCutoutNoCull(IGNIS_SHIELD_CRACK3)) : ignis.getShieldDurability() == 2 ? bufferIn.getBuffer(RenderType.entityCutoutNoCull(IGNIS_SHIELD_CRACK2)) : bufferIn.getBuffer(RenderType.entityCutoutNoCull(IGNIS_SHIELD_CRACK1));
        this.model.renderToBuffer(matrixStackIn, lvt_13_2_, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}