package L_Ender.cataclysm.entity.effect;

import L_Ender.cataclysm.init.ModEffect;
import L_Ender.cataclysm.init.ModEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.UUID;

public class Flame_Strike_Entity extends Entity {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(Flame_Strike_Entity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> DATA_WAITING = SynchedEntityData.defineId(Flame_Strike_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(Flame_Strike_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final float MAX_RADIUS = 32.0F;
    private int duration = 600;
    private int waitTime = 20;
    private int durationOnUse;
    private float radiusOnUse;
    private float radiusPerTick;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    public Flame_Strike_Entity(EntityType<? extends Flame_Strike_Entity> p_19704_, Level p_19705_) {
        super(p_19704_, p_19705_);
        this.noPhysics = true;
        this.setRadius(3.0F);
    }

    public Flame_Strike_Entity(Level p_19707_, double p_19708_, double p_19709_, double p_19710_) {
        this(ModEntities.FLAME_STRIKE.get(), p_19707_);
        this.setPos(p_19708_, p_19709_, p_19710_);
    }

    protected void defineSynchedData() {
        this.getEntityData().define(DATA_RADIUS, 0.5F);
        this.getEntityData().define(DATA_WAITING, true);
        this.getEntityData().define(SOUL, false);
    }

    public void setRadius(float p_19713_) {
        if (!this.level.isClientSide) {
            this.getEntityData().set(DATA_RADIUS, Mth.clamp(p_19713_, 0.0F, 32.0F));
        }

    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    public float getRadius() {
        return this.getEntityData().get(DATA_RADIUS);
    }


    protected void setWaiting(boolean p_19731_) {
        this.getEntityData().set(DATA_WAITING, p_19731_);
    }

    public boolean isWaiting() {
        return this.getEntityData().get(DATA_WAITING);
    }

    public void setSoul(boolean Soul) {
        this.getEntityData().set(SOUL, Soul);
    }

    public boolean isSoul() {
        return this.getEntityData().get(SOUL);
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int p_19735_) {
        this.duration = p_19735_;
    }

    public void tick() {
        super.tick();
        boolean flag = this.isWaiting();
        float f = this.getRadius();
        if (this.level.isClientSide) {
            if (flag && this.random.nextBoolean()) {
                return;
            }
            ParticleOptions particleoptions = this.isSoul() ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME ;
            float f1 = flag ? 0.2F : f;
            double spread = Math.PI * 2 ;
            int arcLen = Mth.ceil(this.getRadius() * spread);

            if(!flag) {
                for (int j = 0; j < arcLen * 2; ++j) {
                    float f2 = this.random.nextFloat() * ((float) Math.PI * 2F);
                    double d0 = this.getX() + (double) (Mth.cos(f2) * f1) * 0.9;
                    double d2 = this.getY();
                    double d4 = this.getZ() + (double) (Mth.sin(f2) * f1) * 0.9;
                    this.level.addParticle(particleoptions, d0, d2, d4, random.nextGaussian() * 0.07D, 0.07D * this.getRadius() + 0.2D, random.nextGaussian() * 0.07D);
                }
            }
        } else {
            if (this.tickCount >= this.waitTime + this.duration) {
                this.discard();
                return;
            }

            boolean flag1 = this.tickCount < this.waitTime;
            if (flag != flag1) {
                this.setWaiting(flag1);
            }

            if (flag1) {
                return;
            }

            if (this.radiusPerTick != 0.0F) {
                f += this.radiusPerTick;
                if (f < 0.5F) {
                    this.discard();
                    return;
                }

                this.setRadius(f);
            }
        }

        if(!flag) {
            if (this.tickCount % 5 == 0) {
                for (LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
                    this.damage(livingentity);
                }
            }
        }
    }

    private void damage(LivingEntity Hitentity) {
        LivingEntity caster = this.getOwner();
        if (Hitentity.isAlive() && !Hitentity.isInvulnerable() && Hitentity != caster) {
            if (this.tickCount % 2 == 0) {
                float damage = this.isSoul() ? 8.0F : 6.0F;
                if (caster == null) {

                    boolean flag = Hitentity.hurt(DamageSource.MAGIC, damage + Hitentity.getMaxHealth() * 0.06f);
                    if (flag) {
                        MobEffectInstance effectinstance1 = Hitentity.getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                        int i = 1;
                        if (effectinstance1 != null) {
                            i += effectinstance1.getAmplifier();
                            Hitentity.removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                        } else {
                            --i;
                        }

                        i = Mth.clamp(i, 0, 4);
                        MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, i, false, false, true);
                        Hitentity.addEffect(effectinstance);
                    }
                } else {
                    if (caster.isAlliedTo(Hitentity)) {
                        return;
                    }
                    boolean flag = Hitentity.hurt(DamageSource.indirectMagic(this, caster), damage + Hitentity.getMaxHealth() * 0.06f);
                    if (flag) {
                        MobEffectInstance effectinstance1 = Hitentity.getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                        int i = 1;
                        if (effectinstance1 != null) {
                            i += effectinstance1.getAmplifier();
                            Hitentity.removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                        } else {
                            --i;
                        }

                        i = Mth.clamp(i, 0, 4);
                        MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, i, false, false, true);
                        Hitentity.addEffect(effectinstance);

                    }
                }
            }
        }
    }

    public float getRadiusOnUse() {
        return this.radiusOnUse;
    }

    public void setRadiusOnUse(float p_19733_) {
        this.radiusOnUse = p_19733_;
    }

    public float getRadiusPerTick() {
        return this.radiusPerTick;
    }

    public void setRadiusPerTick(float p_19739_) {
        this.radiusPerTick = p_19739_;
    }

    public int getDurationOnUse() {
        return this.durationOnUse;
    }

    public void setDurationOnUse(int p_146786_) {
        this.durationOnUse = p_146786_;
    }

    public int getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(int p_19741_) {
        this.waitTime = p_19741_;
    }

    public void setOwner(@Nullable LivingEntity p_19719_) {
        this.owner = p_19719_;
        this.ownerUUID = p_19719_ == null ? null : p_19719_.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    protected void readAdditionalSaveData(CompoundTag p_19727_) {
        this.tickCount = p_19727_.getInt("Age");
        this.duration = p_19727_.getInt("Duration");
        this.waitTime = p_19727_.getInt("WaitTime");
        this.durationOnUse = p_19727_.getInt("DurationOnUse");
        this.radiusOnUse = p_19727_.getFloat("RadiusOnUse");
        this.radiusPerTick = p_19727_.getFloat("RadiusPerTick");
        this.setRadius(p_19727_.getFloat("Radius"));
        if (p_19727_.hasUUID("Owner")) {
            this.ownerUUID = p_19727_.getUUID("Owner");
        }
        setSoul(p_19727_.getBoolean("is_soul"));

    }

    protected void addAdditionalSaveData(CompoundTag p_19737_) {
        p_19737_.putInt("Age", this.tickCount);
        p_19737_.putInt("Duration", this.duration);
        p_19737_.putInt("WaitTime", this.waitTime);
        p_19737_.putInt("DurationOnUse", this.durationOnUse);
        p_19737_.putFloat("RadiusOnUse", this.radiusOnUse);
        p_19737_.putFloat("RadiusPerTick", this.radiusPerTick);
        p_19737_.putFloat("Radius", this.getRadius());
        if (this.ownerUUID != null) {
            p_19737_.putUUID("Owner", this.ownerUUID);
        }
        p_19737_.putBoolean("is_soul", isSoul());


    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_19729_) {
        if (DATA_RADIUS.equals(p_19729_)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(p_19729_);
    }


    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public EntityDimensions getDimensions(Pose p_19721_) {
        return EntityDimensions.scalable(this.getRadius() * 1.8F, this.getRadius() * 3.0F);
    }
}

