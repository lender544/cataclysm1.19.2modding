package com.github.L_Ender.cataclysm.entity;

import com.github.L_Ender.cataclysm.entity.AI.SwimmerJumpPathNavigator;
import com.github.L_Ender.cataclysm.entity.AltlumenGoal.OrcaAIJump;
import com.github.L_Ender.cataclysm.entity.AltlumenGoal.OrcaAIMelee;
import com.github.L_Ender.cataclysm.entity.AltlumenGoal.OrcaAIMeleeJump;
import com.github.L_Ender.cataclysm.entity.projectile.The_Leviathan_Tongue_Entity;
import com.github.L_Ender.cataclysm.entity.util.LeviathanTongueUtil;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class The_Leviathan_Entity extends Boss_monster {

    public static final Animation ANIMATION_BITE = Animation.create(8);
    public static final Animation ANIMATION_TAILSWING = Animation.create(20);
    public int jumpCooldown;

    public The_Leviathan_Entity(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new MoveHelperController(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }



    public static AttributeSupplier.Builder altulmen() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60.0D).add(Attributes.FOLLOW_RANGE, 64.0D).add(Attributes.ARMOR, 0.0D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.7F).add(Attributes.MOVEMENT_SPEED, 1.35F);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new SwimmerJumpPathNavigator(this, worldIn);
    }



    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new OrcaAIJump(this, 10));
        this.goalSelector.addGoal(6, new OrcaAIMeleeJump(this));
        this.goalSelector.addGoal(6, new OrcaAIMelee(this, 1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());

    }
    @Override
    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_BITE, ANIMATION_TAILSWING};
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
        if (jumpCooldown > 0) {
            jumpCooldown--;
            float f2 = (float) -((float) this.getDeltaMovement().y * (double) (180F / (float) Math.PI));
            this.setXRot(f2);
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
        if (target != null ) {

                if (LeviathanTongueUtil.canLaunchTongues(this.level, this)) {
                    LeviathanTongueUtil.retractFarTongues(this.level, this);
                    if (!this.level.isClientSide) {
                        The_Leviathan_Tongue_Entity segment = ModEntities.TONGUE.get().create(this.level);
                        segment.copyPosition(this);
                        this.level.addFreshEntity(segment);
                        segment.setCreatorEntityUUID(this.getUUID());
                        segment.setToEntityID(target.getId());
                        segment.setFromEntityID(this.getId());
                        segment.copyPosition(this);
                        segment.setProgress(0.0F);
                        LeviathanTongueUtil.setLastTongue(this, segment);
                    }

            }
        }

        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    public boolean doHurtTarget(Entity entityIn) {
        if(this.isInWaterOrBubble() && random.nextBoolean()){
            this.setAnimation(ANIMATION_TAILSWING);
        }else{
            this.setAnimation(ANIMATION_BITE);
        }
        return true;
    }

    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int currentAir) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.0F;
    }

    public int getMaxHeadXRot() {
        return 1;
    }

    public int getMaxHeadYRot() {
        return 1;
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
        return false;
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

    public void onJumpHit(LivingEntity entityIn) {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
        }
    }

    static class MoveHelperController extends MoveControl {
        private final The_Leviathan_Entity dolphin;

        public MoveHelperController(The_Leviathan_Entity dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == Operation.MOVE_TO && !this.dolphin.getNavigation().isDone()) {
                double d0 = this.wantedX - this.dolphin.getX();
                double d1 = this.wantedY - this.dolphin.getY();
                double d2 = this.wantedZ - this.dolphin.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double) 2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                    this.dolphin.setYRot(this.rotlerp(this.dolphin.getYRot(), f, 10.0F));
                    this.dolphin.yBodyRot = this.dolphin.getYRot();
                    this.dolphin.yHeadRot = this.dolphin.getYRot();
                    float f1 = (float) (this.speedModifier * this.dolphin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setSpeed(f1 * 0.02F);
                        float f2 = -((float) (Mth.atan2(d1, Mth.sqrt((float) (d0 * d0 + d2 * d2))) * (double) (180F / (float) Math.PI)));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.setXRot(this.rotlerp(this.dolphin.getXRot(), f2, 5.0F));
                        float f3 = Mth.cos(this.dolphin.getXRot() * ((float) Math.PI / 180F));
                        float f4 = Mth.sin(this.dolphin.getXRot() * ((float) Math.PI / 180F));
                        this.dolphin.zza = f3 * f1;
                        this.dolphin.yya = -f4 * f1;
                    } else {
                        this.dolphin.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.dolphin.setSpeed(0.0F);
                this.dolphin.setXxa(0.0F);
                this.dolphin.setYya(0.0F);
                this.dolphin.setZza(0.0F);
            }
        }
    }

}
