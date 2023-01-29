package L_Ender.cataclysm.items;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.entity.effect.Flame_Strike_Entity;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.entity.projectile.Wither_Howitzer_Entity;
import L_Ender.cataclysm.entity.projectile.Wither_Missile_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModSounds;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Wither_Assault_SHoulder_Weapon extends Item {

    public Wither_Assault_SHoulder_Weapon(Properties group) {
        super(group);
    }


    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BOW;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    public void releaseUsing(ItemStack p_43394_, Level p_43395_, LivingEntity p_43396_, int p_43397_) {
        if (p_43396_ instanceof Player player) {
            int i = this.getUseDuration(p_43394_) - p_43397_;
            float f = getPowerForTime(i);
            if (!((double) f < 0.5D)) {
                p_43395_.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS,1.0F, 0.7F);
                if(p_43396_.isShiftKeyDown()) {
                    player.getCooldowns().addCooldown(this, 100);
                    if (!p_43395_.isClientSide) {
                        Wither_Howitzer_Entity rocket = new Wither_Howitzer_Entity(ModEntities.WITHER_HOWITZER.get(), p_43395_, player);
                        rocket.setRadius(1.5F);
                        rocket.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 1.0F, 1.0F);
                        p_43395_.addFreshEntity(rocket);
                    }
                }else {
                    player.getCooldowns().addCooldown(this, 60);
                    if (!p_43395_.isClientSide) {
                        float d7 = p_43396_.getYRot();
                        float d = p_43396_.getXRot();
                        float d1 = -Mth.sin(d7 * ((float) Math.PI / 180F)) * Mth.cos(d * ((float) Math.PI / 180F));
                        float d2 = -Mth.sin(d * ((float) Math.PI / 180F));
                        float d3 = Mth.cos(d7 * ((float) Math.PI / 180F)) * Mth.cos(d * ((float) Math.PI / 180F));
                        double theta = d7 * (Math.PI / 180);
                        theta += Math.PI / 2;
                        double vecX = Math.cos(theta);
                        double vecZ = Math.sin(theta);
                        double x = p_43396_.getX() + vecX;
                        double Z = p_43396_.getZ() + vecZ;
                        Wither_Missile_Entity rocket = new Wither_Missile_Entity(ModEntities.WITHER_MISSILE.get(), x, p_43396_.getEyeY(), Z, d1, d2, d3, p_43395_);
                        p_43395_.addFreshEntity(rocket);
                    }
                }
            }
        }


    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }


    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) cataclysm.PROXY.getISTERProperties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    }
}
