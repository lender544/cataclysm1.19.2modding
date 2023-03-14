package com.github.L_Ender.cataclysm.entity;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Boss_monster extends Monster implements IAnimatedEntity {
    private int animationTick;
    private Animation currentAnimation;
    protected boolean dropAfterDeathAnim = true;
    private int killDataRecentlyHit;
    private DamageSource killDataCause;
    private Player killDataAttackingPlayer;

    @OnlyIn(Dist.CLIENT)
    public Vec3[] socketPosArray;

    public Boss_monster(EntityType entity, Level world) {
        super(entity, world);
        if (world.isClientSide) {
            socketPosArray = new Vec3[]{};
        }
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        boolean attack = super.hurt(source, damage);
        if (attack) {
            if (getHealth() <= 0.0F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, getDeathAnimation());
            }
        }
        return attack;
    }

    public static void setConfigattribute(LivingEntity entity, double hpconfig, double dmgconfig) {
        AttributeInstance maxHealthAttr = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            double difference = maxHealthAttr.getBaseValue() * hpconfig - maxHealthAttr.getBaseValue();
            maxHealthAttr.addTransientModifier(new AttributeModifier(UUID.fromString("9513569b-57b6-41f5-814e-bdc49b81799f"), "Health config multiplier", difference, AttributeModifier.Operation.ADDITION));
            entity.setHealth(entity.getMaxHealth());
        }
        AttributeInstance attackDamageAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttr != null) {
            double difference = attackDamageAttr.getBaseValue() * dmgconfig - attackDamageAttr.getBaseValue();
            attackDamageAttr.addTransientModifier(new AttributeModifier(UUID.fromString("5b17d7cb-294e-4379-88ab-136c372bec9b"), "Attack config multiplier", difference, AttributeModifier.Operation.ADDITION));

        }
    }

    public double calculateRange(DamageSource damagesource) {
        return damagesource.getEntity() != null ? distanceToSqr(damagesource.getEntity()) : -1;
    }

    public double getAngleBetweenEntities(Entity first, Entity second) {
        return Math.atan2(second.getZ() - first.getZ(), second.getX() - first.getX()) * (180 / Math.PI) + 90;
    }

    public  List<LivingEntity> getEntityLivingBaseNearby(double distanceX, double distanceY, double distanceZ, double radius) {
        return getEntitiesNearby(LivingEntity.class, distanceX, distanceY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double dX, double dY, double dZ, double r) {
        return level.getEntitiesOfClass(entityClass, getBoundingBox().inflate(dX, dY, dZ), e -> e != this && distanceTo(e) <= r + e.getBbWidth() / 2f && e.getY() <= getY() + dY);
    }

    public static void disableShield(LivingEntity livingEntity, int ticks) {
        ((Player)livingEntity).getCooldowns().addCooldown(livingEntity.getUseItem().getItem(), ticks);
        livingEntity.stopUsingItem();
        livingEntity.level.broadcastEntityEvent(livingEntity, (byte)30);
    }


    protected void onAnimationFinish(Animation animation) {}

    @Override
    public void baseTick() {
        super.baseTick();
        if (getHealth() <= 0.0F) {
            Animation death;
            if ((death = getDeathAnimation()) != null) {
                onDeathUpdate(death.getDuration() - 20);
            } else {
                onDeathUpdate(20);
            }
        }
    }

    protected void onDeathAIUpdate() {}

    @Override
    protected final void tickDeath() {}

    private void onDeathUpdate(int deathDuration) { // TODO copy from entityLiving
        onDeathAIUpdate();

        ++this.deathTime;
        if (this.deathTime == deathDuration) {
            lastHurtByPlayer = killDataAttackingPlayer;
            lastHurtByPlayerTime = killDataRecentlyHit;
            if (!this.level.isClientSide && dropAfterDeathAnim && killDataCause != null) {
                dropAllDeathLoot(killDataCause);
            }

            this.discard();

            for(int i = 0; i < 20; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
            }
        }
    }

    @Override
    public void die(DamageSource cause) // TODO copy from entityLiving
    {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;
        if (!this.dead) {
            Entity entity = cause.getEntity();
            LivingEntity livingentity = this.getTarget();
            if (this.deathScore >= 0 && livingentity != null) {
                livingentity.awardKillScore(this, this.deathScore, cause);
            }

            if (this.isSleeping()) {
                this.stopSleeping();
            }

            this.dead = true;
            this.getCombatTracker().recheckStatus();
            if (this.level instanceof ServerLevel) {
                if (entity == null || entity.wasKilled((ServerLevel)this.level, this)) {
                    this.gameEvent(GameEvent.ENTITY_DIE);
                    this.createWitherRose(livingentity);
                    if (!dropAfterDeathAnim){
                        this.dropAllDeathLoot(cause);
                    }
                }
            }
            killDataCause = cause;
            killDataRecentlyHit = this.lastHurtByPlayerTime;
            killDataAttackingPlayer = lastHurtByPlayer;

            this.level.broadcastEntityEvent(this, (byte)3);
            this.setPose(Pose.DYING);
        }
    }

    public void circleEntity(Entity target, float radius, float speed, boolean direction, int circleFrame, float offset, float moveSpeedMultiplier) {
        int directionInt = direction ? 1 : -1;
        double t = directionInt * circleFrame * 0.5 * speed / radius + offset;
        Vec3 movePos = target.position().add(radius * Math.cos(t), 0, radius * Math.sin(t));
        this.getNavigation().moveTo(movePos.x, movePos.y, movePos.z, speed * moveSpeedMultiplier);
    }

    protected void repelEntities(float x, float y, float z, float radius) {
        List<LivingEntity> nearbyEntities = getEntityLivingBaseNearby(x, y, z, radius);
        for (Entity entity : nearbyEntities) {
            if (entity.isPickable() && !entity.noPhysics) {
                double angle = (getAngleBetweenEntities(this, entity) + 90) * Math.PI / 180;
                entity.setDeltaMovement(-0.1 * Math.cos(angle), entity.getDeltaMovement().y, -0.1 * Math.sin(angle));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void setSocketPosArray(int index, Vec3 pos) {
        if (socketPosArray != null && socketPosArray.length > index) {
            socketPosArray[index] = pos;
        }
    }

    public boolean canBePushedByEntity(Entity entity) {
        return true;
    }

    // TODO: Copied from parent classes
    @Override
    public void push(Entity entityIn) {
        if (!this.isSleeping()) {
            if (!this.isPassengerOfSameVehicle(entityIn)) {
                if (!entityIn.noPhysics && !this.noPhysics) {
                    double d0 = entityIn.getX() - this.getX();
                    double d1 = entityIn.getZ() - this.getZ();
                    double d2 = Mth.absMax(d0, d1);
                    if (d2 >= (double)0.01F) {
                        d2 = Mth.sqrt((float) d2);
                        d0 = d0 / d2;
                        d1 = d1 / d2;
                        double d3 = 1.0D / d2;
                        if (d3 > 1.0D) {
                            d3 = 1.0D;
                        }

                        d0 = d0 * d3;
                        d1 = d1 * d3;
                        d0 = d0 * (double)0.05F;
                        d1 = d1 * (double)0.05F;
                        // d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
                        // d1 = d1 * (double)(1.0F - this.entityCollisionReduction);
                        if (!this.isVehicle()) {
                            if (canBePushedByEntity(entityIn)) {
                                this.push(-d0, 0.0D, -d1);
                            }
                        }

                        if (!entityIn.isVehicle()) {
                            entityIn.push(d0, 0.0D, d1);
                        }
                    }

                }
            }
        }
    }


    public BossEvent.BossBarColor bossBarColor() {
        return BossEvent.BossBarColor.PURPLE;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION};
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }


    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.currentAnimation;
    }


    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.currentAnimation);
        }
        this.currentAnimation = animation;
        setAnimationTick(0);
    }

    public boolean canBeAffected(MobEffectInstance p_34192_) {
        return p_34192_.getEffect() != ModEffect.EFFECTSTUN.get() && super.canBeAffected(p_34192_);
    }

    @Nullable
    public Animation getDeathAnimation()
    {
        return null;
    }

    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    protected boolean canRide(Entity p_31508_) {
        return false;
    }
}
