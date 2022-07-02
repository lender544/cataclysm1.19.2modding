package L_Ender.cataclysm.items;

import L_Ender.cataclysm.cataclysm;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Ignitium_Armor extends ArmorItem {

    public Ignitium_Armor(Armortier material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);

    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept((net.minecraftforge.client.IItemRenderProperties) cataclysm.PROXY.getArmorRenderProperties());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return cataclysm.MODID + ":textures/armor/ignitium_armor" + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png");
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack itemStackMaterial) {
        return false;
    }




    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (this.slot == EquipmentSlot.HEAD) {
            return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.ARMOR || enchantment.category == EnchantmentCategory.ARMOR_HEAD;
        }
        if (this.slot == EquipmentSlot.CHEST) {
            return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.ARMOR || enchantment.category == EnchantmentCategory.ARMOR_CHEST;
        }
        if (this.slot == EquipmentSlot.LEGS) {
            return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.ARMOR;
        }
        if (this.slot == EquipmentSlot.FEET) {
            return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.ARMOR || enchantment.category == EnchantmentCategory.ARMOR_FEET;
        }
        return super.canApplyAtEnchantingTable(stack,enchantment);
    }

}