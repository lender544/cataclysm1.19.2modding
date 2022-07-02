package L_Ender.cataclysm.items;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.init.ModEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class Monstrous_Helm extends ArmorItem {

    public Monstrous_Helm(ArmorMaterials material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);

    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept((net.minecraftforge.client.IItemRenderProperties) cataclysm.PROXY.getArmorRenderProperties());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return cataclysm.MODID + ":textures/armor/monstrous_helm.png";
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
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        boolean berserk = player.getMaxHealth() * 1 / 2 >= player.getHealth();
        double radius = 4.0D;
        double xx = Mth.cos(player.getYRot() % 360.0F / 180.0F * 3.1415927F) * 0.75F;
        double zz = Mth.sin(player.getYRot() % 360.0F / 180.0F * 3.1415927F) * 0.75F;
        List<Entity> list = world.getEntities(player, player.getBoundingBox().inflate(radius));
        if(berserk && !(player.getCooldowns().isOnCooldown(this))) {
           // player.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 0.75F, 0.5F);
            for (Entity entity : list) {
                if (entity instanceof LivingEntity) {
                    entity.hurt(DamageSource.playerAttack(player), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE)* 1/2);
                    double d0 = entity.getX() - player.getX();
                    double d1 = entity.getZ() - player.getZ();
                    double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                    entity.push(d0 / d2 * 1.5 , 0.15D, d1 / d2 * 1.5);
                }
            }
            world.explode(player, player.getX() + xx, player.getY() + (double) player.getEyeHeight(), player.getZ() + zz, 1.5F, Explosion.BlockInteraction.NONE);
            player.getCooldowns().addCooldown(this, 350);
            player.addEffect(new MobEffectInstance(ModEffect.EFFECTMONSTROUS.get(), 200, 0, false, true));
        }
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.ARMOR || enchantment.category == EnchantmentCategory.ARMOR_HEAD;

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.cataclysm.monstrous_helm.desc").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.cataclysm.monstrous_helm2.desc").withStyle(ChatFormatting.DARK_GREEN));
    }
}