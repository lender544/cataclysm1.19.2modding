package L_Ender.cataclysm.entity.projectile;

import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import L_Ender.cataclysm.entity.effect.Smoke_Effect_Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Smoke_Breath_Entity extends Entity {
    private static final int RANGE = 7;
    private static final int ARC = 45;
    private LivingEntity caster;
    private UUID casterUuid;




    public Smoke_Breath_Entity(EntityType<? extends Smoke_Breath_Entity> type, Level world) {
        super(type, world);

    }

    public Smoke_Breath_Entity(EntityType<? extends Smoke_Breath_Entity> type, Level world, LivingEntity caster) {
        super(type, world);
        this.setCaster(caster);

    }

    @Override
    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    @Override
    public void tick() {
        super.tick();

        if (caster != null && !caster.isAlive()) this.discard();

        if (caster !=null){
            this.setYRot(caster.yHeadRot);
            this.setXRot(caster.getXRot());
        }
        float yaw = (float) Math.toRadians(-getYRot());
        float pitch = (float) Math.toRadians(-getXRot());
        float spread = 0.25f;
        float speed = 0.56f;
        float xComp = (float) (Math.sin(yaw) * Math.cos(pitch));
        float yComp = (float) (Math.sin(pitch));
        float zComp = (float) (Math.cos(yaw) * Math.cos(pitch));
        if (level.isClientSide) {
            for (int i = 0; i < 80; i++) {
                double xSpeed = speed * xComp + (spread * 1 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - xComp * xComp)));
                double ySpeed = speed * yComp + (spread * 1 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - yComp * yComp)));
                double zSpeed = speed * zComp + (spread * 1 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - zComp * zComp)));
                level.addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
            }
            for (int i = 0; i < 2; i++) {
                double xSpeed = speed * xComp + (spread * 0.7 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - xComp * xComp)));
                double ySpeed = speed * yComp + (spread * 0.7 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - yComp * yComp)));
                double zSpeed = speed * zComp + (spread * 0.7 * (random.nextFloat() * 2 - 1) * (Math.sqrt(1 - zComp * zComp)));
                level.addParticle(ParticleTypes.FLAME, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
            }
        }
        if (tickCount > 2) {
            hitEntities();
        }
        if (tickCount > 25) discard() ;
    }

    public void hitEntities() {
        List<LivingEntity> entitiesHit = getEntityLivingBaseNearby(RANGE, RANGE, RANGE, RANGE);;
        for (LivingEntity entityHit : entitiesHit) {
            if (entityHit == caster) continue;

            if (entityHit instanceof EnderDragon) continue;

            float entityHitYaw = (float) ((Math.atan2(entityHit.getZ() - getZ(), entityHit.getX() - getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingYaw = getYRot() % 360;
            if (entityHitYaw < 0) {
                entityHitYaw += 360;
            }
            if (entityAttackingYaw < 0) {
                entityAttackingYaw += 360;
            }
            float entityRelativeYaw = entityHitYaw - entityAttackingYaw;

            float xzDistance = (float) Math.sqrt((entityHit.getZ() - getZ()) * (entityHit.getZ() - getZ()) + (entityHit.getX() - getX()) * (entityHit.getX() - getX()));
            double hitY = entityHit.getY() + entityHit.getBbHeight() / 2.0;
            float entityHitPitch = (float) ((Math.atan2((hitY - getY()), xzDistance) * (180 / Math.PI)) % 360);
            float entityAttackingPitch = -getXRot() % 360;
            if (entityHitPitch < 0) {
                entityHitPitch += 360;
            }
            if (entityAttackingPitch < 0) {
                entityAttackingPitch += 360;
            }
            float entityRelativePitch = entityHitPitch - entityAttackingPitch;

            float entityHitDistance = (float) Math.sqrt((entityHit.getZ() - getZ()) * (entityHit.getZ() - getZ()) + (entityHit.getX() - getX()) * (entityHit.getX() - getX()) + (hitY - getY()) * (hitY - getY()));
            int distance = this.tickCount / 2 ;
            boolean inRange = entityHitDistance <= distance + 1.0F;
            boolean yawCheck = (entityRelativeYaw <= ARC / 2f && entityRelativeYaw >= -ARC / 2f) || (entityRelativeYaw >= 360 - ARC / 2f || entityRelativeYaw <= -360 + ARC / 2f);
            boolean pitchCheck = (entityRelativePitch <= ARC / 2f && entityRelativePitch >= -ARC / 2f) || (entityRelativePitch >= 360 - ARC / 2f || entityRelativePitch <= -360 + ARC / 2f);
            boolean CloseCheck = caster instanceof Ignited_Revenant_Entity && entityHitDistance <= 2;
            if (inRange && yawCheck && pitchCheck || CloseCheck) {
                if (!raytraceCheckEntity(entityHit)) continue;
                if (entityHit.hurt(DamageSource.indirectMobAttack(this,caster), 6)) {
                    //entityHit.setDeltaMovement(entityHit.getDeltaMovement().multiply(0.25, 1, 0.25));
                    MobEffectInstance effectinstance = new MobEffectInstance(MobEffects.BLINDNESS, 160, 0, false, false, true);
                    MobEffectInstance effectinstance1 = new MobEffectInstance(MobEffects.CONFUSION, 160, 0, false, false, true);
                    //entityHit.addEffect(effectinstance);
                    //entityHit.addEffect(effectinstance1);
                }
            }
        }
    }


    public boolean raytraceCheckEntity(Entity entity) {
        Vec3 from = this.position();
        int numChecks = 3;
        for (int i = 0; i < numChecks; i++) {
            float increment = entity.getBbHeight() / (numChecks + 1);
            Vec3 to = entity.position().add(0, increment * (i + 1), 0);
            BlockHitResult result = level.clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (result.getType() != HitResult.Type.BLOCK) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void defineSynchedData() {

    }

    public void setCaster(@Nullable LivingEntity p_190549_1_) {
        this.caster = p_190549_1_;
        this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUUID();
    }

    @Nullable
    public LivingEntity getCaster() {
        if (this.caster == null && this.casterUuid != null && this.level instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level).getEntity(this.casterUuid);
            if (entity instanceof LivingEntity) {
                this.caster = (LivingEntity)entity;
            }
        }

        return this.caster;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.hasUUID("Owner")) {
            this.casterUuid = compound.getUUID("Owner");
        }

    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        if (this.casterUuid != null) {
            compound.putUUID("Owner", this.casterUuid);
        }

    }


    @Override
    public boolean isPickable() {
        return false;
    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public void push(Entity entityIn) {
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    public  List<LivingEntity> getEntityLivingBaseNearby(double distanceX, double distanceY, double distanceZ, double radius) {
        return getEntitiesNearby(LivingEntity.class, distanceX, distanceY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double dX, double dY, double dZ, double r) {
        return level.getEntitiesOfClass(entityClass, getBoundingBox().inflate(dX, dY, dZ), e -> e != this && distanceTo(e) <= r + e.getBbWidth() / 2f && e.getY() <= getY() + dY);
    }

    @Override
    public boolean isPushable() {
        return false;
    }


}