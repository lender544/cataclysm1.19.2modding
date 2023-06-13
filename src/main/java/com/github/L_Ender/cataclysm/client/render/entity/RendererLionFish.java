package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelDeepling;
import com.github.L_Ender.cataclysm.client.model.entity.ModelLionFish;
import com.github.L_Ender.cataclysm.client.render.layer.Deepling_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.LayerDeeplingItem;
import com.github.L_Ender.cataclysm.client.render.layer.LionFish_Layer;
import com.github.L_Ender.cataclysm.entity.Deepling.Deepling_Entity;
import com.github.L_Ender.cataclysm.entity.Deepling.LionFish_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererLionFish extends MobRenderer<LionFish_Entity, ModelLionFish> {

    private static final ResourceLocation LIONFISH_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/lionfish.png");

    public RendererLionFish(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelLionFish(), 0.4F);
        this.addLayer(new LionFish_Layer(this));

    }
    @Override
    public ResourceLocation getTextureLocation(LionFish_Entity entity) {
        return LIONFISH_TEXTURES;
    }

    @Override
    protected void scale(LionFish_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }


}