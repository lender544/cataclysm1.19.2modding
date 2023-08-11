package com.github.L_Ender.cataclysm.entity.BossMonsters;

import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.AttackMoveGoal;
import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.CMEntityMoveHelper;
import com.github.L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import com.github.L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import com.github.L_Ender.cataclysm.entity.projectile.EarthQuake_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;


public class Amethyst_Crab_Entity extends Boss_monster implements NeutralMob {
    public static final Animation CRAB_SMASH = Animation.create(53);
    public static final Animation CRAB_SMASH_THREE = Animation.create(77);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;

    public Amethyst_Crab_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
        this.maxUpStep = 1.5F;
        moveControl = new CMEntityMoveHelper(this, 45);
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION, CRAB_SMASH,CRAB_SMASH_THREE};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new AttackMoveGoal(this, false, 1.0D));
        this.goalSelector.addGoal(0, new CrabSmashGoal(this, CRAB_SMASH));
        this.goalSelector.addGoal(0, new CrabSmashThree(this, CRAB_SMASH_THREE));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

    }

    public static AttributeSupplier.Builder amethyst_crab() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F)
                .add(Attributes.ATTACK_DAMAGE, 13)
                .add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }


    protected int decreaseAirSupply(int air) {
        return air;
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void tick() {
        super.tick();
        repelEntities(1.7F, 3.7f, 1.7F, 1.7F);
        AnimationHandler.INSTANCE.updateAnimations(this);
        LivingEntity target = this.getTarget();
        if (this.isAlive()) {
            if (target != null && target.isAlive()) {
                if((!isNoAi() && this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 3.0)){
                    if(this.random.nextInt(2) == 0) {
                        setAnimation(CRAB_SMASH);
                    }else{
                        setAnimation(CRAB_SMASH_THREE);
                    }
                }
            }
        }
    }

    public void aiStep() {
        super.aiStep();
        if (this.getAnimation() == CRAB_SMASH) {
            if(this.getAnimationTick() == 22){
                AreaAttack(4.0f,4.0f,70,1.25f,120);
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                Attackparticle(2.4f,-0.4f);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 20);
            }
        }
        if (this.getAnimation() == CRAB_SMASH_THREE) {
            if(this.getAnimationTick() == 16){
                Attackparticle(2.2f,-0.2f);
                EarthQuakeSummon(2.2f,-0.2f);
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 20);
            }
            if(this.getAnimationTick() == 36){
                Attackparticle(1.8f,-1.5f);
                EarthQuakeSummon(1.8f,-1.5f);
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 20);
            }
            if(this.getAnimationTick() == 56){
                Attackparticle(1.7f,1.3f);
                EarthQuakeSummon(1.7f,1.3f);
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 20);
            }
        }


    }

    private void AreaAttack(float range, float height, float arc, float damage, int shieldbreakticks) {
        List<LivingEntity> entitiesHit = this.getEntityLivingBaseNearby(range, height, range, range);
        for (LivingEntity entityHit : entitiesHit) {
            float entityHitAngle = (float) ((Math.atan2(entityHit.getZ() - this.getZ(), entityHit.getX() - this.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingAngle = this.yBodyRot % 360;
            if (entityHitAngle < 0) {
                entityHitAngle += 360;
            }
            if (entityAttackingAngle < 0) {
                entityAttackingAngle += 360;
            }
            float entityRelativeAngle = entityHitAngle - entityAttackingAngle;
            float entityHitDistance = (float) Math.sqrt((entityHit.getZ() - this.getZ()) * (entityHit.getZ() - this.getZ()) + (entityHit.getX() - this.getX()) * (entityHit.getX() - this.getX()));
            if (entityHitDistance <= range && (entityRelativeAngle <= arc / 2 && entityRelativeAngle >= -arc / 2) || (entityRelativeAngle >= 360 - arc / 2 || entityRelativeAngle <= -360 + arc / 2)) {
                if (!(entityHit instanceof Amethyst_Crab_Entity)) {
                 entityHit.hurt(DamageSource.mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage ));
                    if (entityHit instanceof Player && entityHit.isBlocking() && shieldbreakticks > 0) {
                        disableShield(entityHit, shieldbreakticks);
                    }
                }
            }
        }
    }

    private void Attackparticle(float vec, float math) {
        if (this.level.isClientSide) {
            for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                double DeltaMovementX = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementY = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementZ = getRandom().nextGaussian() * 0.07D;
                float angle = (0.01745329251F * this.yBodyRot) + i1;
                float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) ;
                float f1 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) ;
                double extraX = 1.0 * Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = 1.0 * Mth.cos(angle);
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                int hitX = Mth.floor(getX() + vec * vecX+ extraX);
                int hitY = Mth.floor(getY());
                int hitZ = Mth.floor(getZ() + vec * vecZ + extraZ);
                BlockPos hit = new BlockPos(hitX, hitY, hitZ);
                BlockState block = level.getBlockState(hit.below());
                this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, block), getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, DeltaMovementX, DeltaMovementY, DeltaMovementZ);

            }
        }
    }

    private void EarthQuakeSummon(float vec, float math) {
        float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) ;
        float f1 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) ;
        double theta = (yBodyRot) * (Math.PI / 180);
        theta += Math.PI / 2;
        double vecX = Math.cos(theta);
        double vecZ = Math.sin(theta);
        final int quakeCount = 16;
        float angle = 360.0F / quakeCount;
        for (int i = 0; i < quakeCount; i++) {
            EarthQuake_Entity peq = new EarthQuake_Entity(this.level, this);
            peq.shootFromRotation(this, 0, angle * i, 0.0F, 0.25F, 0.0F);
            peq.setPos(this.getX() + vec * vecX + f * math, this.getY(), getZ() + vec * vecZ + f1 * math);
            this.level.addFreshEntity(peq);

        }
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }


    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.GOLEMHURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.GOLEMDEATH.get();
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new CMPathNavigateGround(this, worldIn);
    }

    public void setRemainingPersistentAngerTime(int p_32515_) {
        this.remainingPersistentAngerTime = p_32515_;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_32509_) {
        this.persistentAngerTarget = p_32509_;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    static class CrabSmashGoal extends SimpleAnimationGoal<Amethyst_Crab_Entity> {


        public CrabSmashGoal(Amethyst_Crab_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (entity.getAnimationTick() < 19 && target != null) {
                entity.lookAt(target, 30, 30);
                entity.getNavigation().moveTo(target, 1.0f);
            } else {
                entity.setYRot(entity.yRotO);
            }
            if (entity.getAnimationTick() == 19){
                entity.getNavigation().stop();
            }

        }
    }

    static class CrabSmashThree extends SimpleAnimationGoal<Amethyst_Crab_Entity> {


        public CrabSmashThree(Amethyst_Crab_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (entity.getAnimationTick() < 10 && target != null) {
                entity.lookAt(target, 30, 30);
            } else {
                entity.setYRot(entity.yRotO);
            }
        }
    }

}





