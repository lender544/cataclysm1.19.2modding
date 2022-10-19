package L_Ender.cataclysm.items;

import L_Ender.cataclysm.init.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;


 public enum Armortier implements ArmorMaterial {
     IGNITIUM(new int[] {4, 7, 10, 4}, 3.5f, 45, 25, 0.15f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.IGNITIUM_INGOT);

     private static final int[] DURABILITY_ARRAY = new int[] {13, 15, 16, 11};
     private final int durability, enchantability;
     private final int[] dmgReduction; // boots[0], legs[1], chest[2], helm[3]
     private final float toughness, knockbackResistance;
     private final SoundEvent sound;
     private final Supplier<Item> repairMaterial;

     Armortier(int[] dmgReduction, float toughness, int durability, int enchantability, float knockbackResistance, SoundEvent sound, Supplier<Item> repairMaterial)
     {
         this.durability = durability;
         this.dmgReduction = dmgReduction;
         this.enchantability = enchantability;
         this.toughness = toughness;
         this.knockbackResistance = knockbackResistance;
         this.sound = sound;
         this.repairMaterial = repairMaterial;
     }

     @Override
     public int getDurabilityForSlot(EquipmentSlot slotIn)
     {
         return DURABILITY_ARRAY[slotIn.getIndex()] * durability;
     }

     @Override
     public int getDefenseForSlot(EquipmentSlot slot)
     {
         return dmgReduction[slot.getIndex()];
     }

     @Override
     public int getEnchantmentValue()
     {
         return enchantability;
     }

     @Override
     public SoundEvent getEquipSound()
     {
         return sound;
     }

     @Override
     public Ingredient getRepairIngredient()
     {
         return Ingredient.of(repairMaterial.get());
     }

     @Override
     public String getName()
     {
         return toString().toLowerCase();
     }

     @Override
     public float getToughness()
     {
         return toughness;
     }

     @Override
     public float getKnockbackResistance()
     {
         return knockbackResistance;
     }

 }



