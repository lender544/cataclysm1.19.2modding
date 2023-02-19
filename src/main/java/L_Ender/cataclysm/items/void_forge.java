package L_Ender.cataclysm.items;


import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.entity.projectile.Void_Rune_Entity;
import L_Ender.cataclysm.init.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.List;

public class void_forge extends PickaxeItem {
    public void_forge(Tiers toolMaterial, Properties props) {

        super(toolMaterial, 8, -3.2f, props);
    }

    @Override
    public boolean hurtEnemy(ItemStack heldItemStack, LivingEntity target, LivingEntity attacker) {
        if (!target.level.isClientSide) {
            target.playSound(ModSounds.HAMMERTIME.get(), 0.5F, 0.5F);
            target.knockback( 1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        int standingOnY = Mth.floor(player.getY()) - 3;
        Level world = context.getLevel();
        if (player.getMainHandItem() == stack) {
            Vec3 looking = player.getLookAngle();
            double headY = player.getY() + 1.0D;
            Vec3[] all = new Vec3[]{looking, looking.yRot(0.3f), looking.yRot(-0.3f), looking.yRot(0.6f), looking.yRot(-0.6f), looking.yRot(0.9f), looking.yRot(-0.9f)};
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.5f, 1F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
            ScreenShake_Entity.ScreenShake(world, player.position(), 30, 0.1f, 0, 30);
            for (Vec3 vector3d : all) {
                float f = (float) Mth.atan2(vector3d.z, vector3d.x);
                player.getCooldowns().addCooldown(this, CMConfig.VoidForgeCooldown);
                for (int i = 0; i < 4; i++) {
                    double d2 = 1.75D * (double) (i + 1);
                    int j = 2 * i;
                    this.spawnFangs(player.getX() + (double) Mth.cos(f) * d2, headY, player.getZ() + (double) Mth.sin(f) * d2, standingOnY, f, j, world, player);
                }
            }

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }


    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }


    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack itemStackMaterial) {
        return false;
    }

    @Override
    public int getEnchantmentValue() {
        return 16;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category ==  EnchantmentCategory.WEAPON && enchantment != Enchantments.SWEEPING_EDGE
                || enchantment.category == EnchantmentCategory.DIGGER;
    }

    private boolean spawnFangs(double x, double y, double z, int lowestYCheck, float yRot, int warmupDelayTicks, Level world, Player player) {
        BlockPos blockpos = new BlockPos(x, y, z);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = world.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(world, blockpos1, Direction.UP)) {
                if (!world.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(world, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while (blockpos.getY() >= lowestYCheck);

        if (flag) {
            world.addFreshEntity(new Void_Rune_Entity(world, x, (double) blockpos.getY() + d0, z, yRot, warmupDelayTicks, player));
            return true;
        }
        return false;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) cataclysm.PROXY.getISTERProperties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.cataclysm.void_forge.desc").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.cataclysm.void_forge.desc2").withStyle(ChatFormatting.DARK_GREEN));
    }
}







