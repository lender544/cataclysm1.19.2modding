package com.github.L_Ender.cataclysm.client.render.item;

import com.github.L_Ender.cataclysm.client.model.armor.*;
import com.github.L_Ender.cataclysm.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CustomArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;

    public static Modelignitium_Elytra_chestplate ELYTRA_ARMOR;
    public static ModelMonstrousHelm MONSTROUS_HELM_MODEL;
    public static ModelIgnitium_Armor IGNITIUM_ARMOR_MODEL;
    public static ModelIgnitium_Armor IGNITIUM_ARMOR_MODEL_LEGS;
    public static ModelBloom_Stone_Pauldrons BLOOM_STONE_PAULDRONS_MODEL;

    public static void initializeModels() {
        init = true;
        MONSTROUS_HELM_MODEL = new ModelMonstrousHelm(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.MONSTROUS_HELM));
        IGNITIUM_ARMOR_MODEL = new ModelIgnitium_Armor(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.IGNITIUM_ARMOR_MODEL));
        ELYTRA_ARMOR = new Modelignitium_Elytra_chestplate(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.ELYTRA_ARMOR));
        IGNITIUM_ARMOR_MODEL_LEGS = new ModelIgnitium_Armor(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.IGNITIUM_ARMOR_MODEL_LEGS));
        BLOOM_STONE_PAULDRONS_MODEL = new ModelBloom_Stone_Pauldrons(Minecraft.getInstance().getEntityModels().bakeLayer(CMModelLayers.BLOOM_STONE_PAULDRONS_MODEL));

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

        if(itemStack.getItem() == ModItems.BLOOM_STONE_PAULDRONS.get()){
            return BLOOM_STONE_PAULDRONS_MODEL;
        }

        if(itemStack.getItem() == ModItems.IGNITIUM_ELYTRA_CHESTPLATE.get()){
            return ELYTRA_ARMOR.withAnimations(entityLiving);
        }
        return _default;
    }
}
