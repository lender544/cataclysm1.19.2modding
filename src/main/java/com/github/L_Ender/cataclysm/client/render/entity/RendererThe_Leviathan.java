package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Leviathan;
import com.github.L_Ender.cataclysm.client.render.layer.LayerBasicGlow;
import com.github.L_Ender.cataclysm.client.render.layer.The_Leviathan_Layer;
import com.github.L_Ender.cataclysm.entity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Part;
import com.github.L_Ender.cataclysm.entity.partentity.Netherite_Monstrosity_Part;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererThe_Leviathan extends MobRenderer<The_Leviathan_Entity, ModelThe_Leviathan> {

    private static final ResourceLocation LEVIATHAN_TEXTURES = new ResourceLocation("cataclysm:textures/entity/leviathan/the_leviathan.png");
    private static final ResourceLocation LEVIATHAN_TEXTURE_EYES = new ResourceLocation("cataclysm:textures/entity/leviathan/the_leviathan_eye.png");
    private final RandomSource rnd = RandomSource.create();

    public RendererThe_Leviathan(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelThe_Leviathan(), 1.5F);
        this.addLayer(new The_Leviathan_Layer(this));
        this.addLayer(new LayerBasicGlow(this, LEVIATHAN_TEXTURE_EYES));
    }

    @Override
    public ResourceLocation getTextureLocation(The_Leviathan_Entity entity) {
        return LEVIATHAN_TEXTURES;
    }



    public Vec3 getRenderOffset(The_Leviathan_Entity entityIn, float partialTicks) {
        if (entityIn.getAnimation() == The_Leviathan_Entity.LEVIATHAN_ABYSS_BLAST && entityIn.getAnimationTick() <= 66) {
            double d0 = 0.01D;
            return new Vec3(this.rnd.nextGaussian() * d0, 0.0D, this.rnd.nextGaussian() * d0);
        } else {
            return super.getRenderOffset(entityIn, partialTicks);
        }
    }

    @Override
    protected void scale(The_Leviathan_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.75F, 1.75F, 1.75F);
    }


}

