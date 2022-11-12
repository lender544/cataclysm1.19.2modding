package L_Ender.cataclysm.client.render.layer;

import L_Ender.cataclysm.client.render.RenderUtils;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Item_Layer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private AdvancedModelBox modelRenderer;
    private ItemStack itemstack;
    private ItemTransforms.TransformType transformType;

    public Item_Layer(RenderLayerParent<T, M> renderer, AdvancedModelBox modelRenderer, ItemStack itemstack, ItemTransforms.TransformType transformType) {
        super(renderer);
        this.itemstack = itemstack;
        this.modelRenderer = modelRenderer;
        this.transformType = transformType;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!modelRenderer.showModel) return;
        matrixStackIn.pushPose();
        RenderUtils.matrixStackFromModel(matrixStackIn, getModelRenderer());
        Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().renderItem(entitylivingbaseIn, getItemstack(), transformType, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

    public ItemStack getItemstack() {
        return itemstack;
    }

    public void setItemstack(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public AdvancedModelBox getModelRenderer() {
        return modelRenderer;
    }

    public void setModelRenderer(AdvancedModelBox modelRenderer) {
        this.modelRenderer = modelRenderer;
    }
}
