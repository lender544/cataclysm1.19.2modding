package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.client.model.entity.ModelEndermaptera;
import L_Ender.cataclysm.client.render.layer.LayerGenericGlowing;
import L_Ender.cataclysm.entity.Endermaptera_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererEndermaptera extends MobRenderer<Endermaptera_Entity, ModelEndermaptera> {

    private static final ResourceLocation SSAPBUG_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ender_ssap_bug.png");
    private static final ResourceLocation SSAPBUG_LAYER_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ender_ssap_bug_layer.png");

    public RendererEndermaptera(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelEndermaptera(), 0.7F);
        this.addLayer(new LayerGenericGlowing(this, SSAPBUG_LAYER_TEXTURES));

    }
    @Override
    public ResourceLocation getTextureLocation(Endermaptera_Entity entity) {
        return SSAPBUG_TEXTURES;
    }

    @Override
    protected void scale(Endermaptera_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void setupRotations(Endermaptera_Entity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (this.isShaking(entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.tickCount * 3.25D) * Math.PI * (double)0.4F);
        }
        float trans = 0.5F;
        Pose pose = entityLiving.getPose();
        if (pose != Pose.SLEEPING) {
            float progresso = 1F - (entityLiving.prevAttachChangeProgress + (entityLiving.attachChangeProgress - entityLiving.prevAttachChangeProgress) * partialTicks);

            if(entityLiving.getAttachmentFacing() == Direction.DOWN){
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees (180.0F - rotationYaw));
                matrixStackIn.translate(0.0D, trans, 0.0D);
                if(entityLiving.yo < entityLiving.getY()){
                    matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90 * (1 - progresso)));
                }else{
                    matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-90 * (1 - progresso)));
                }
                matrixStackIn.translate(0.0D, -trans, 0.0D);

            }else if(entityLiving.getAttachmentFacing() == Direction.UP){
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees (180.0F - rotationYaw));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(180));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180));
                matrixStackIn.translate(0.0D, -trans, 0.0D);

            }else{
                matrixStackIn.translate(0.0D, trans, 0.0D);
                switch (entityLiving.getAttachmentFacing()){
                    case NORTH:
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * progresso));
                        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(0));
                        break;
                    case SOUTH:
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * progresso ));
                        break;
                    case WEST:
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F - 90.0F * progresso));
                        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
                        break;
                    case EAST:
                        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F ));
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F * progresso - 90F));
                        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                        break;
                }
                if(entityLiving.getDeltaMovement().y <= -0.001F){
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-180.0F));
                }
                matrixStackIn.translate(0.0D, -trans, 0.0D);
            }
        }

        if (entityLiving.deathTime > 0) {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }

            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(f * this.getFlipDegrees(entityLiving)));
        } else if (entityLiving.isAutoSpinAttack()) {
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-90.0F - entityLiving.getXRot()));
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(((float)entityLiving.tickCount + partialTicks) * -75.0F));
        } else if (pose == Pose.SLEEPING) {

        } else if (entityLiving.hasCustomName() ) {
            String s = ChatFormatting.stripFormatting(entityLiving.getName().getString());
            if (("Dinnerbone".equals(s) || "Grumm".equals(s))) {
                matrixStackIn.translate(0.0D, (double)(entityLiving.getBbHeight() + 0.1F), 0.0D);
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            }
        }
    }

}