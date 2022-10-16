package L_Ender.cataclysm.client.render.entity;

import L_Ender.cataclysm.entity.projectile.Blazing_Bone_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class RendererBlazing_Bone extends EntityRenderer<Blazing_Bone_Entity> {

    public RendererBlazing_Bone(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public void render(Blazing_Bone_Entity entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        float spin = (entity.tickCount + partialTicks) * 30F;
        // size up
        stack.scale(1.25F, 1.25F, 1.25F);
        this.renderDroppedItem(stack, buffer, light, entity.getItem(), yaw, spin);
        stack.popPose();
    }

    private void renderDroppedItem(PoseStack matrix, MultiBufferSource buffer, int light, ItemStack stack, float rotation, float spin) {
        matrix.pushPose();
        matrix.mulPose(Vector3f.YP.rotationDegrees(rotation + 90));
        matrix.mulPose(Vector3f.ZP.rotationDegrees(spin));
        matrix.translate(0f, 0f, 0);
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, light, OverlayTexture.NO_OVERLAY, matrix, buffer, 0);
        matrix.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(Blazing_Bone_Entity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
