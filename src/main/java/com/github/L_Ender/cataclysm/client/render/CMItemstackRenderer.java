package com.github.L_Ender.cataclysm.client.render;

import com.github.L_Ender.cataclysm.client.model.block.Model_Altar_of_Fire;
import com.github.L_Ender.cataclysm.client.model.block.Model_Altar_of_Void;
import com.github.L_Ender.cataclysm.client.model.block.Model_EMP;
import com.github.L_Ender.cataclysm.client.model.block.Model_Mechanical_Anvil;
import com.github.L_Ender.cataclysm.client.model.entity.ModelCoral_Spear;
import com.github.L_Ender.cataclysm.client.model.item.*;
import com.github.L_Ender.cataclysm.client.model.item.*;
import com.github.L_Ender.cataclysm.init.ModBlocks;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CMItemstackRenderer extends BlockEntityWithoutLevelRenderer {

    public static int ticksExisted = 0;

    private static final ModelBulwark_of_the_flame BULWARK_OF_THE_FLAME_MODEL = new ModelBulwark_of_the_flame();
    private static final Model_EMP EMP_MODEL = new Model_EMP();
    private static final Model_Mechanical_Anvil MF_MODEL = new Model_Mechanical_Anvil();
    private static final ModelGauntlet_of_Guard GAUNTLET_OF_GUARD_MODEL = new ModelGauntlet_of_Guard();
    private static final ModelGauntlet_of_Bulwark GAUNTLET_OF_BULWARK_MODEL = new ModelGauntlet_of_Bulwark();
    private static final ModelIncinerator THE_INCINERATOR_MODEL = new ModelIncinerator();
    private static final Model_Altar_of_Fire ALTAR_OF_FIRE_MODEL = new Model_Altar_of_Fire();
    private static final Model_Altar_of_Void ALTAR_OF_VOID_MODEL = new Model_Altar_of_Void();
    private static final ModelWither_Assault_SHoulder_Weapon WASW_MODEL = new ModelWither_Assault_SHoulder_Weapon();
    private static final ModelCoral_Spear CORAL_SPEAR_MODEL = new ModelCoral_Spear();
    private static final ModelVoid_Forge VOID_FORGE_MODEL = new ModelVoid_Forge();
    private static final ResourceLocation BULWARK_OF_THE_FLAME_TEXTURE = new ResourceLocation("cataclysm:textures/items/bulwark_of_the_flame.png");
    private static final ResourceLocation GAUNTLET_OF_GUARD_TEXTURE = new ResourceLocation("cataclysm:textures/items/gauntlet_of_guard.png");
    private static final ResourceLocation GAUNTLET_OF_BULWARK_TEXTURE = new ResourceLocation("cataclysm:textures/items/gauntlet_of_bulwark.png");
    private static final ResourceLocation THE_INCINERATOR_TEXTURE = new ResourceLocation("cataclysm:textures/items/the_incinerator.png");
    private static final ResourceLocation VOID_FORGE_TEXTURE = new ResourceLocation("cataclysm:textures/items/void_forge.png");
    private static final ResourceLocation ALTAR_OF_FIRE_TEXTURE = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altar_of_fire.png");
    private static final ResourceLocation ALTAR_OF_VOID_TEXTURE = new ResourceLocation("cataclysm:textures/blocks/altar_of_void.png");
    private static final ResourceLocation MIF_TEXTURE = new ResourceLocation("cataclysm:textures/blocks/mechanical_fusion_anvil.png");
    private static final ResourceLocation WASW_TEXTURE = new ResourceLocation("cataclysm:textures/items/wither_assualt_shoulder_weapon.png");
    private static final ResourceLocation VASW_TEXTURE = new ResourceLocation("cataclysm:textures/items/void_assualt_shoulder_weapon.png");
    private static final ResourceLocation EMP_TEXTURE = new ResourceLocation("cataclysm:textures/blocks/emp.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire3.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire4.png");

    private static final ResourceLocation CORAL_SPEAR_TEXTURE = new ResourceLocation("cataclysm:textures/entity/coral_spear.png");

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
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(BULWARK_OF_THE_FLAME_TEXTURE), false, itemStackIn.hasFoil());
            BULWARK_OF_THE_FLAME_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if (itemStackIn.getItem() == ModItems.GAUNTLET_OF_GUARD.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(GAUNTLET_OF_GUARD_TEXTURE), false, itemStackIn.hasFoil());
            GAUNTLET_OF_GUARD_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if (itemStackIn.getItem() == ModItems.GAUNTLET_OF_BULWARK.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(GAUNTLET_OF_BULWARK_TEXTURE), false, itemStackIn.hasFoil());
            GAUNTLET_OF_BULWARK_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if (itemStackIn.getItem() == ModItems.THE_INCINERATOR.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(THE_INCINERATOR_TEXTURE), false, itemStackIn.hasFoil());
            THE_INCINERATOR_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if (itemStackIn.getItem() == ModItems.WITHER_ASSULT_SHOULDER_WEAPON.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(WASW_TEXTURE), false, itemStackIn.hasFoil());
            WASW_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if (itemStackIn.getItem() == ModItems.VOID_ASSULT_SHOULDER_WEAPON.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(VASW_TEXTURE), false, itemStackIn.hasFoil());
            WASW_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }

        if (itemStackIn.getItem() == ModItems.CORAL_SPEAR.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(CORAL_SPEAR_TEXTURE), false, itemStackIn.hasFoil());
            CORAL_SPEAR_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }

        if (itemStackIn.getItem() == ModItems.VOID_FORGE.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(VOID_FORGE_TEXTURE), false, itemStackIn.hasFoil());
            VOID_FORGE_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
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
        if(itemStackIn.getItem() == ModBlocks.ALTAR_OF_VOID.get().asItem()){
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            ALTAR_OF_VOID_MODEL.resetToDefaultPose();
            ALTAR_OF_VOID_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(ALTAR_OF_VOID_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if(itemStackIn.getItem() == ModBlocks.EMP.get().asItem()){
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            EMP_MODEL.resetToDefaultPose();
            EMP_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(EMP_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
        if(itemStackIn.getItem() == ModBlocks.MECHANICAL_FUSION_ANVIL.get().asItem()){
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180));
            MF_MODEL.resetToDefaultPose();
            MF_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(MIF_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
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
