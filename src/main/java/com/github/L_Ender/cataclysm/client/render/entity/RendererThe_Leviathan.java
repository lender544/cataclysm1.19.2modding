package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Leviathan;
import com.github.L_Ender.cataclysm.entity.Nameless_Sorcerer_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererThe_Leviathan extends MobRenderer<The_Leviathan_Entity, ModelThe_Leviathan> {

    private static final ResourceLocation LEVIATHAN_TEXTURES = new ResourceLocation("cataclysm:textures/entity/the_leviathan.png");


    public RendererThe_Leviathan(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelThe_Leviathan(), 1.5F);

    }
    @Override
    public ResourceLocation getTextureLocation(The_Leviathan_Entity entity) {
        return LEVIATHAN_TEXTURES;
    }


    @Override
    protected void scale(The_Leviathan_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.75F, 1.75F, 1.75F);
    }


}

