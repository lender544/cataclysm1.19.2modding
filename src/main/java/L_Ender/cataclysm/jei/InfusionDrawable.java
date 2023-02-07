package L_Ender.cataclysm.jei;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class InfusionDrawable implements IDrawable {

    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm", "textures/gui/infusion.png");
    @Override
    public int getWidth() {
        return 125;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = xOffset;
        int j = yOffset;
        GuiComponent.blit(poseStack, i, j, 26, 46, 125, 18, 256, 256);
    }
}
