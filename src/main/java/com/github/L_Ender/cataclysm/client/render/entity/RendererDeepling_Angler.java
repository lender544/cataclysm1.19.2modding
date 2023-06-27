package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelDeepling_Angler;
import com.github.L_Ender.cataclysm.client.render.layer.Deepling_Angler_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.LayerDeepling_AnglerItem;
import com.github.L_Ender.cataclysm.entity.Deepling.Deepling_Angler_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererDeepling_Angler extends MobRenderer<Deepling_Angler_Entity, ModelDeepling_Angler> {

    private static final ResourceLocation SSAPBUG_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/deepling_angler.png");

    public RendererDeepling_Angler(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelDeepling_Angler(), 0.7F);
        this.addLayer(new Deepling_Angler_Layer(this));
        this.addLayer(new LayerDeepling_AnglerItem(this, renderManagerIn.getItemInHandRenderer()));

    }
    @Override
    public ResourceLocation getTextureLocation(Deepling_Angler_Entity entity) {
        return SSAPBUG_TEXTURES;
    }

    @Override
    protected void scale(Deepling_Angler_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }
}