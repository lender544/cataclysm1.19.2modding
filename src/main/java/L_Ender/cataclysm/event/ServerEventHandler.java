package L_Ender.cataclysm.event;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.init.ModEffect;
import L_Ender.cataclysm.init.ModItems;
import L_Ender.cataclysm.items.final_fractal;
import L_Ender.cataclysm.items.zweiender;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = cataclysm.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {



    @SubscribeEvent
    public void onLivingDamage(LivingHurtEvent event) {
        if (event.getSource() instanceof EntityDamageSource) {
            if (event.getSource().getEntity() instanceof LivingEntity) {
                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
                LivingEntity target = event.getEntityLiving();
                ItemStack weapon = attacker.getMainHandItem();
                if (weapon != null && weapon.getItem() instanceof zweiender) {
                    Vec3 lookDir = new Vec3(target.getLookAngle().x, 0, target.getLookAngle().z).normalize();
                    Vec3 vecBetween = new Vec3(target.getX() - attacker.getX(), 0, target.getZ() - attacker.getZ()).normalize();
                    double dot = lookDir.dot(vecBetween);
                    if (dot > 0.05) {
                        event.setAmount(event.getAmount() * 2);
                        target.playSound(SoundEvents.ENDERMAN_TELEPORT, 0.75F, 0.5F);
                    }
                }
                if (weapon != null && weapon.getItem() instanceof final_fractal) {
                    event.setAmount(event.getAmount() + target.getMaxHealth() * 0.03f);
                }
            }
        }
        //  if(event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())){
         //   event.getEntityLiving().removeEffect(ModEffect.EFFECTSTUN.get());
        //}
    }
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (!event.getEntityLiving().getUseItem().isEmpty() && event.getSource() != null && event.getSource().getEntity() != null) {
            if (event.getEntityLiving().getUseItem().getItem() == ModItems.BULWARK_OF_THE_FLAME.get()) {
                Entity attacker = event.getSource().getEntity();
                if (attacker instanceof LivingEntity) {
                    if (attacker.distanceTo(event.getEntityLiving()) <= 4 && !attacker.isOnFire()) {
                        attacker.setSecondsOnFire(5);

                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

  //  @SubscribeEvent
  //  public static void modifiyVisibility(LivingEvent.LivingVisibilityEvent event) {
   //     if (event.getLookingEntity() instanceof LivingEntity living) {
    //        if (living.hasEffect(ModEffect.EFFECTSTUN.get()))
     //           event.modifyVisibility(0.01);
      //  }
    //}

    @SubscribeEvent
    public void onLivingSetTargetEvent(LivingSetAttackTargetEvent event) {
        if (event.getTarget() != null && event.getEntityLiving() instanceof Mob) {
            if (event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
                ((Mob) event.getEntityLiving()).setTarget(null);
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.getEffect(ModEffect.EFFECTSTUN.get()) != null){
            entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.0D, entity.getDeltaMovement().z());
        }
    }

    @SubscribeEvent
    public void onPlayerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getPlayer();
        if (event.isCancelable() && player.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlaceBlock(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onFillBucket(FillBucketEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (living != null) {
            if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (event.isCancelable() && event.getPlayer().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.getHealth() <= event.getAmount() && entity.hasEffect(ModEffect.EFFECTSTUN.get())) {
            entity.removeEffect(ModEffect.EFFECTSTUN.get());
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        if (event.isCancelable() && event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving().level.isClientSide && (event.getEntityLiving().hasEffect(ModEffect.EFFECTSTUN.get()))) {
            for (int i = 0; i < 5; i++) {
                float innerAngle = (0.01745329251F * (event.getEntityLiving().yBodyRot + event.getEntityLiving().tickCount * 5) * (i + 1));
                double extraX = 0.5F * Mth.sin((float) (Math.PI + innerAngle));
                double extraZ = 0.5F * Mth.cos(innerAngle);
                event.getEntityLiving().level.addParticle(ParticleTypes.CRIT, true, event.getEntityLiving().getX() + extraX, event.getEntityLiving().getEyeY() + 0.5F, event.getEntityLiving().getZ() + extraZ, 0, 0, 0);
            }
        }
    }
}


