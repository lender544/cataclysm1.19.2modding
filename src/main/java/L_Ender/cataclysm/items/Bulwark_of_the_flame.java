package L_Ender.cataclysm.items;


import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.entity.effect.Charge_Watcher_Entity;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;
import java.util.List;

public class Bulwark_of_the_flame extends Item {
    public Bulwark_of_the_flame(Item.Properties group) {
        super(group);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }

    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if(!entityLiving.isShiftKeyDown()) {
            if(!entityLiving.isFallFlying()) {
                int i = this.getUseDuration(stack) - timeLeft;
                int t = Mth.clamp(i, 1, 4);
                float f7 = entityLiving.getYRot();
                float f = entityLiving.getXRot();

                float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
                float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                float f5 = 3.0F * (t / 6.0F);
                f1 *= f5 / f4;
                f3 *= f5 / f4;
                entityLiving.push((double) f1, (double) 0, (double) f3);
                if (entityLiving.isOnGround()) {
                    float f6 = 1.1999999F;
                    entityLiving.move(MoverType.SELF, new Vec3(0.0D, (double) f6 / 2, 0.0D));
                }
                if (!level.isClientSide) {
                    Charge_Watcher_Entity initializer = new Charge_Watcher_Entity(entityLiving.level, entityLiving.blockPosition(), t * 2,
                            t * 0.25, 2, 0.5F,
                            f1 * 0.5F, f3 * 0.5F,
                            entityLiving);
                    level.addFreshEntity(initializer);
                   // ((Player) entityLiving).getCooldowns().addCooldown(this, 100);
                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        ItemStack lvt_4_1_ = p_77659_2_.getItemInHand(p_77659_3_);
        p_77659_2_.startUsingItem(p_77659_3_);
        return InteractionResultHolder.consume(lvt_4_1_);
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) cataclysm.PROXY.getISTERProperties());
    }
}