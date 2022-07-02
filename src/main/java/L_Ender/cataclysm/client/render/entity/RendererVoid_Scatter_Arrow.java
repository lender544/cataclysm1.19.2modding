package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.entity.projectile.Void_Scatter_Arrow_Entity;
import net.minecraft.client.renderer.entity.ArrowRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class RendererVoid_Scatter_Arrow extends ArrowRenderer<Void_Scatter_Arrow_Entity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm:textures/entity/void_scatter_arrow.png");

    public RendererVoid_Scatter_Arrow(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(Void_Scatter_Arrow_Entity entity) {
        return TEXTURE;
    }
}
