package com.github.L_Ender.cataclysm.client.render.layer;

import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Harbinger;
import com.github.L_Ender.cataclysm.client.render.RenderUtils;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Harbinger;
import com.github.L_Ender.cataclysm.entity.BossMonster.The_Harbinger_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class The_Harbinger_Item_Layer extends RenderLayer<The_Harbinger_Entity, ModelThe_Harbinger> {
    private AdvancedModelBox AdvancedModelBox;
    private ItemStack itemstack;
    private ItemTransforms.TransformType transformType;

    public The_Harbinger_Item_Layer(RendererThe_Harbinger renderIn, AdvancedModelBox AdvancedModelBox, ItemStack itemstack, ItemTransforms.TransformType transformType) {
        super(renderIn);
        this.itemstack = itemstack;
        this.AdvancedModelBox = AdvancedModelBox;
        this.transformType = transformType;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, The_Harbinger_Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getIsAct()) {
            matrixStackIn.pushPose();
            RenderUtils.matrixStackFromModel(matrixStackIn, getAdvancedModelBox());
            matrixStackIn.translate(-0.0125F, 0.0F, 0.0F);
            Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().renderItem(entity, getItemstack(), transformType, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }
    }

    public ItemStack getItemstack() {
        return itemstack;
    }

    public void setItemstack(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public AdvancedModelBox getAdvancedModelBox() {
        return AdvancedModelBox;
    }

    public void setAdvancedModelBox(AdvancedModelBox AdvancedModelBox) {
        this.AdvancedModelBox = AdvancedModelBox;
    }
}
