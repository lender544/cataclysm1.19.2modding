package L_Ender.cataclysm.client.render.entity;


import L_Ender.cataclysm.client.model.entity.ModelIgnis;
import L_Ender.cataclysm.client.render.RenderUtils;
import L_Ender.cataclysm.client.render.layer.Ignis_Armor_Crack_Layer;
import L_Ender.cataclysm.client.render.layer.Ignis_Shield_Layer;
import L_Ender.cataclysm.entity.Ignis_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererIgnis extends MobRenderer<Ignis_Entity, ModelIgnis> {

    private static final ResourceLocation IGNIS_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis.png");

    private static final ResourceLocation IGNIS_SOUL_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul.png");

    public RendererIgnis(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelIgnis(), 1.0F);
        this.addLayer(new Ignis_Armor_Crack_Layer(this));
        this.addLayer(new Ignis_Shield_Layer(this));
    }
    @Override
    public ResourceLocation getTextureLocation(Ignis_Entity entity) {
        return entity.getBossPhase() > 0 ? IGNIS_SOUL_TEXTURES : IGNIS_TEXTURES;
    }

    @Override
    public void render(Ignis_Entity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entity.getAnimation() == Ignis_Entity.HORIZONTAL_SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SWING_ATTACK_SOUL
                || entity.getAnimation() == Ignis_Entity.SWING_ATTACK_SOUL
                || entity.getAnimation() == Ignis_Entity.BURNS_THE_EARTH
                || entity.getAnimation() == Ignis_Entity.REINFORCED_SMASH_IN_AIR
                || entity.getAnimation() == Ignis_Entity.PHASE_3
                || entity.getAnimation() == Ignis_Entity.FOUR_COMBO
                || entity.getAnimation() == Ignis_Entity.STRIKE
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SMALL_SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SMALL_SWING_ALT_ATTACK2
                || entity.getAnimation() == Ignis_Entity.SWING_UPPERSLASH) {
            Vec3 bladePos = RenderUtils.getWorldPosFromModel(entity, entityYaw, model.blade2);
            entity.setSocketPosArray(0, bladePos);
        }
    }

    protected int getBlockLightLevel(Ignis_Entity entityIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected float getFlipDegrees(Ignis_Entity entity) {
        return 0;
    }

}