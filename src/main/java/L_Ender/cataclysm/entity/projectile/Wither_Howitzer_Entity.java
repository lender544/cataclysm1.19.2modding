package L_Ender.cataclysm.entity.projectile;

import L_Ender.cataclysm.entity.effect.Wither_Smoke_Effect_Entity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class Wither_Howitzer_Entity extends ThrowableProjectile {
    public Wither_Howitzer_Entity(EntityType<Wither_Howitzer_Entity> type, Level world) {
        super(type, world);
    }

    public Wither_Howitzer_Entity(EntityType<Wither_Howitzer_Entity> type, Level world, LivingEntity thrower) {
        super(type, thrower, world);
    }

    @Override
    protected void defineSynchedData() {

    }


    protected void onHitEntity(EntityHitResult p_37626_) {
        super.onHitEntity(p_37626_);
        if (!this.level.isClientSide) {
            Entity entity = p_37626_.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag;
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity1;
                flag = entity.hurt(DamageSource.indirectMobAttack(this, livingentity).setProjectile(), 8.0F);
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
                int i = 10;
                if (this.level.getDifficulty() == Difficulty.NORMAL) {
                    i = 20;
                } else if (this.level.getDifficulty() == Difficulty.HARD) {
                    i = 30;
                }

                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * i, 1), this.getEffectSource());
            }

        }
    }

    protected void onHit(HitResult p_37628_) {
        super.onHit(p_37628_);
        if (!this.level.isClientSide) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2.0F, false, Explosion.BlockInteraction.NONE);
            Wither_Smoke_Effect_Entity areaeffectcloud = new Wither_Smoke_Effect_Entity(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(3.5F);
            LivingEntity entity1 = (LivingEntity) this.getOwner();
            areaeffectcloud.setOwner(entity1);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
            this.level.addFreshEntity(areaeffectcloud);
            this.discard();
        }
    }


    @Override
    public void tick() {
        super.tick();

        if (this.level.isClientSide) {
            Vec3 vec3 = this.getDeltaMovement();
            level.addParticle(ParticleTypes.FLAME, this.getX() - vec3.x, this.getY() - vec3.y, this.getZ() - vec3.z, 0, 0, 0);
            level.addParticle(ParticleTypes.SMOKE, this.getX() - vec3.x, this.getY() - vec3.y, this.getZ() - vec3.z, 0, 0, 0);
        }

    }


    @Override
    protected float getGravity() {
        return 0.03F;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
