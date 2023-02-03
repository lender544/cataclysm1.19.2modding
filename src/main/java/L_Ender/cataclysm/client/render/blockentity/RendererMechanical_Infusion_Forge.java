package L_Ender.cataclysm.client.render.blockentity;


import L_Ender.cataclysm.client.model.block.Model_Mechanical_Forge;

import L_Ender.cataclysm.tileentities.TileEntityMechanical_Infusion_Forge;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

import net.minecraft.resources.ResourceLocation;

public class RendererMechanical_Infusion_Forge<T extends TileEntityMechanical_Infusion_Forge> implements BlockEntityRenderer<T> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm:textures/blocks/mechanical_infusion_forge.png");
    private static final Model_Mechanical_Forge MODEL = new Model_Mechanical_Forge();

    public RendererMechanical_Infusion_Forge(Context rendererDispatcherIn) {
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.5F, 0.5F);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), combinedLightIn, combinedOverlayIn, 1, 1F, 1, 1);
        matrixStackIn.popPose();
    }

}


