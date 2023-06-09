package com.github.L_Ender.cataclysm.entity.Deepling;

import com.github.L_Ender.cataclysm.entity.AI.CmAttackGoal;
import com.github.L_Ender.cataclysm.entity.AI.MobAIFindWater;
import com.github.L_Ender.cataclysm.entity.AI.MobAILeaveWater;
import com.github.L_Ender.cataclysm.entity.Boss_monster;
import com.github.L_Ender.cataclysm.entity.etc.GroundPathNavigatorWide;
import com.github.L_Ender.cataclysm.entity.etc.ISemiAquatic;
import com.github.L_Ender.cataclysm.entity.etc.SemiAquaticPathNavigator;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;


public class Coralssus_Entity extends Boss_monster implements ISemiAquatic {
    boolean searchingForLand;
    private int moistureAttackTime = 0;
    private boolean isLandNavigator;
    public float SwimProgress = 0;
    public float prevSwimProgress = 0;

    public static final int VOID_RUNE_ATTACK_COOLDOWN = 250;
    private int void_rune_attack_cooldown = 0;

    private static final EntityDataAccessor<Integer> MOISTNESS = SynchedEntityData.defineId(Coralssus_Entity.class, EntityDataSerializers.INT);


    public Coralssus_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
        this.maxUpStep = 1.5F;
        this.moveControl = new CoralssusMoveControl(this);
        switchNavigator(false);
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION};
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new CmAttackGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new MobAIFindWater(this,1.0D));
        this.goalSelector.addGoal(4, new MobAILeaveWater(this));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder coralssus() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.MAX_HEALTH, 110)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8);
    }


    protected PathNavigation createNavigation(Level worldIn) {
        return new WaterBoundPathNavigation(this, worldIn);
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    public boolean hurt(DamageSource source, float damage) {
        return super.hurt(source, damage);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS, 60000);
    }

    public void switchNavigator(boolean onLand) {
        if (onLand) {
            this.navigation = new GroundPathNavigatorWide(this, level);
            this.isLandNavigator = true;
        } else {
            this.navigation = new SemiAquaticPathNavigator(this, level);
            this.isLandNavigator = false;
        }
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Moisture", this.getMoistness());

    }
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setMoistness(compound.getInt("Moisture"));

    }

    public int getMoistness() {
        return this.entityData.get(MOISTNESS);
    }

    public void setMoistness(int p_211137_1_) {
        this.entityData.set(MOISTNESS, p_211137_1_);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean isPushedByFluid() {
        return !this.isSwimming();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean shouldEnterWater() {
        return getMoistness() < 300;
    }

    @Override
    public boolean shouldLeaveWater() {
        return this.getTarget() != null && !this.getTarget().isInWater();
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 32;

    }
    

    public void tick() {
        super.tick();
        AnimationHandler.INSTANCE.updateAnimations(this);
        if (isInWater() && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (!isInWater() && !this.isLandNavigator) {
            switchNavigator(true);
        }
        this.prevSwimProgress = SwimProgress;
        if (this.isInWater()) {
            if (this.SwimProgress < 10F)
                this.SwimProgress++;
        } else {
            if (this.SwimProgress > 0F)
                this.SwimProgress--;
        }
        
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoistness(6000);
            } else {
                int dry = this.level.isDay() ? 2 : 1;
                this.setMoistness(this.getMoistness() - dry);
                if (this.getMoistness() <= 0 && moistureAttackTime-- <= 0) {
                    this.hurt(DamageSource.DRY_OUT, random.nextInt(2) == 0 ? 1.0F : 0F);
                    moistureAttackTime = 20;
                }
            }
        }
    }

    private void launch(LivingEntity e, boolean huge) {

        double d0 = e.getX() - this.getX();
        double d1 = e.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        float f = huge ? 2F : 0.5F;
        e.push(d0 / d2 * f, huge ? 0.5D : 0.2F, d1 / d2 * f);
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        } else if (super.isAlliedTo(entityIn)) {
            return true;
        } else if (entityIn instanceof Coralssus_Entity || entityIn instanceof AbstractDeepling ) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }



    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.GOLEMHURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.GOLEMDEATH.get();
    }

    boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            LivingEntity livingentity = this.getTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    public void travel(Vec3 p_32394_) {
        if (this.isEffectiveAi() && this.isInWater() && this.wantsToSwim()) {
            this.moveRelative(0.01F, p_32394_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_32394_);
        }

    }


    protected boolean closeToNextPos() {
        Path path = this.getNavigation().getPath();
        if (path != null) {
            BlockPos blockpos = path.getTarget();
            if (blockpos != null) {
                double d0 = this.distanceToSqr((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
                if (d0 < 4.0D) {
                    return true;
                }
            }
        }
        return false;
    }


    public void setSearchingForLand(boolean p_32399_) {
        this.searchingForLand = p_32399_;
    }

    static class CoralssusGoToBeachGoal extends MoveToBlockGoal {
        private final Coralssus_Entity drowned;

        public CoralssusGoToBeachGoal(Coralssus_Entity p_32409_, double p_32410_) {
            super(p_32409_, p_32410_, 8, 2);
            this.drowned = p_32409_;
        }

        public boolean canUse() {
            return super.canUse() && this.drowned.level.isRaining() && this.drowned.isInWater() && this.drowned.getY() >= (double)(this.drowned.level.getSeaLevel() - 3);
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        protected boolean isValidTarget(LevelReader p_32413_, BlockPos p_32414_) {
            BlockPos blockpos = p_32414_.above();
            return p_32413_.isEmptyBlock(blockpos) && p_32413_.isEmptyBlock(blockpos.above()) ? p_32413_.getBlockState(p_32414_).entityCanStandOn(p_32413_, p_32414_, this.drowned) : false;
        }

        public void start() {
            this.drowned.setSearchingForLand(false);
            // this.drowned.switchNavigator(true);
            super.start();
        }

        public void stop() {
            super.stop();
        }
    }

    static class CoralssusSwimUpGoal extends Goal {
        private final Coralssus_Entity drowned;
        private final double speedModifier;
        private final int seaLevel;
        private boolean stuck;

        public CoralssusSwimUpGoal(Coralssus_Entity p_32440_, double p_32441_, int p_32442_) {
            this.drowned = p_32440_;
            this.speedModifier = p_32441_;
            this.seaLevel = p_32442_;
        }

        public boolean canUse() {
            return (this.drowned.level.isRaining() || this.drowned.isInWater())&& this.drowned.getY() < (double)(this.seaLevel - 2);
        }

        public boolean canContinueToUse() {
            return this.canUse() && !this.stuck;
        }

        public void tick() {
            if (this.drowned.getY() < (double)(this.seaLevel - 1) && (this.drowned.getNavigation().isDone() || this.drowned.closeToNextPos())) {
                Vec3 vec3 = DefaultRandomPos.getPosTowards(this.drowned, 4, 8, new Vec3(this.drowned.getX(), (double)(this.seaLevel - 1), this.drowned.getZ()), (double)((float)Math.PI / 2F));
                if (vec3 == null) {
                    this.stuck = true;
                    return;
                }

                this.drowned.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, this.speedModifier);
            }

        }

        public void start() {
            this.drowned.setSearchingForLand(true);
            this.stuck = false;
        }

        public void stop() {
            this.drowned.setSearchingForLand(false);
        }
    }


    static class CoralssusMoveControl extends MoveControl {
        private final Coralssus_Entity drowned;

        public CoralssusMoveControl(Coralssus_Entity p_32433_) {
            super(p_32433_);
            this.drowned = p_32433_;
        }

        public void tick() {
            LivingEntity livingentity = this.drowned.getTarget();
            if (this.drowned.wantsToSwim() && this.drowned.isInWater()) {
                if (livingentity != null && livingentity.getY() > this.drowned.getY() || this.drowned.searchingForLand) {
                    this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0D, 0.002D, 0.0D));
                }

                if (this.operation != Operation.MOVE_TO || this.drowned.getNavigation().isDone()) {
                    this.drowned.setSpeed(0.0F);
                    return;
                }

                double d0 = this.wantedX - this.drowned.getX();
                double d1 = this.wantedY - this.drowned.getY();
                double d2 = this.wantedZ - this.drowned.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.drowned.setYRot(this.rotlerp(this.drowned.getYRot(), f, 90.0F));
                this.drowned.yBodyRot = this.drowned.getYRot();
                float f1 = (float)(this.speedModifier * this.drowned.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float f2 = Mth.lerp(0.125F, this.drowned.getSpeed(), f1);
                this.drowned.setSpeed(f2);
                this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add((double)f2 * d0 * 0.005D, (double)f2 * d1 * 0.1D, (double)f2 * d2 * 0.005D));
            } else {
                if (!this.drowned.onGround) {
                    this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0D, -0.008D, 0.0D));
                }

                super.tick();
            }

        }
    }
}





