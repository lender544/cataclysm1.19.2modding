package L_Ender.cataclysm.event;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.client.render.CMItemstackRenderer;
import L_Ender.cataclysm.init.ModEffect;
import L_Ender.cataclysm.init.ModItems;
import L_Ender.cataclysm.items.final_fractal;
import L_Ender.cataclysm.items.zweiender;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = cataclysm.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {



    @SubscribeEvent
    public void onLivingDamage(LivingHurtEvent event) {
        if (event.getSource() instanceof EntityDamageSource) {
            if (event.getSource().getEntity() instanceof LivingEntity) {
                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
                LivingEntity target = event.getEntity();
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
        //  if(event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())){
         //   event.getEntity().removeEffect(ModEffect.EFFECTSTUN.get());
        //}
    }
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (!event.getEntity().getUseItem().isEmpty() && event.getSource() != null && event.getSource().getEntity() != null) {
            if (event.getEntity().getUseItem().getItem() == ModItems.BULWARK_OF_THE_FLAME.get()) {
                Entity attacker = event.getSource().getEntity();
                if (attacker instanceof LivingEntity) {
                    if (attacker.distanceTo(event.getEntity()) <= 4 && !attacker.isOnFire()) {
                        attacker.setSecondsOnFire(5);

                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
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
    public void onLivingSetTargetEvent(LivingSetAttackTargetEvent  event) {
        if (event.getTarget() != null && event.getEntity() instanceof Mob) {
            if (event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
                ((Mob) event.getEntity()).setTarget(null);
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getEffect(ModEffect.EFFECTSTUN.get()) != null){
            entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.0D, entity.getDeltaMovement().z());
        }
    }

    @SubscribeEvent
    public void onPlayerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (event.isCancelable() && player.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent event) {
        LivingEntity living = event.getEntity();
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
        LivingEntity living = event.getEntity();
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
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getHealth() <= event.getAmount() && entity.hasEffect(ModEffect.EFFECTSTUN.get())) {
            entity.removeEffect(ModEffect.EFFECTSTUN.get());
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderLevelLastEvent event) {
        CMItemstackRenderer.incrementTick();

    }

}


