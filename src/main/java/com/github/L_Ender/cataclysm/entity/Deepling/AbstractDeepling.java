package com.github.L_Ender.cataclysm.entity.Deepling;

import com.github.L_Ender.cataclysm.entity.AI.MobAIFindWater;
import com.github.L_Ender.cataclysm.entity.AI.MobAILeaveWater;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.etc.GroundPathNavigatorWide;
import com.github.L_Ender.cataclysm.entity.etc.ISemiAquatic;
import com.github.L_Ender.cataclysm.entity.etc.SemiAquaticPathNavigator;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AbstractDeepling extends Monster implements IAnimatedEntity, ISemiAquatic {
    private int animationTick;
    private Animation currentAnimation;
    private int moistureAttackTime = 0;
    public float LayerBrightness, oLayerBrightness;
    public int LayerTicks;
    private boolean isLandNavigator;
    public float SwimProgress = 0;
    public float prevSwimProgress = 0;
    private static final EntityDataAccessor<Integer> MOISTNESS = SynchedEntityData.defineId(AbstractDeepling.class, EntityDataSerializers.INT);


    public AbstractDeepling(EntityType entity, Level world) {
        super(entity, world);
    }



    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new MobAIFindWater(this,1.0D));
        this.goalSelector.addGoal(4, new MobAILeaveWater(this));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION};
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS, 60000);
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        } else if (super.isAlliedTo(entityIn)) {
            return true;
        } else if (entityIn instanceof Coralssus_Entity || entityIn instanceof AbstractDeepling || entityIn instanceof LionFish_Entity || entityIn instanceof The_Leviathan_Entity){
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }


    @Override
    public void tick() {
        super.tick();

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

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (this.level.isClientSide){
            ++LayerTicks;
            this.LayerBrightness += (0.0F - this.LayerBrightness) * 0.8F;
        }
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

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    protected void onAnimationFinish(Animation animation) {}


    public boolean isPushedByFluid() {
        return !this.isSwimming();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.currentAnimation);
        }
        this.currentAnimation = animation;
        setAnimationTick(0);
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

}
