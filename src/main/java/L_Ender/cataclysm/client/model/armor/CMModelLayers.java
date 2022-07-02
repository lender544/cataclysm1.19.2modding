package L_Ender.cataclysm.client.model.armor;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class CMModelLayers {

    public static final ModelLayerLocation MONSTROUS_HELM = createLocation("monstrous", "main");
    public static final ModelLayerLocation IGNITIUM_ARMOR_MODEL = createLocation("ignitium_armor_model", "main");
    public static final ModelLayerLocation IGNITIUM_ARMOR_MODEL_LEGS = createLocation("ignitium_armor_model_leg", "main");

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MONSTROUS_HELM, () -> ModelMonstrousHelm.createArmorLayer(new CubeDeformation(0.3F)));
        event.registerLayerDefinition(IGNITIUM_ARMOR_MODEL, () -> ModelIgnitium_Armor.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(IGNITIUM_ARMOR_MODEL_LEGS, () -> ModelIgnitium_Armor.createArmorLayer(new CubeDeformation(0.2F)));
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation("cataclysm", model), layer);
    }


}
