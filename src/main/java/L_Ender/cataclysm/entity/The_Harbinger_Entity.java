package L_Ender.cataclysm.entity;

import L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import L_Ender.cataclysm.entity.projectile.Death_Laser_Beam_Entity;
import L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity;
import L_Ender.cataclysm.entity.projectile.Wither_Missile_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModParticle;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;


public class The_Harbinger_Entity extends Boss_monster implements RangedAttackMob {
    public static final Animation DEATHLASER_ANIMATION = Animation.create(74);
    public static final Animation CHARGE_ANIMATION = Animation.create(39);
    public static final Animation DEATH_ANIMATION = Animation.create(144);
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);
    private static final EntityDataAccessor<Boolean> LASER_MODE = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.BOOLEAN);
    public static final int MODE_CHANGE_COOLDOWN = 300;
    private final float[] xRotHeads = new float[2];
    private final float[] yRotHeads = new float[2];
    private final float[] xRotOHeads = new float[2];
    private final float[] yRotOHeads = new float[2];
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];
    public float Laser_Mode_Progress;
    public float prev_Laser_Mode_Progress;

    private int mode_change_cooldown = 0;


    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (p_31504_) -> {
        return p_31504_.attackable();
    };
    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(LIVING_ENTITY_SELECTOR);


    public The_Harbinger_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,DEATHLASER_ANIMATION,CHARGE_ANIMATION,DEATH_ANIMATION};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new DeathLaserGoal(this,DEATHLASER_ANIMATION));
        this.goalSelector.addGoal(1, new ChargeGoal(this,CHARGE_ANIMATION));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
    }

    public static AttributeSupplier.Builder harbinger() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.6F)
                .add(Attributes.FLYING_SPEED, (double)0.6F)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 4.0D);
    }


    protected int decreaseAirSupply(int air) {
        return air;
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    public ItemEntity spawnAtLocation(ItemStack stack) {
        ItemEntity itementity = this.spawnAtLocation(stack,0.0f);
        if (itementity != null) {
            itementity.setGlowingTag(true);
            itementity.setExtendedLifetime();
        }
        return itementity;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LASER_MODE, false);;
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void aiStep() {
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);

        prev_Laser_Mode_Progress = Laser_Mode_Progress;
        if (this.getIsLaserMode() && Laser_Mode_Progress < 20f) {
            Laser_Mode_Progress++;
        }
        if (!this.getIsLaserMode() && Laser_Mode_Progress > 0F) {
            Laser_Mode_Progress--;
        }

        if(this.getAnimation() == NO_ANIMATION){
            //this.setAnimation(DEATHLASER_ANIMATION);
        }
        if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0 ) {
            Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double d0 = vec3.y;
                if ( this.getY() < entity.getY() + 5.0D) {
                    d0 = Math.max(0.0D, d0);
                    d0 += 0.3D - d0 * (double)0.6F;
                }

                vec3 = new Vec3(vec3.x, d0, vec3.z);
                Vec3 vec31 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                if (vec31.horizontalDistanceSqr() > 9.0D) {
                    Vec3 vec32 = vec31.normalize();
                    vec3 = vec3.add(vec32.x * 0.3D - vec3.x * 0.6D, 0.0D, vec32.z * 0.3D - vec3.z * 0.6D);
                }
            }
        }

        this.setDeltaMovement(vec3);
        if (vec3.horizontalDistanceSqr() > 0.05D) {
            this.setYRot((float)Mth.atan2(vec3.z, vec3.x) * (180F / (float)Math.PI) - 90.0F);
        }


        super.aiStep();
        AnimationHandler.INSTANCE.updateAnimations(this);
        for(int i = 0; i < 2; ++i) {
            this.yRotOHeads[i] = this.yRotHeads[i];
            this.xRotOHeads[i] = this.xRotHeads[i];
        }

        for(int j = 0; j < 2; ++j) {
            int k = this.getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level.getEntity(k);
            }

            if (entity1 != null) {
                double d9 = this.getHeadX(j + 1);
                double d1 = this.getHeadY(j + 1);
                double d3 = this.getHeadZ(j + 1);
                double d4 = entity1.getX() - d9;
                double d5 = entity1.getEyeY() - d1;
                double d6 = entity1.getZ() - d3;
                double d7 = Math.sqrt(d4 * d4 + d6 * d6);
                float f = (float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)) - 90.0F;
                float f1 = (float)(-(Mth.atan2(d5, d7) * (double)(180F / (float)Math.PI)));
                this.xRotHeads[j] = this.m_31442_(this.xRotHeads[j], f1, 40.0F);
                this.yRotHeads[j] = this.m_31442_(this.yRotHeads[j], f, 10.0F);
            } else {
                this.yRotHeads[j] = this.m_31442_(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }
        }
        if (this.level.isClientSide) {
            double d0 = (random.nextFloat() - 0.5F) + this.getDeltaMovement().x;
            double d1 = (random.nextFloat() - 0.5F) + this.getDeltaMovement().y;
            double d2 = (random.nextFloat() - 0.5F) + this.getDeltaMovement().z;
            double dist = 1F + random.nextFloat() * 0.2F;
            double d3 = d0 * dist;
            double d4 = d1 * dist;
            double d5 = d2 * dist;
            this.level.addParticle(ModParticle.LIGHTNING.get(), this.getX() + d0, this.getY() + 2, this.getZ() + d2, d3, d4, d5);
            if(!this.isOnGround()) {
                float f = Mth.cos((yBodyRot) * ((float) Math.PI / 180F));
                float f1 = Mth.sin((yBodyRot) * ((float) Math.PI / 180F));
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                double vec = -1.75D;
                double math = 1.35;
                for (int i1 = 0; i1 < 10; i1++) {
                    float angle = (0.01745329251F * this.yBodyRot) + i1;
                    double extraX = 0.2F * Mth.sin((float) (Math.PI + angle));
                    double extraY = 2.75F;
                    double extraZ = 0.2F * Mth.cos(angle);
                    this.level.addParticle(ParticleTypes.FLAME, getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, 0, -0.07, 0);
                    this.level.addParticle(ParticleTypes.FLAME, getX() + vec * vecX + extraX + f * -math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * -math, 0, -0.07, 0);
                }
                for (int i1 = 0; i1 < 5; i1++) {
                    float angle = (0.01745329251F * this.yBodyRot) + i1;
                    double extraX = 0.2F * Mth.sin((float) (Math.PI + angle));
                    double extraY = 2.75F;
                    double extraZ = 0.2F * Mth.cos(angle);
                    this.level.addParticle(ParticleTypes.SMOKE, getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, 0, -0.07, 0);
                    this.level.addParticle(ParticleTypes.SMOKE, getX() + vec * vecX + extraX + f * -math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * -math, 0, -0.07, 0);
                }
            }
        }

    }


    protected void customServerAiStep() {
        super.customServerAiStep();

        for (int i = 1; i < 3; ++i) {
            if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
                this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
                if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
                    int i3 = i - 1;
                    int j3 = this.idleHeadUpdates[i - 1];
                    this.idleHeadUpdates[i3] = this.idleHeadUpdates[i - 1] + 1;
                    if (j3 > 15) {
                        float f = 10.0F;
                        float f1 = 5.0F;
                        double d0 = Mth.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                        double d1 = Mth.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                        double d2 = Mth.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);
                        this.performRangedAttack(i + 1, d0, d1, d2, true);
                        this.idleHeadUpdates[i - 1] = 0;
                    }
                }

                int l1 = this.getAlternativeTarget(i);
                if (l1 > 0) {
                    LivingEntity livingentity = (LivingEntity) this.level.getEntity(l1);
                    if (livingentity != null && this.canAttack(livingentity) && !(this.distanceToSqr(livingentity) > 1600.0D) && this.hasLineOfSight(livingentity) && (Laser_Mode_Progress == 20 || Laser_Mode_Progress == 0)) {
                        this.performRangedAttack(i + 1, livingentity);
                        this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
                        this.idleHeadUpdates[i - 1] = 0;
                    } else {
                        this.setAlternativeTarget(i, 0);
                    }
                } else {
                    List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));
                    if (!list.isEmpty()) {
                        LivingEntity livingentity1 = list.get(this.random.nextInt(list.size()));
                        this.setAlternativeTarget(i, livingentity1.getId());
                    }
                }
            }
        }

        if(mode_change_cooldown < MODE_CHANGE_COOLDOWN ) {
            mode_change_cooldown++;
        }else{
            this.setIsLaserMode(!this.getIsLaserMode());
            mode_change_cooldown = this.random.nextInt(50);
        }

        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        } else {
            this.setAlternativeTarget(0, 0);
        }
        if (this.tickCount % 20 == 0) {
            this.heal(1.0F);
        }
    }


    protected SoundEvent getAmbientSound() {
        this.playSound(SoundEvents.WITHER_AMBIENT, 1.0f, 0.5f);
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        this.playSound(SoundEvents.WITHER_HURT, 1.0f, 0.5f);
        return null;
    }

    protected SoundEvent getDeathSound() {
        this.playSound(SoundEvents.WITHER_HURT, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected void onDeathAIUpdate() {
        super.onDeathAIUpdate();

    }

    private double getHeadX(int p_31515_) {
        if (p_31515_ <= 0) {
            return this.getX();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_31515_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);
            double f2 = this.getIsLaserMode() ? 1.65D : 1.5D;
            return this.getX() + (double)f1 * f2;
        }
    }

    private double getHeadY(int p_31517_) {
        return p_31517_ <= 0 ? this.getY() + 3.0D : this.getY() + 2.6D;
    }

    private double getHeadZ(int p_31519_) {
        if (p_31519_ <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_31519_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            double f2 = this.getIsLaserMode() ? 1.65D : 1.5D;
            return this.getZ() + (double)f1 * f2;
        }
    }

    private float m_31442_(float p_31443_, float p_31444_, float p_31445_) {
        float f = Mth.wrapDegrees(p_31444_ - p_31443_);
        if (f > p_31445_) {
            f = p_31445_;
        }

        if (f < -p_31445_) {
            f = -p_31445_;
        }

        return p_31443_ + f;
    }

    private void performRangedAttack(int p_31458_, LivingEntity p_31459_) {
        this.performRangedAttack(p_31458_, p_31459_.getX(), p_31459_.getY() + (double)p_31459_.getEyeHeight() * 0.5D, p_31459_.getZ(), p_31458_ == 0 && this.random.nextFloat() < 0.001F);
    }

    private void performRangedAttack(int p_31449_, double p_31450_, double p_31451_, double p_31452_, boolean p_31453_) {
        if (!this.isSilent()) {
            this.level.levelEvent((Player)null, 1024, this.blockPosition(), 0);
        }

        double d0 = this.getHeadX(p_31449_);
        double d1 = this.getHeadY(p_31449_);
        double d2 = this.getHeadZ(p_31449_);
        double d3 = p_31450_ - d0;
        double d4 = p_31451_ - d1;
        double d5 = p_31452_ - d2;
        if(this.getIsLaserMode()) {
            Laser_Beam_Entity laserBeam = new Laser_Beam_Entity(this.level, this);
            laserBeam.shoot(d3, d4, d5, 1F, 1F);
            laserBeam.setPosRaw(d0, d1, d2);
            this.level.addFreshEntity(laserBeam);
        }else{
            Wither_Missile_Entity witherskull = new Wither_Missile_Entity(this, d3, d4, d5, this.level);
            witherskull.setPosRaw(d0, d1, d2);
            this.level.addFreshEntity(witherskull);
        }
    }

    public void performRangedAttack(LivingEntity p_31468_, float p_31469_) {
        this.performRangedAttack(0, p_31468_);
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    public float getHeadYRot(int p_31447_) {
        return this.yRotHeads[p_31447_];
    }

    public float getHeadXRot(int p_31481_) {
        return this.xRotHeads[p_31481_];
    }

    public int getAlternativeTarget(int p_31513_) {
        return this.entityData.get(DATA_TARGETS.get(p_31513_));
    }

    public void setAlternativeTarget(int p_31455_, int p_31456_) {
        this.entityData.set(DATA_TARGETS.get(p_31455_), p_31456_);
    }


    public void setIsLaserMode(boolean isLaserMode) {
        this.entityData.set(LASER_MODE, isLaserMode);
    }

    public boolean getIsLaserMode() {
        return this.entityData.get(LASER_MODE);
    }


    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    @Nullable
    public Animation getDeathAnimation()
    {
        return DEATH_ANIMATION;
    }

    public boolean canBeAffected(MobEffectInstance p_31495_) {
        return p_31495_.getEffect() != MobEffects.WITHER && super.canBeAffected(p_31495_);
    }

    static class DeathLaserGoal extends SimpleAnimationGoal<The_Harbinger_Entity> {

        public DeathLaserGoal(The_Harbinger_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Flag.JUMP));
        }

        public void tick() {
            float radius1 = 0.25f;
            LivingEntity target = entity.getTarget();
            if (entity.getAnimationTick() == 8 && !entity.level.isClientSide) {
                //Death_Laser_Beam_Entity DeathBeam = new Death_Laser_Beam_Entity(ModEntities.DEATH_LASER_BEAM.get(), entity.level, entity, entity.getX() + radius1 * Math.sin(-entity.getYRot() * Math.PI / 180), entity.getY() + 2.9, entity.getZ() + radius1 * Math.cos(-entity.getYRot() * Math.PI / 180), (float) ((entity.yHeadRot + 90) * Math.PI / 180), (float) (-entity.getXRot() * Math.PI / 180), 20);
                Death_Laser_Beam_Entity DeathBeam = new Death_Laser_Beam_Entity(ModEntities.DEATH_LASER_BEAM.get(), entity.level, entity, entity.getX(), entity.getY() + 2.9, entity.getZ(), (float) ((entity.yHeadRot + 90) * Math.PI / 180), (float) (-entity.getXRot() * Math.PI / 180), 20);
                entity.level.addFreshEntity(DeathBeam);
            }

            if (entity.getAnimationTick() >= 25) {
                if (target != null) {
                    entity.getLookControl().setLookAt(target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(), 2, 90);
                    entity.lookAt(target, 30, 30);
                }
            }
        }
    }

    static class ChargeGoal extends SimpleAnimationGoal<The_Harbinger_Entity> {

        public ChargeGoal(The_Harbinger_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Flag.JUMP));
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                if (entity.getAnimationTick() < 19) {
                    entity.lookAt(target, 30, 30);
                    entity.getLookControl().setLookAt(target, 30, 30);
                }
                if (entity.getAnimationTick() == 18) {
                  //  Vec3 vec3 = (new Vec3(target.getX() - entity.getX(), target.getY() - entity.getY(), target.getZ() - entity.getZ()));
                   // entity.setDeltaMovement(vec3.x * 0.8,vec3.y * 1.0, vec3.z * 0.8);
                    //entity.setDeltaMovement(entity.getDeltaMovement().add(vec3.x * 0.8D, 0.9D, vec3.z * 0.8D));
                    Vec3 rot = target.position().subtract(0.0, 2.0, 0.0).add(entity.position().multiply(-1.0, -1.0, -1.0)).normalize();
                    entity.setDeltaMovement(rot.multiply(4.0, 3.0, 4.0));
                }
            }
        }
    }
}





