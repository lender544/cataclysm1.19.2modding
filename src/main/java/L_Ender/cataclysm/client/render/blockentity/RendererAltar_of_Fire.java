package L_Ender.cataclysm.client.render.blockentity;

import L_Ender.cataclysm.client.model.block.Model_Altar_of_Fire;
import L_Ender.cataclysm.client.render.CMRenderTypes;
import L_Ender.cataclysm.tileentities.TileEntityAltarOfFire;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class RendererAltar_of_Fire<T extends TileEntityAltarOfFire> implements BlockEntityRenderer<T> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altar_of_fire.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire3.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation("cataclysm:textures/blocks/altar_of_fire/altarfire4.png");
    private static final Model_Altar_of_Fire MODEL = new Model_Altar_of_Fire();

    public RendererAltar_of_Fire(Context rendererDispatcherIn) {
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.5F, 0.5F);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        MODEL.animate(tileEntityIn, partialTicks);;
        MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), combinedLightIn, combinedOverlayIn, 1, 1F, 1, 1);
        MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(CMRenderTypes.getGlowingEffect(getIdleTexture(tileEntityIn.tickCount % 12))), 210, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStackIn.popPose();
        renderItem(tileEntityIn, partialTicks,matrixStackIn,bufferIn,combinedLightIn);

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

    public void renderItem(T tileEntityIn,float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn) {
        ItemStack stack = tileEntityIn.getItem(0);
        float f2 = (float) tileEntityIn.tickCount + partialTicks;
        if (!stack.isEmpty()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.0F, 0.5F);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f2));
            BakedModel ibakedmodel = Minecraft.getInstance().getItemRenderer().getModel(stack, tileEntityIn.getLevel(), (LivingEntity) null, 180);
            Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn, combinedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
            matrixStackIn.popPose();
        }
    }
}


