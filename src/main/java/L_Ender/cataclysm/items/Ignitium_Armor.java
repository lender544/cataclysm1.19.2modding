package L_Ender.cataclysm.items;

import L_Ender.cataclysm.cataclysm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.List;

public class Ignitium_Armor extends ArmorItem {

    public Ignitium_Armor(Armortier material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);

    }
    
    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) cataclysm.PROXY.getArmorRenderProperties());
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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (this.slot == EquipmentSlot.HEAD) {
            tooltip.add(Component.translatable("item.cataclysm.ignitium_helmet.desc").withStyle(ChatFormatting.DARK_GREEN));
        }
        if (this.slot == EquipmentSlot.CHEST) {
            tooltip.add(Component.translatable("item.cataclysm.ignitium_chestplate.desc").withStyle(ChatFormatting.DARK_GREEN));
        }
        if (this.slot == EquipmentSlot.LEGS) {
            tooltip.add(Component.translatable("item.cataclysm.ignitium_leggings.desc").withStyle(ChatFormatting.DARK_GREEN));
        }
        if (this.slot == EquipmentSlot.FEET) {
            tooltip.add(Component.translatable("item.cataclysm.ignitium_boots.desc").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

}