package com.github.L_Ender.cataclysm.entity.effect;

import com.github.L_Ender.cataclysm.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class Charge_Watcher_Entity extends Entity {
    static final EntityDataAccessor<Integer> TIMER = SynchedEntityData.defineId(Charge_Watcher_Entity.class, EntityDataSerializers.INT);
    int effectiveChargeTime;
    double dx;
    double dz;
    float damagePerEffectiveCharge;
    double speedIndex;
    double knockbackSpeedIndex;
    LivingEntity source;
    boolean stopTracking = false;


    public Charge_Watcher_Entity(EntityType<? extends Charge_Watcher_Entity> entityTypeIn, Level level) {
        super(entityTypeIn, level);
    }

    public Charge_Watcher_Entity(Level level, BlockPos pos, int effectiveChargeTime, double knockbackSpeedIndex, double speedIndex, float damagePerEffectiveCharge, double dx, double dz, LivingEntity source) {
        super(ModEntities.CHARGE_WATCHER.get(), level);
        setPos(pos.getX(), pos.getY(), pos.getZ());
        entityData.set(TIMER, effectiveChargeTime);
        this.effectiveChargeTime = effectiveChargeTime;
        this.knockbackSpeedIndex = knockbackSpeedIndex;
        this.damagePerEffectiveCharge = damagePerEffectiveCharge;
        this.dx = dx;
        this.dz = dz;
        this.source = source;
        this.speedIndex = speedIndex;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            int temp = entityData.get(TIMER);

            //Deal with rocket punch is valid
            if (temp > 0 && !stopTracking && source != null) {
                //Slightly enlarge player's hitbox
                AABB collideBox = source.getBoundingBox().inflate(3.5f, 3.5f, 3.5f);

                //Collision Detection
                List<LivingEntity> checks = level.getEntitiesOfClass(LivingEntity.class, collideBox);
                checks.remove(source);

                //If any mob is detected
                if (!checks.isEmpty()) {
                    // spawn an watchEntity to simulate rocket punch effect
                    Wall_Watcher_Entity watchEntity = new Wall_Watcher_Entity(level, source.blockPosition(), temp, effectiveChargeTime,
                            knockbackSpeedIndex, damagePerEffectiveCharge, dx, dz,
                            source);
                    for (LivingEntity target : checks) {
                        // Deal damage
                        boolean flag = target.hurt(DamageSource.indirectMobAttack(this,source), damagePerEffectiveCharge * effectiveChargeTime);
                        watchEntity.watch(target);
                        if(flag){
                            target.playSound(SoundEvents.ANVIL_LAND, 1.5f, 0.8F);
                        }

                    }
                    source.level.addFreshEntity(watchEntity);

                    // Player stop moving and clear pocket punch status
                    source.setDeltaMovement(0, 0, 0);
                    source.hurtMarked = true;
                    stopTracking = true;
                }

                // If rocket punch is active and player hit a wall
                // stop player and clear rocket punch status
                if (source.horizontalCollision) {
                    stopTracking = true;
                }

                // Deal with player rocket punch movement
                if (!stopTracking) {
                    entityData.set(TIMER, temp - 1);
                }
            }

            if (stopTracking || source == null || temp == 0) {
                discard();
            }
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(TIMER, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        source = null;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
