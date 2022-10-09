package L_Ender.cataclysm.client.render;

import L_Ender.cataclysm.client.model.block.Model_Altar_of_Fire;
import L_Ender.cataclysm.client.model.item.ModelBulwark_of_the_flame;
import L_Ender.cataclysm.client.model.item.ModelGauntlet_of_Guard;
import L_Ender.cataclysm.client.model.item.ModelIncinerator;
import L_Ender.cataclysm.init.ModBlocks;
import L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CMItemstackRenderer extends BlockEntityWithoutLevelRenderer {

    public static int ticksExisted = 0;

    private static final ModelBulwark_of_the_flame BULWARK_OF_THE_FLAME_MODEL = new ModelBulwark_of_the_flame();
    private static final ModelGauntlet_of_Guard GAUNTLET_OF_GUARD_MODEL = new ModelGauntlet_of_Guard();
    private static final ModelIncinerator THE_INCINERATOR_MODEL = new ModelIncinerator();
    private static final Model_Altar_of_Fire ALTAR_OF_FIRE_MODEL = new Model_Altar_of_Fire();
    private static final ResourceLocation BULWARK_OF_THE_FLAME_TEXTURE = new ResourceLocation("cataclysm:textures/items/bulwark_of_the_flame.png");
    private static final ResourceLocation GAUNTLET_OF_GUARD_TEXTURE = new ResourceLocation("cataclysm:textures/items/gauntlet_of_guard.png");
    private static final ResourceLocation THE_INCINERATOR_TEXTURE = new ResourceLocation("cataclysm:textures/items/the_incinerator.png");
    private static final ResourceLocation ALTAR_OF_FIRE_TEXTURE = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altar_of_fire.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire3.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire4.png");

    public CMItemstackRenderer() {
        super(null, null);
    }

    public static void incrementTick() {
        ticksExisted++;
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType p_239207_2_, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        int tick;
        if(Minecraft.getInstance().player == null || Minecraft.getInstance().isPaused()){
            tick = ticksExisted;
        }else{
            tick = Minecraft.getInstance().player.tickCount;
        }
        if (itemStackIn.getItem() == ModItems.BULWARK_OF_THE_FLAME.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.4F, -0.75F, 0.5F);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-180));
            BULWARK_OF_THE_FLAME_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(BULWARK_OF_THE_FLAME_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }

        if (itemStackIn.getItem() == ModItems.GAUNTLET_OF_GUARD.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            GAUNTLET_OF_GUARD_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(GAUNTLET_OF_GUARD_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }

        if (itemStackIn.getItem() == ModItems.THE_INCINERATOR.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            //matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-180));
            THE_INCINERATOR_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(THE_INCINERATOR_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }

        if(itemStackIn.getItem() == ModBlocks.ALTAR_OF_FIRE.get().asItem()){
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            ALTAR_OF_FIRE_MODEL.resetToDefaultPose();
            ALTAR_OF_FIRE_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(ALTAR_OF_FIRE_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            ALTAR_OF_FIRE_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(CMRenderTypes.getGlowingEffect(getIdleTexture(tick % 12))), 210, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
            matrixStackIn.popPose();
        }

    }

    private ResourceLocation getIdleTexture(int age) {
        if (age < 3) {
            return TEXTURE_1;
        } else if (age < 6) {
            return TEXTURE_2;
        } else if (age < 9) {
            return TEXTURE_3;
        } else if (age < 12) {
            return TEXTURE_4;
        } else {
            return TEXTURE_1;
        }
    }
}
