package L_Ender.cataclysm.entity.projectile;

import L_Ender.cataclysm.init.ModEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class Ignis_Fireball_Entity extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(Ignis_Fireball_Entity.class, EntityDataSerializers.BOOLEAN);

    public Ignis_Fireball_Entity(EntityType<? extends Ignis_Fireball_Entity> type, Level level) {
        super(type, level);
    }

    protected float getInertia() {
        return this.isSoul() ? 1.1F : 0.95F;
    }

    public boolean isOnFire() {
        return false;
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            Entity entity = result.getEntity();
            Entity shooter = this.getOwner();
            boolean flag;
            if (shooter instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)shooter;
                if(this.isSoul()) {
                    flag = entity.hurt(DamageSource.indirectMagic(this, livingentity), 8.0F);
                }else{
                    flag = entity.hurt(DamageSource.indirectMagic(this, livingentity), 6.0F);
                }
                if (flag) {
                    if (entity.isAlive()) {
                        this.doEnchantDamageEffects(livingentity, entity);
                    } else {
                        livingentity.heal(5.0F);
                    }
                }
            } else {
                flag = entity.hurt(DamageSource.MAGIC, 5.0F);
            }

            if (flag && entity instanceof LivingEntity) {
                MobEffectInstance effectinstance1 = ((LivingEntity)entity).getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                int i = 1;
                if (effectinstance1 != null) {
                    i += effectinstance1.getAmplifier();
                    ((LivingEntity)entity).removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                } else {
                    --i;
                }

                i = Mth.clamp(i, 0, 4);
                MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 150, i, false, false, true);
                ((LivingEntity)entity).addEffect(effectinstance);

            }

        }
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, true, explosion$blockinteraction);
            this.discard();
        }

    }

    public boolean isPickable() {
        return false;
    }

    public boolean hurt(DamageSource p_37616_, float p_37617_) {
        return false;
    }

    protected void defineSynchedData() {
        this.entityData.define(SOUL, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("is_soul", isSoul());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setSoul(compound.getBoolean("is_soul"));
    }

    public boolean isSoul() {
        return this.entityData.get(SOUL);
    }

    public void setSoul(boolean IsSoul) {
        this.entityData.set(SOUL, IsSoul);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}


