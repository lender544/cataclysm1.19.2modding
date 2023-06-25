package com.github.L_Ender.cataclysm.entity.Pet;


import com.github.L_Ender.cataclysm.entity.etc.IFollower;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public class AnimationPet extends TamableAnimal implements IAnimatedEntity, IFollower {
    private int animationTick;
    private Animation currentAnimation;
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(AnimationPet.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.defineId(AnimationPet.class, EntityDataSerializers.INT);

    public AnimationPet(EntityType entity, Level world) {
        super(entity, world);

    }


    protected void onAnimationFinish(Animation animation) {}


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
        this.entityData.define(COMMAND, Integer.valueOf(0));
        this.entityData.define(SITTING, Boolean.valueOf(false));
    }

    public int getCommand() {
        return this.entityData.get(COMMAND).intValue();
    }

    public void setCommand(int command) {
        this.entityData.set(COMMAND, Integer.valueOf(command));
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING).booleanValue();
    }

    public void setOrderedToSit(boolean sit) {
        this.entityData.set(SITTING, Boolean.valueOf(sit));
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.currentAnimation;
    }


    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.currentAnimation);
        }
        this.currentAnimation = animation;
        setAnimationTick(0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("CmPetSitting", this.isSitting());
        compound.putInt("Command", this.getCommand());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setOrderedToSit(compound.getBoolean("CmPetSitting"));
        this.setCommand(compound.getInt("Command"));
    }


    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }


    @Override
    public boolean shouldFollow() {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }
}
