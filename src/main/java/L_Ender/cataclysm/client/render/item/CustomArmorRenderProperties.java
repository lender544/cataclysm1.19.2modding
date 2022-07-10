package L_Ender.cataclysm.client.render.item;

import L_Ender.cataclysm.client.model.armor.CMModelLayers;
import L_Ender.cataclysm.client.model.armor.ModelIgnitium_Armor;
import L_Ender.cataclysm.client.model.armor.ModelMonstrousHelm;
import L_Ender.cataclysm.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CustomArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;


    public static ModelMonstrousHelm MONSTROUS_HELM_MODEL;
    public static ModelIgnitium_Armor IGNITIUM_ARMOR_MODEL;
    public static ModelIgnitium_Armor IGNITIUM_ARMOR_MODEL_LEGS;

    public static void initializeModels() {
        init = true;
        MONSTROUS_HELM_MODEL = new ModelMonstrousHelm(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.MONSTROUS_HELM));
        IGNITIUM_ARMOR_MODEL = new ModelIgnitium_Armor(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.IGNITIUM_ARMOR_MODEL));
        IGNITIUM_ARMOR_MODEL_LEGS = new ModelIgnitium_Armor(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.IGNITIUM_ARMOR_MODEL_LEGS));
    }

    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if(!init){
            initializeModels();
        }

        if(itemStack.getItem() == ModItems.MONSTROUS_HELM.get()){
            return MONSTROUS_HELM_MODEL;
        }

        if(itemStack.getItem() == ModItems.IGNITIUM_HELMET.get()){
            return IGNITIUM_ARMOR_MODEL;
        }

        if(itemStack.getItem() == ModItems.IGNITIUM_CHESTPLATE.get()){
            return IGNITIUM_ARMOR_MODEL;
        }

        if(itemStack.getItem() == ModItems.IGNITIUM_LEGGINGS.get()){
            return IGNITIUM_ARMOR_MODEL_LEGS;
        }

        if(itemStack.getItem() == ModItems.IGNITIUM_BOOTS.get()){
            return IGNITIUM_ARMOR_MODEL;
        }
        return _default;
    }
}
