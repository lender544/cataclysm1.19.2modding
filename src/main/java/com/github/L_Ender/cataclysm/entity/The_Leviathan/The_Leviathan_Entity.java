package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.entity.AI.EntityAINearestTarget3D;
import com.github.L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.Boss_monster;
import com.github.L_Ender.cataclysm.entity.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.*;
import com.github.L_Ender.cataclysm.entity.projectile.Abyss_Blast_Entity;
import com.github.L_Ender.cataclysm.entity.util.LeviathanTongueUtil;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class The_Leviathan_Entity extends Boss_monster implements ISemiAquatic {

    public static final Animation LEVIATHAN_GRAB = Animation.create(115);
    public static final Animation LEVIATHAN_TAILSWING = Animation.create(20);
    public static final Animation LEVIATHAN_GRAB_BITE = Animation.create(13);
    public static final Animation LEVIATHAN_ABYSS_BLAST = Animation.create(173);
    public static final Animation LEVIATHAN_RUSH = Animation.create(109);
    public float NoSwimProgress = 0;
    public float prevNoSwimProgress = 0;
    private boolean isLandNavigator;

    private static final EntityDataAccessor<Float> LIGHT = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.FLOAT);

    public int jumpCooldown;


    public The_Leviathan_Entity(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.maxUpStep = 1.5F;
        switchNavigator(false);
    }

    public static AttributeSupplier.Builder leviathan() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.1);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new WaterBoundPathNavigation(this, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIGHT, 0F);
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LeviathanAIFindWater(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new LeviathanGrabAttackGoal(this,LEVIATHAN_GRAB));
        this.goalSelector.addGoal(0, new LeviathanGrabBiteAttackGoal(this,LEVIATHAN_GRAB_BITE));
        this.goalSelector.addGoal(0, new LeviathanBlastAttackGoal(this,LEVIATHAN_ABYSS_BLAST));
        this.goalSelector.addGoal(0, new LeviathanRushAttackGoal(this,LEVIATHAN_RUSH));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new EntityAINearestTarget3D<>(this, Player.class, true,true));
        this.targetSelector.addGoal(3, new EntityAINearestTarget3D<>(this, LivingEntity.class, 160, true, true, ModEntities.buildPredicateFromTag(ModTag.LEVIATHAN_TARGET)));

    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MoveControl(this);
            this.navigation = new CMPathNavigateGround(this, level);
            this.isLandNavigator = true;
        } else {
            this.moveControl = new AquaticMoveController(this, 10, 1, 6);
            this.navigation = new SemiAquaticPathNavigator(this, level);
            this.isLandNavigator = false;
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{LEVIATHAN_GRAB, LEVIATHAN_TAILSWING,LEVIATHAN_GRAB_BITE,LEVIATHAN_ABYSS_BLAST,LEVIATHAN_RUSH};
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }


    public void tick() {
        super.tick();
        this.setYRot(this.yHeadRot);
        if (isInWater() && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (!isInWater() && !this.isLandNavigator) {
            switchNavigator(true);
        }
        final boolean groundAnimate = !this.isInWater();
        this.prevNoSwimProgress = NoSwimProgress;
        if (groundAnimate) {
            if (this.NoSwimProgress < 10F)
                this.NoSwimProgress++;
        } else {
            if (this.NoSwimProgress > 0F)
                this.NoSwimProgress--;
        }

        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.level.isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vector3d = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * 0.9F;
                float f1 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * 0.9F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for (int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 + (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 + (double) f1, 0.0D, 0.0D, 0.0D);
                    this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 - (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 - (double) f1, 0.0D, 0.0D, 0.0D);
                }
            }

        }
        LivingEntity target = this.getTarget();


        if(target !=null) {
            if (this.getAnimation() == NO_ANIMATION) {
                this.setAnimation(LEVIATHAN_ABYSS_BLAST);

            }
        }

        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    public void aiStep() {
        super.aiStep();
        LivingEntity target = this.getTarget();
        if(this.getAnimation() == LEVIATHAN_ABYSS_BLAST){
            if(this.getAnimationTick() < 30){
                if (this.level.isClientSide) {
                    for (int i = 0; i < 20; ++i) {
                        float f = -Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(this.getXRot() * ((float)Math.PI / 180F));
                        float f2 = -Mth.sin(this.getXRot() * ((float)Math.PI / 180F));
                        float f3 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(this.getXRot() * ((float)Math.PI / 180F));
                        this.level.addParticle(ParticleTypes.PORTAL, this.getX() + f * 4.0,this.getY() + f2 * 3.5, this.getZ() + f3 * 4.0, (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
                    }
                }

            }


                if (this.getAnimationTick() >= 53) {

                    this.setXRot(this.xRotO);

            }

            for (int i = 33, j = 0; i <= 73; i++, j++) {
                float l = j * 0.025f;
                if (this.getAnimationTick() == i) {
                    this.setLight(l);
                }
            }

            for (int i = 133, j = 1; i <= 173; i++, j++) {
                float l = j * -0.025f;
                if (this.getAnimationTick() == i) {
                    this.setLight(l);
                }
            }

            if(this.getAnimationTick() == 73 ) {
                ScreenShake_Entity.ScreenShake(this.level, this.position(), 20, 0.1f, 90, 10);
            }
        }
    }


    public boolean doHurtTarget(Entity entityIn) {
        if(this.isInWaterOrBubble() && random.nextBoolean()){
            this.setAnimation(LEVIATHAN_TAILSWING);
        }
        return true;
    }

    protected int increaseAirSupply(int currentAir) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.45F;
    }

    public boolean shouldUseJumpAttack(LivingEntity attackTarget) {
        if (attackTarget.isInWater()) {
            BlockPos up = attackTarget.blockPosition().above();
            return level.getFluidState(up.above()).isEmpty() && level.getFluidState(up.above(2)).isEmpty() && this.jumpCooldown == 0;
        } else {
            return this.jumpCooldown == 0;
        }
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.updateAir(i);
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean checkSpawnObstruction(LevelReader worldIn) {
        return worldIn.isUnobstructed(this);
    }

    protected void updateAir(int p_209207_1_) {
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

    }

    public float getLight() {
        return this.entityData.get(LIGHT);
    }

    public void setLight(float light) {
        this.entityData.set(LIGHT, light);
    }


    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    public void onJumpHit(LivingEntity entityIn) {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean shouldEnterWater() {
        return true;
    }

    @Override
    public boolean shouldLeaveWater() {
        return false;
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 32;
    }

    static class LeviathanGrabAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanGrabAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            super.start();
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            if (entity.getAnimationTick() == 25) {
                if (target != null) {
                    if (LeviathanTongueUtil.canLaunchTongues(this.entity.level, this.entity)) {
                        LeviathanTongueUtil.retractFarTongues(this.entity.level, this.entity);
                        if (!entity.level.isClientSide) {
                            The_Leviathan_Tongue_Entity segment = ModEntities.THE_LEVIATHAN_TONGUE.get().create(this.entity.level);
                            segment.copyPosition(this.entity);
                            this.entity.level.addFreshEntity(segment);
                            segment.setCreatorEntityUUID(this.entity.getUUID());
                            segment.setToEntityID(target.getId());
                            segment.setFromEntityID(this.entity.getId());
                            segment.setMaxExtendTime(15);
                            segment.copyPosition(this.entity);
                            segment.setProgress(0.0F);
                            LeviathanTongueUtil.setLastTongue(this.entity, segment);
                        }
                    }
                }
            }
            if (this.entity.getAnimationTick() > 25 && this.entity.getAnimationTick() <= 95) {
                if (target != null) {
                    if (LeviathanTongueUtil.canLaunchTongues(this.entity.level, this.entity)) {
                        if (this.entity.distanceTo(target) < 8) {
                            AnimationHandler.INSTANCE.sendAnimationMessage(this.entity, LEVIATHAN_GRAB_BITE);
                        }
                    }
                }
            }
        }
    }

    static class LeviathanGrabBiteAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanGrabBiteAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            entity.playSound(SoundEvents.PHANTOM_BITE);
            super.start();
        }

        public void tick() {
            entity.setYRot(entity.yRotO);
        }
    }

    static class LeviathanBlastAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanBlastAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            super.start();
        }

        public void tick() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                if (this.entity.getAnimationTick() < 68) {
                    entity.getLookControl().setLookAt(target, 30, 90);
                }
            }
            entity.setDeltaMovement(0,0,0);
            float dir = 90.0f;
            if(this.entity.getAnimationTick() == 53) {
                if(!entity.level.isClientSide) {
                    Abyss_Blast_Entity DeathBeam = new Abyss_Blast_Entity(ModEntities.ABYSS_BLAST.get(), entity.level, entity, entity.getX(), entity.getY(), entity.getZ(), (float) ((entity.yHeadRot + dir) * Math.PI / 180), (float) (-entity.getXRot() * Math.PI / 180), 80, dir);
                    entity.level.addFreshEntity(DeathBeam);
                }
            }
        }
    }

    static class LeviathanRushAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanRushAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 30);
                if (this.entity.getAnimationTick() > 48 && this.entity.getAnimationTick() < 89) {
                    final double d0 = target.getX() - entity.getX();
                    final double d1 = target.getEyeY() - entity.getEyeY();
                    final double d2 = target.getZ() - entity.getZ();
                    final double d3 = Mth.sqrt((float) (d0 * d0 + d2 * d2));
                    final float targetYaw = (float) (Mth.atan2(d2, d0) * 180.0F / (float) Math.PI - 90.0F);
                    final float targetPitch = (float) (-(Mth.atan2(d1, d3) * 180.0F / (float) Math.PI));
                    entity.setXRot((entity.getXRot() + Mth.clamp(targetPitch - entity.getXRot(), -2, 2)));
                    if (d0 * d0 + d2 * d2 >= 25) {
                        entity.setYRot((entity.getYRot() + Mth.clamp(targetYaw - entity.getYRot(), -2, 2)));
                        entity.yBodyRot = entity.getYRot();
                    }
                    if (Math.abs(Mth.wrapDegrees(targetYaw) - Mth.wrapDegrees(entity.getYRot())) < 4) {
                        final double distSq = d0 * d0 + d2 * d2;
                        if (distSq < 25) {
                            entity.setYRot(entity.yRotO);
                            entity.yBodyRot = entity.yRotO;
                            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.8, 1, 0.8));
                        } else {
                            if (entity.isInWater() && target.isInWater()) {
                                final Vec3 vector3d = entity.getDeltaMovement();
                                Vec3 vector3d1 = new Vec3(target.getX() - entity.getX(), target.getY() - entity.getY(), target.getZ() - entity.getZ());
                                if (vector3d1.lengthSqr() > 1.0E-7D) {
                                    vector3d1 = vector3d1.normalize().scale(0.5D).add(vector3d.scale(0.4D));
                                }
                                entity.setDeltaMovement(vector3d1.x, vector3d1.y, vector3d1.z);
                            }
                            entity.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
                            entity.getNavigation().moveTo(target, 1.0F);
                        }
                    }
                }
            }
        }
    }
}
