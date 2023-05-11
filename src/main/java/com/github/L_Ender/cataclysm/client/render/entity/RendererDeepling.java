package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelDeepling;
import com.github.L_Ender.cataclysm.client.model.entity.ModelEndermaptera;
import com.github.L_Ender.cataclysm.client.render.layer.Deepling_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.LayerDeeplingItem;
import com.github.L_Ender.cataclysm.client.render.layer.LayerGenericGlowing;
import com.github.L_Ender.cataclysm.entity.Deepling_Entity;
import com.github.L_Ender.cataclysm.entity.Endermaptera_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererDeepling extends MobRenderer<Deepling_Entity, ModelDeepling> {

    private static final ResourceLocation SSAPBUG_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/deepling_1.png");
    private static final ResourceLocation SSAPBUG_LAYER_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/deepling_layer.png");

    public RendererDeepling(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelDeepling(), 0.7F);
        this.addLayer(new Deepling_Layer(this));
        this.addLayer(new LayerDeeplingItem(this, renderManagerIn.getItemInHandRenderer()));

    }
    @Override
    public ResourceLocation getTextureLocation(Deepling_Entity entity) {
        return SSAPBUG_TEXTURES;
    }

    @Override
    protected void scale(Deepling_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }


}