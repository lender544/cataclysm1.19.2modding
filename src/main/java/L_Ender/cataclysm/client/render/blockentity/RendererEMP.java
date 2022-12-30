package L_Ender.cataclysm.client.render.blockentity;

import L_Ender.cataclysm.blocks.BlockEMP;
import L_Ender.cataclysm.client.model.block.Model_Altar_of_Fire;
import L_Ender.cataclysm.client.model.block.Model_EMP;
import L_Ender.cataclysm.client.render.CMRenderTypes;
import L_Ender.cataclysm.tileentities.TileEntityAltarOfFire;
import L_Ender.cataclysm.tileentities.TileEntityEMP;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class RendererEMP<T extends TileEntityEMP> implements BlockEntityRenderer<T> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm:textures/blocks/emp.png");
    private static final Model_EMP MODEL_EMP = new Model_EMP();

    public RendererEMP(Context rendererDispatcherIn) {
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.5F, 0.5F);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        matrixStackIn.pushPose();
        matrixStackIn.translate(0, 0.2F, 0.0F);
        matrixStackIn.scale(0.9f,0.9f,0.9f);
        MODEL_EMP.animate(tileEntityIn, partialTicks);
        MODEL_EMP.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), combinedLightIn, combinedOverlayIn, 1, 1F, 1, 1);
        matrixStackIn.popPose();
        matrixStackIn.popPose();
    }

}


