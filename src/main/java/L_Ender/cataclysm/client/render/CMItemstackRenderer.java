package L_Ender.cataclysm.client.render;

import L_Ender.cataclysm.client.model.item.ModelBulwark_of_the_flame;
import L_Ender.cataclysm.client.model.item.ModelGauntlet_of_Guard;
import L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CMItemstackRenderer extends BlockEntityWithoutLevelRenderer {

    private static final ModelBulwark_of_the_flame BULWARK_OF_THE_FLAME_MODEL = new ModelBulwark_of_the_flame();
    private static final ModelGauntlet_of_Guard GAUNTLET_OF_GUARD_MODEL = new ModelGauntlet_of_Guard();
    private static final ResourceLocation BULWARK_OF_THE_FLAME_TEXTURE = new ResourceLocation("cataclysm:textures/items/bulwark_of_the_flame.png");
    private static final ResourceLocation GAUNTLET_OF_GUARD_TEXTURE = new ResourceLocation("cataclysm:textures/items/gauntlet_of_guard.png");

    public CMItemstackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType p_239207_2_, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
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


    }
}
