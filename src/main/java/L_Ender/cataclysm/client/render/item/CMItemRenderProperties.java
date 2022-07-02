package L_Ender.cataclysm.client.render.item;


import L_Ender.cataclysm.client.render.CMItemstackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.IItemRenderProperties;

public class CMItemRenderProperties implements IItemRenderProperties {

    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
        return new CMItemstackRenderer();
    }
}
