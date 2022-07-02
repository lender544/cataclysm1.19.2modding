package L_Ender.cataclysm.client.render.layer;

import L_Ender.cataclysm.client.model.entity.ModelNetherite_Monstrosity;
import L_Ender.cataclysm.client.render.entity.RendererNetherite_Monstrosity;
import L_Ender.cataclysm.entity.Netherite_Monstrosity_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Netherite_Monstrosity_Layer extends RenderLayer<Netherite_Monstrosity_Entity, ModelNetherite_Monstrosity> {
    private static final ResourceLocation NETHERITE_MONSTRISITY_LAYER_TEXTURES  = new ResourceLocation("cataclysm:textures/entity/netherite_monstrosity_layer.png");

    public Netherite_Monstrosity_Layer(RendererNetherite_Monstrosity renderIn) {
        super(renderIn);

    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Netherite_Monstrosity_Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getIsAwaken() && entity.deathTime <= 75) {
            //need rework
            RenderType eyes = RenderType.eyes(NETHERITE_MONSTRISITY_LAYER_TEXTURES);
            VertexConsumer VertexConsumer = bufferIn.getBuffer(eyes);
            this.getParentModel().renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}


