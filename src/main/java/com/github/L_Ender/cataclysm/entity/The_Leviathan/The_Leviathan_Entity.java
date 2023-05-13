package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.AI.AnimationGoal;
import com.github.L_Ender.cataclysm.entity.AI.EntityAINearestTarget3D;
import com.github.L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.Boss_monster;
import com.github.L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.effect.Cm_Falling_Block_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.*;
import com.github.L_Ender.cataclysm.entity.partentity.Cm_Part_Entity;
import com.github.L_Ender.cataclysm.entity.util.LeviathanTongueUtil;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class The_Leviathan_Entity extends Boss_monster implements ISemiAquatic {

    public static final Animation LEVIATHAN_GRAB = Animation.create(100);
    public static final Animation LEVIATHAN_GRAB_BITE = Animation.create(13);
    public static final Animation LEVIATHAN_ABYSS_BLAST = Animation.create(184);
    public static final Animation LEVIATHAN_RUSH = Animation.create(157);
    public static final Animation LEVIATHAN_STUN = Animation.create(90);

    public static final Animation LEVIATHAN_ABYSS_BLAST_PORTAL = Animation.create(100);
    public static final Animation LEVIATHAN_TENTACLE_STRIKE_UPPER_R = Animation.create(44);
    public static final Animation LEVIATHAN_TENTACLE_STRIKE_LOWER_R = Animation.create(44);
    public static final Animation LEVIATHAN_TENTACLE_STRIKE_UPPER_L = Animation.create(44);
    public static final Animation LEVIATHAN_TENTACLE_STRIKE_LOWER_L = Animation.create(44);
    public final The_Leviathan_Part headPart;
    public final The_Leviathan_Part tailPart1;
    public final The_Leviathan_Part tailPart2;

    public final The_Leviathan_Part[] leviathanParts;


    public float NoSwimProgress = 0;
    public float prevNoSwimProgress = 0;
    private boolean isLandNavigator;
    public Vec3 teleportPos = null;
    public Abyss_Portal_Entity portalTarget = null;
    public boolean fullyThrough = true;
    public boolean updatePostSummon = false;

    public static final int GRAB_HUNTING_COOLDOWN = 100;

    public static final int RUSH_HUNTING_COOLDOWN = 140;

    public static final int BLAST_HUNTING_COOLDOWN = 200;

    public static final int TENTACLE_STRIKE_HUNTING_COOLDOWN = 80;


    public static final int MAKEPORTAL_COOLDOWN = 240;
    private int hunting_cooldown = 160;
    private int makePortalCooldown = 0;

    public double endPosX, endPosY, endPosZ;
    public double collidePosX, collidePosY, collidePosZ;
    private int destroyBlocksTick;
    private static final EntityDataAccessor<Float> LIGHT = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> PORTAL_TICKS = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BLAST_CHANCE = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.INT);

    public int jumpCooldown;


    public The_Leviathan_Entity(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.maxUpStep = 1.5F;

        this.headPart = new The_Leviathan_Part(this, 2.8F, 2.8F);
        this.tailPart1 = new The_Leviathan_Part(this, 1.5F, 2.4F);
        this.tailPart2 = new The_Leviathan_Part(this, 1.3F, 2.4F);
        this.leviathanParts = new The_Leviathan_Part[]{this.headPart,this.tailPart1,this.tailPart2};
        switchNavigator(false);
    }

    public static AttributeSupplier.Builder leviathan() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new WaterBoundPathNavigation(this, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIGHT, 0F);
        this.entityData.define(PORTAL_TICKS, 0);
        this.entityData.define(BLAST_CHANCE, 0);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LeviathanAIFindWaterAndPortal(this));
        this.goalSelector.addGoal(2, new LeviathanAttackGoal(this));
        this.goalSelector.addGoal(4, new LeviathanAIRandomSwimming(this, 1F, 3, 15));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new LeviathanGrabAttackGoal(this,LEVIATHAN_GRAB));
        this.goalSelector.addGoal(0, new LeviathanStunGoal(this,LEVIATHAN_STUN));
        this.goalSelector.addGoal(0, new LeviathanGrabBiteAttackGoal(this,LEVIATHAN_GRAB_BITE));
        this.goalSelector.addGoal(0, new LeviathanTentacleAttackGoal(this));
        this.goalSelector.addGoal(0, new LeviathanBlastAttackGoal(this,LEVIATHAN_ABYSS_BLAST));
        this.goalSelector.addGoal(0, new LeviathanAbyssBlastPortalAttackGoal(this,LEVIATHAN_ABYSS_BLAST_PORTAL));
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
            this.moveControl = new LeviathanMoveController(this, 10, 1, 6);
            this.navigation = new WaterBoundPathNavigation(this, level);
            this.isLandNavigator = false;
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{LEVIATHAN_GRAB,LEVIATHAN_GRAB_BITE,LEVIATHAN_ABYSS_BLAST,LEVIATHAN_RUSH,LEVIATHAN_TENTACLE_STRIKE_UPPER_R,LEVIATHAN_TENTACLE_STRIKE_UPPER_L,LEVIATHAN_TENTACLE_STRIKE_LOWER_L,LEVIATHAN_TENTACLE_STRIKE_LOWER_R,LEVIATHAN_STUN};
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null && this.getAnimation() == NO_ANIMATION) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }


    private static Animation getRandomTantalcleStrike(RandomSource rand) {
        switch (rand.nextInt(4)) {
            case 0:
                return LEVIATHAN_TENTACLE_STRIKE_LOWER_L;
            case 1:
                return LEVIATHAN_TENTACLE_STRIKE_LOWER_R;
            case 2:
                return LEVIATHAN_TENTACLE_STRIKE_UPPER_L;
            case 3:
                return LEVIATHAN_TENTACLE_STRIKE_UPPER_R;
        }
        return LEVIATHAN_TENTACLE_STRIKE_UPPER_R;
    }

    public boolean hurt(DamageSource source, float damage) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof Abyss_Blast_Entity || entity instanceof Portal_Abyss_Blast_Entity) {
            return false;
        }
        if (this.destroyBlocksTick <= 0) {
            this.destroyBlocksTick = 20;
        }
        boolean attack = super.hurt(source, damage);
        if(this.getAnimation() == LEVIATHAN_RUSH) {
            if (this.getAnimationTick() >= 38 && this.getAnimationTick() <= 54) {
                if(attack){
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, LEVIATHAN_STUN);
                }
            }
        }


        return attack;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source == DamageSource.IN_WALL || source == DamageSource.FALLING_BLOCK || super.isInvulnerableTo(source);
    }

    public boolean attackEntityFromPart(The_Leviathan_Part leviathan_part, DamageSource source, float amount) {
        return this.hurt(source, amount);
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
        if (this.portalTarget != null && this.portalTarget.getLifespan() < 5) {
            this.portalTarget = null;
        }
        if (teleportPos != null) {
            this.setPos(teleportPos.x, teleportPos.y, teleportPos.z);
            teleportPos = null;
        }
        if (makePortalCooldown > 0) {
            makePortalCooldown--;
        }

        if (hunting_cooldown > 0) {
            hunting_cooldown--;
        }

       // blockbreak(3,3,3);

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (!this.isNoAi()) {
            final float f17 = this.getYRot() * (float) Math.PI / 180.0F;
            final float pitch = this.getXRot() * (float) Math.PI / 180.0F;
            final float f3 = Mth.sin(f17) * (1 - Math.abs(this.getXRot() / 90F));
            final float f18 = Mth.cos(f17) * (1 - Math.abs(this.getXRot() / 90F));
            this.setPartPosition(this.headPart, f3 * -3.8F, -pitch * 3F, -f18 * -3.8F);
            this.setPartPosition(this.tailPart1, f3 * 3.3F, -pitch * -5F, -f18 * 3.3F);
            this.setPartPosition(this.tailPart2, f3 * 4.7F, -pitch * -8F, -f18 * 4.7F);
            Vec3[] avector3d = new Vec3[this.leviathanParts.length];
            for (int j = 0; j < this.leviathanParts.length; ++j) {
                avector3d[j] = new Vec3(this.leviathanParts[j].getX(), this.leviathanParts[j].getY(), this.leviathanParts[j].getZ());
            }
            for (int l = 0; l < this.leviathanParts.length; ++l) {
                this.leviathanParts[l].xo = avector3d[l].x;
                this.leviathanParts[l].yo = avector3d[l].y;
                this.leviathanParts[l].zo = avector3d[l].z;
                this.leviathanParts[l].xOld = avector3d[l].x;
                this.leviathanParts[l].yOld = avector3d[l].y;
                this.leviathanParts[l].zOld = avector3d[l].z;
            }

        }
    }

    public void aiStep() {
        super.aiStep();
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

            if (this.getAnimationTick() >= 64) {
                this.setXRot(this.xRotO);
            }
            for (int i = 44, j = 0; i <= 84; i++, j++) {
                float l = j * 0.025f;
                if (this.getAnimationTick() == i) {
                    this.setLight(l);
                }
            }
            for (int i = 144, j = 1; i <= 184; i++, j++) {
                float l = j * -0.025f;
                if (this.getAnimationTick() == i) {
                    this.setLight(l);
                }
            }
            if(this.getAnimationTick() == 84 ) {
                ScreenShake_Entity.ScreenShake(this.level, this.position(), 30, 0.1f, 90, 10);
            }
        }
        if(this.getAnimation() == LEVIATHAN_RUSH){
            if (this.getAnimationTick() > 54 && this.getAnimationTick() < 137) {
                charge();
            }

            if (this.getAnimationTick() == 54) {
                this.level.playSound((Player) null, this, ModSounds.LEVIATHAN_ROAR.get(), SoundSource.HOSTILE, 4.0f, 1.0f);
                ScreenShake_Entity.ScreenShake(this.level, this.position(), 30, 0.1f, 60, 10);
            }
        }
        if(this.getAnimation() == LEVIATHAN_GRAB_BITE){
            if (this.getAnimationTick() == 2) {
                // charge();
                biteattack(6.5f,1.5,1.5,1.5);
            }
        }
        if(this.getAnimation() == LEVIATHAN_TENTACLE_STRIKE_UPPER_R){
            Tentacleattack(24,9,2,2,2);
        }
        if(this.getAnimation() == LEVIATHAN_TENTACLE_STRIKE_LOWER_R){
            Tentacleattack(28,9,2, 2,2);

        }
        if(this.getAnimation() == LEVIATHAN_TENTACLE_STRIKE_UPPER_L){
            Tentacleattack(26,9,2, 2,2);

        }
        if(this.getAnimation() == LEVIATHAN_TENTACLE_STRIKE_LOWER_L){
            Tentacleattack(21,9,2, 2,2);
        }

        if(this.getAnimation() == LEVIATHAN_STUN){
            if (this.getAnimationTick() == 52) {
                this.level.playSound((Player) null, this, ModSounds.LEVIATHAN_STUN_ROAR.get(), SoundSource.HOSTILE, 4.0f, 0.8f);
                ScreenShake_Entity.ScreenShake(this.level, this.position(), 30, 0.1f, 40, 10);
            }
        }
    }


    private void blockbreak(int x, int y, int z) {
        int MthX = Mth.floor(this.getX());
        int MthY = Mth.floor(this.getY());
        int MthZ = Mth.floor(this.getZ());
        if(!this.isNoAi()){
            if (!this.level.isClientSide) {
                if (this.destroyBlocksTick > 0) {
                    --this.destroyBlocksTick;
                    if (this.destroyBlocksTick == 0 && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                        boolean flag = false;
                        for (int k2 = -x; k2 <= x; ++k2) {
                            for (int l2 = -z; l2 <= z; ++l2) {
                                for (int j = 0; j <= y; ++j) {
                                    int i3 = MthX + k2;
                                    int k = MthY + j;
                                    int l = MthZ + l2;
                                    BlockPos blockpos = new BlockPos(i3, k, l);
                                    BlockState blockstate = this.level.getBlockState(blockpos);
                                    FluidState fluidState = level.getFluidState(blockpos);
                                    if (blockstate.getMaterial() != Material.AIR && blockstate.canEntityDestroy(this.level, blockpos, this) && fluidState.isEmpty() && !blockstate.is(ModTag.LEVIATHAN_IMMUNE) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                                        flag = this.level.destroyBlock(blockpos, false, this) || flag;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void charge() {
        if (!this.level.isClientSide) {
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                boolean flag = false;

                AABB aabb = this.getBoundingBox().inflate(4.5D, 0.5D, 4.5D);
                double yblockbreak = this.isInWater() ? aabb.minY : this.getY();
                for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(yblockbreak), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    FluidState fluidState = level.getFluidState(blockpos);
                    if (blockstate.getMaterial() != Material.AIR && blockstate.canEntityDestroy(this.level, blockpos, this) && fluidState.isEmpty() && !blockstate.is(ModTag.LEVIATHAN_IMMUNE) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                        flag = this.level.destroyBlock(blockpos, false, this) || flag;

                    }
                }
            }
        }
        if (this.tickCount % 4 == 0) {
            for (LivingEntity Lentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(3D))) {
                if (!isAlliedTo(Lentity) && !(Lentity instanceof The_Leviathan_Entity) && Lentity != this) {
                    boolean flag = Lentity.hurt(DamageSource.mobAttack(this),  (float) ((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
                    if (Lentity instanceof Player && Lentity.isBlocking()) {
                        disableShield(Lentity, 120);
                    }
                    if (flag) {
                        if (Lentity.isOnGround()) {
                            double d0 = Lentity.getX() - this.getX();
                            double d1 = Lentity.getZ() - this.getZ();
                            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                            float f = 1.5F;
                            Lentity.push(d0 / d2 * f, 0.5F, d1 / d2 * f);
                        }
                    }
                }
            }
        }
    }


    private void biteattack(float radius , double inflateX, double inflateY, double inflateZ) {
        double renderYaw = (this.yHeadRot +90) * Math.PI / 180.0d;
        double renderPitch = (float) (-this.getXRot() * Math.PI / 180.0d);

        endPosX = getX() + radius * Math.cos(renderYaw) * Math.cos(renderPitch);
        endPosZ = getZ() + radius * Math.sin(renderYaw) * Math.cos(renderPitch);
        endPosY = getY() + radius * Math.sin(renderPitch);
        if (!level.isClientSide) {
            List<LivingEntity> hit = raytraceEntities(level, inflateX, inflateY,inflateZ, new Vec3(getX(), getY(), getZ()), new Vec3(endPosX, endPosY, endPosZ) ).entities;
            for (LivingEntity target : hit) {
                if (!isAlliedTo(target) && !(target instanceof The_Leviathan_Entity) && target != this) {
                    boolean flag = target.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2);
                    if (target instanceof Player && target.isBlocking()) {
                        disableShield(target, 200);
                    }

                }
            }
        }
    }


    private void Tentacleattack(int anime, float radius, double inflateX, double inflateY, double inflateZ) {
        double renderYaw = (this.yHeadRot +90) * Math.PI / 180.0d;
        double renderPitch = (float) (-this.getXRot() * Math.PI / 180.0d);

        endPosX = getX() + radius * Math.cos(renderYaw) * Math.cos(renderPitch);
        endPosZ = getZ() + radius * Math.sin(renderYaw) * Math.cos(renderPitch);
        endPosY = getY() + radius * Math.sin(renderPitch);
        if(this.getAnimationTick() == anime){
            if (!level.isClientSide) {
                List<LivingEntity> hit = raytraceEntities(level, inflateX, inflateY,inflateZ, new Vec3(getX(), getY(), getZ()), new Vec3(endPosX, endPosY, endPosZ)).entities;
                for (LivingEntity target : hit) {
                    if (!isAlliedTo(target) && !(target instanceof The_Leviathan_Entity) && target != this) {
                        boolean flag = target.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                        if (target instanceof Player && target.isBlocking()) {
                            disableShield(target, 90);
                        }
                        if(flag){
                            double d0 = target.getX() - this.getX();
                            double d1 = target.getZ() - this.getZ();
                            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                            target.push(d0 / d2 * 7.0D, 0.2D, d1 / d2 * 7.0D);
                        }
                    }
                }

            }
        }
    }

    private BiteHitResult raytraceEntities(Level world, double inflateX, double inflateY, double inflateZ,Vec3 from, Vec3 to) {
        BiteHitResult result = new BiteHitResult();
        collidePosX = endPosX;
        collidePosY = endPosY;
        collidePosZ = endPosZ;

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AABB(Math.min(getX(), collidePosX), Math.min(getY(), collidePosY), Math.min(getZ(), collidePosZ), Math.max(getX(), collidePosX), Math.max(getY(), collidePosY), Math.max(getZ(), collidePosZ)).inflate(inflateX, inflateY, inflateZ));
        for (LivingEntity entity : entities) {
            float pad = 2.5f;
            AABB aabb = entity.getBoundingBox().inflate(pad, pad, pad);
            Optional<Vec3> hit = aabb.clip(from, to);
            if (aabb.contains(from)) {
                result.addEntityHit(entity);
            } else if (hit.isPresent()) {
                result.addEntityHit(entity);
            }
        }
        return result;
    }

    public static class BiteHitResult {
        private final List<LivingEntity> entities = new ArrayList<>();


        public void addEntityHit(LivingEntity entity) {
            entities.add(entity);
        }
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



    public boolean isPushedByFluid() {
        return false;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean checkSpawnObstruction(LevelReader worldIn) {
        return worldIn.isUnobstructed(this);
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


    public int getPortalTicks() {
        return this.entityData.get(PORTAL_TICKS);
    }

    public void setPortalTicks(int ticks) {
        this.entityData.set(PORTAL_TICKS, ticks);
    }

    public int getBlastChance() {
        return this.entityData.get(BLAST_CHANCE);
    }

    public void setBlastChance(int chance) {
        this.entityData.set(BLAST_CHANCE, chance);
    }

    public void createStuckPortal() {
        if (this.getTarget() != null) {
            createPortal(this.getTarget().position().add(random.nextInt(8) - 4, 2 + random.nextInt(3), random.nextInt(8) - 4));
        } else {
            Vec3 vec = Vec3.atCenterOf(level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, this.blockPosition().above(random.nextInt(10) + 10)));
            createPortal(vec);
        }
    }

    public void createPortal(Vec3 to) {
        createPortal(this.position().add(this.getLookAngle().scale(20)), to, null);
    }


    public void createPortalRandomDestination() {
        Vec3 vec = null;
        for (int i = 0; i < 15; i++) {
            BlockPos pos = new BlockPos(this.getX() + random.nextInt(60) - 30, 0, this.getZ() + random.nextInt(60) - 30);
            BlockPos height = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos);
            if(height.getY() < 10){
                height = height.above(50 + random.nextInt(50));
            }else{
                height = height.above(random.nextInt(30));
            }
            if (level.isEmptyBlock(height)) {
                vec = Vec3.atBottomCenterOf(height);
            }
        }
        if (vec != null) {
            createPortal(this.position().add(this.getLookAngle().scale(20)), vec, null);
        }
    }

    public void createPortal(Vec3 from, Vec3 to, @Nullable Direction outDir) {
        if (!level.isClientSide && portalTarget == null) {
            Vec3 Vector3d = new Vec3(this.getX(), this.getEyeY(), this.getZ());
            HitResult result = this.level.clip(new ClipContext(Vector3d, from, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            Vec3 vec = result.getLocation() != null ? result.getLocation() : this.position();
            if (result instanceof BlockHitResult) {
                BlockHitResult result1 = (BlockHitResult) result;
                vec = vec.add(net.minecraft.world.phys.Vec3.atLowerCornerOf(result1.getDirection().getNormal()));
            }
            Abyss_Portal_Entity portal = ModEntities.ABYSS_PORTAL.get().create(level);
            portal.setPos(vec.x, vec.y, vec.z);
            Vec3 dirVec = vec.subtract(this.position());
            Direction dir = Direction.getNearest(dirVec.x, dirVec.y, dirVec.z);
            portal.setAttachmentFacing(dir);
            portal.setEntrance(true);
            portal.setLifespan(10000);
            if (!level.isClientSide) {
                level.addFreshEntity(portal);
            }
            portalTarget = portal;
            portal.setDestination(new BlockPos(to.x, to.y, to.z), outDir);
            makePortalCooldown = 300;
        }
    }

    public void createPortal2(double x,double y,double z,@Nullable Direction outDir, Vec3 to, @Nullable Direction outDir2) {
        if (!level.isClientSide && portalTarget == null) {
            Abyss_Portal_Entity portal = ModEntities.ABYSS_PORTAL.get().create(level);
            portal.setPos(x, y, z);
            portal.setAttachmentFacing(outDir);
            portal.setLifespan(10000);
            portal.setEntrance(true);
            if (!level.isClientSide) {
                level.addFreshEntity(portal);
            }
            portalTarget = portal;
            portal.setDestination(new BlockPos(to.x, to.y, to.z), outDir2);
            makePortalCooldown = 300;
        }
    }

    public void resetPortalLogic() {
        portalTarget = null;
    }

    public void teleportTo(Vec3 vec) {
        teleportPos = vec;
        fullyThrough = false;

    }


    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
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

    protected SoundEvent getAmbientSound() {
        return ModSounds.LEVIATHAN_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.LEVIATHAN_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.LEVIATHAN_DEFEAT.get();
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.getX(), this.getEyeY(), this.getZ());

        return this.level.clip(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() != HitResult.Type.MISS;
    }

    private void setPartPosition(The_Leviathan_Part part, double offsetX, double offsetY, double offsetZ) {
        part.setPos(this.getX() + offsetX * part.scale, this.getY() + offsetY * part.scale, this.getZ() + offsetZ * part.scale);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public net.minecraftforge.entity.PartEntity<?>[] getParts() {
        return this.leviathanParts;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        Cm_Part_Entity.assignPartIDs(this);
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
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.start();
        }

        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
            entity.hunting_cooldown = GRAB_HUNTING_COOLDOWN;
            entity.setBlastChance(entity.getBlastChance() +1);
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
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
            if (this.entity.getAnimationTick() > 25 && this.entity.getAnimationTick() <= 85) {
                if (target != null && target.isAlive()) {
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
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            entity.playSound(SoundEvents.PHANTOM_BITE);
            super.start();
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            entity.setYRot(entity.yRotO);
        }
        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
            entity.hunting_cooldown = GRAB_HUNTING_COOLDOWN;
        }
    }

    static class LeviathanBlastAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanBlastAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            entity.level.playSound((Player) null, entity, ModSounds.ABYSS_BLAST.get(), SoundSource.HOSTILE, 4.0f, 1.0f);
            super.start();
        }

        public void stop() {
            super.stop();
            entity.hunting_cooldown = BLAST_HUNTING_COOLDOWN;
            entity.setBlastChance(0);
        }

        public void tick() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                if (this.entity.getAnimationTick() < 79) {
                    entity.getLookControl().setLookAt(target, 30, 90);
                }
            }
            float dir = 90.0f;
            if(this.entity.getAnimationTick() == 64) {
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


        public void start() {
            entity.getNavigation().stop();
            super.start();
        }

        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
            entity.hunting_cooldown = RUSH_HUNTING_COOLDOWN;
            entity.setBlastChance(entity.getBlastChance() +1);
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
                if (this.entity.getAnimationTick() > 53 && this.entity.getAnimationTick() < 134) {
                    final double d0 = target.getX() - entity.getX();
                    final double d1 = target.getEyeY() - entity.getEyeY();
                    final double d2 = target.getZ() - entity.getZ();
                    final double d3 = Mth.sqrt((float) (d0 * d0 + d2 * d2));
                    final float targetYaw = (float) (Mth.atan2(d2, d0) * 180.0F / (float) Math.PI - 90.0F);
                    final float targetPitch = (float) (-(Mth.atan2(d1, d3) * 180.0F / (float) Math.PI));
                    entity.setXRot((entity.getXRot() + Mth.clamp(targetPitch - entity.getXRot(), -2, 2)));
                    if (d0 * d0 + d2 * d2 >= 9) {
                        entity.setYRot((entity.getYRot() + Mth.clamp(targetYaw - entity.getYRot(), -2, 2)));
                        entity.yBodyRot = entity.getYRot();
                    }
                    if (Math.abs(Mth.wrapDegrees(targetYaw) - Mth.wrapDegrees(entity.getYRot())) < 4) {
                        final double distSq = d0 * d0 + d2 * d2;
                        if (distSq < 9) {
                            entity.setYRot(entity.yRotO);
                            entity.yBodyRot = entity.yRotO;
                            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.8, 1, 0.8));
                        } else {
                            if (entity.isInWater() && target.isInWater()) {
                                final Vec3 vector3d = entity.getDeltaMovement();
                                Vec3 vector3d1 = new Vec3(target.getX() - entity.getX(), target.getY() - entity.getY(), target.getZ() - entity.getZ());
                                if (vector3d1.lengthSqr() > 1.0E-7D) {
                                    vector3d1 = vector3d1.normalize().scale(0.5D).add(vector3d.scale(0.5D));
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





    static class LeviathanTentacleAttackGoal extends AnimationGoal<The_Leviathan_Entity> {

        public LeviathanTentacleAttackGoal(The_Leviathan_Entity entity) {
            super(entity);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        @Override
        protected boolean test(Animation animation) {
            return animation == LEVIATHAN_TENTACLE_STRIKE_UPPER_R
                    || animation == LEVIATHAN_TENTACLE_STRIKE_LOWER_R
                    || animation == LEVIATHAN_TENTACLE_STRIKE_UPPER_L
                    || animation == LEVIATHAN_TENTACLE_STRIKE_LOWER_L;
        }

        public void start() {
            entity.getNavigation().stop();
            super.start();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
        }

        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
            entity.hunting_cooldown = TENTACLE_STRIKE_HUNTING_COOLDOWN;
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);

            }
        }
    }





    static class LeviathanAbyssBlastPortalAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanAbyssBlastPortalAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            super.start();
        }

        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
            entity.hunting_cooldown = BLAST_HUNTING_COOLDOWN;
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
                if (this.entity.getAnimationTick() == 48) {
                    Abyss_Blast_Portal_Entity laserBeam = new Abyss_Blast_Portal_Entity(entity.level, entity.getX(), entity.getY() +5,entity.getZ(), entity);
                    double d0 = entity.getTarget().getX() - laserBeam.getX();
                    double d1 = entity.getTarget().getY() + entity.getTarget().getBbHeight() * 0.5f - laserBeam.getY();
                    double d2 = entity.getTarget().getZ() - laserBeam.getZ();

                    double b0 = laserBeam.getX() - entity.getTarget().getX();
                    double b2 = laserBeam.getZ() - entity.getTarget().getZ();

                    float f = (float) (Mth.atan2(b2, b0) * (180D / Math.PI)) - 90.0F;
                    laserBeam.setYRot(f % 360);
                    double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                    float f1 = (float) (-(Mth.atan2(d1, d3) * (180D / Math.PI)));
                    laserBeam.setXRot(updateRotation(laserBeam.getXRot(), f1, 90F));


                    entity.level.addFreshEntity(laserBeam);

                }
            }
        }


        private static float updateRotation(float angle, float targetAngle, float maxIncrease) {
            float f = Mth.wrapDegrees(targetAngle - angle);
            if (f > maxIncrease) {
                f = maxIncrease;
            }
            if (f < -maxIncrease) {
                f = -maxIncrease;
            }
            return angle + f;
        }

    }


    static class LeviathanStunGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanStunGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            super.start();
        }

        public void stop() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.stop();
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                if (this.entity.getAnimationTick() > 28) {
                    entity.getLookControl().setLookAt(target, 30, 90);
                }
            }
        }
    }

    static class LeviathanAIFindWaterAndPortal extends Goal {
        private final The_Leviathan_Entity creature;
        private BlockPos targetPos;

        public LeviathanAIFindWaterAndPortal(The_Leviathan_Entity creature) {
            this.creature = creature;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            if (this.creature.isOnGround() && !this.creature.level.getFluidState(this.creature.blockPosition()).is(FluidTags.WATER)) {
                if (this.creature.shouldEnterWater()) {
                    targetPos = generateTarget();
                    return targetPos != null;
                }
            }
            return false;
        }

        public void start() {
            if (targetPos != null) {
                Vec3 to = new Vec3(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                this.creature.createPortal2(this.creature.getX() , this.creature.getY() + 0.1,this.creature.getZ(),Direction.UP, to, null);
            }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (targetPos != null) {
                if (this.creature.portalTarget != null) {
                    double centerX = this.creature.portalTarget.getX();
                    double centerY = this.creature.portalTarget.getY();
                    double centerZ = this.creature.portalTarget.getZ();
                    this.creature.getNavigation().moveTo(centerX, centerY, centerZ, 1D);
                    // this.creature.getMoveControl().setWantedPosition(centerX, centerY, centerZ, 1.0D);
                    this.creature.lookAt(this.creature.portalTarget, 30.0F, 30.0F);
                }
            }
        }


        public BlockPos generateTarget() {
            BlockPos blockpos = null;
            final RandomSource random = this.creature.getRandom();
            final int range = this.creature.getWaterSearchRange();
            for(int i = 0; i < 15; i++) {
                BlockPos blockPos = this.creature.blockPosition().offset(random.nextInt(range) - range/2, 3, random.nextInt(range) - range/2);
                while (this.creature.level.isEmptyBlock(blockPos) && blockPos.getY() > 1) {
                    blockPos = blockPos.below();
                }

                if (this.creature.level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    blockpos = blockPos;
                }
            }
            return blockpos;
        }
    }





    static class LeviathanAttackGoal extends Goal {
        private final The_Leviathan_Entity mob;
        private LivingEntity target;
        private float circlingTime = 0;
        private float circleDistance = 18;
        private boolean clockwise = false;

        public LeviathanAttackGoal(The_Leviathan_Entity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            this.target = this.mob.getTarget();
            return this.target != null && target.isAlive() && this.mob.getAnimation() == NO_ANIMATION;
        }

        public boolean canContinueToUse() {
            this.target = this.mob.getTarget();
            return this.target != null;
        }

        public void start(){
            circlingTime = 0;
            circleDistance = 18 + this.mob.random.nextInt(10);
            clockwise = this.mob.random.nextBoolean();
            this.mob.setAggressive(true);
        }

        public void stop() {
            circlingTime = 0;
            circleDistance = 18 + this.mob.random.nextInt(10);
            clockwise = this.mob.random.nextBoolean();
            this.target = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.getNavigation().stop();
            if (this.mob.getTarget() == null) {
                this.mob.setAggressive(false);
                this.mob.getNavigation().stop();
            }
        }


        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target != null) {
                double dist = this.mob.distanceTo(target );
                if(circlingTime >= this.mob.hunting_cooldown){
                    if (this.mob.getRandom().nextFloat() * 100.0F < (2f * this.mob.getBlastChance())){
                        this.mob.setAnimation(LEVIATHAN_ABYSS_BLAST);
                    }else if(this.mob.getRandom().nextFloat() * 100.0F < 8f && this.mob.distanceToSqr(target) >= 900.0D){
                        this.mob.setAnimation(LEVIATHAN_GRAB);
                    }else if (this.mob.getRandom().nextFloat() * 100.0F < 6f && this.mob.distanceToSqr(target) < 900.0D && this.mob.distanceToSqr(target) >= 49.0D){
                        this.mob.setAnimation(LEVIATHAN_RUSH);
                    }
                }else{
                    if(this.mob.getRandom().nextFloat() * 100.0F < 12f && this.mob.distanceToSqr(target) <= 49.0D) {
                        Animation animation = getRandomTantalcleStrike(this.mob.random);
                        this.mob.setAnimation(animation);
                    }
                }
                BlockPos circlePos = getLeviathanCirclePos(target);
                if(circlePos != null){
                    this.mob.getNavigation().moveTo(circlePos.getX() + 0.5D, circlePos.getY(), circlePos.getZ() + 0.5D, 1.0D);
                }

            }
        }

        public BlockPos getLeviathanCirclePos(LivingEntity target) {
            float angle = (0.01745329251F * (clockwise ? -circlingTime : circlingTime));
            double extraX = circleDistance * Mth.sin((angle));
            double extraZ = circleDistance * Mth.cos(angle);

            return new BlockPos(target.getX() + 0.5F + extraX, target.getY() + 4.0f, target.getZ() + 0.5F + extraZ);
        }

    }


    static class LeviathanMoveController extends MoveControl {
        private final The_Leviathan_Entity entity;
        private final float speedMulti;
        private final float ySpeedMod;
        private final float yawLimit;
        private  int stillTicks = 0;
        public LeviathanMoveController(The_Leviathan_Entity entity, float speedMulti, float ySpeedMod, float yawLimit) {
            super(entity);
            this.entity = entity;
            this.speedMulti = speedMulti;
            this.ySpeedMod = ySpeedMod;
            this.yawLimit = yawLimit;
        }

        public void tick() {
            if (this.entity.isInWater() && this.entity.getAnimation() == NO_ANIMATION) {

                if (Math.abs(this.entity.xo - this.entity.getX()) < 0.01F && Math.abs(this.entity.yo - this.entity.getY()) < 0.01F && Math.abs(this.entity.zo - this.entity.getZ()) < 0.01F) {
                    stillTicks++;
                } else {
                    stillTicks = 0;
                }
                if (stillTicks > 40){
                    this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, -0.005D, 0.0D));

                }
            }
            if (((ISemiAquatic) entity).shouldStopMoving()) {
                this.entity.setSpeed(0.0F);
                return;
            }
            if (this.operation == Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
                double lvt_1_1_ = this.wantedX - this.entity.getX();
                double lvt_3_1_ = this.wantedY - this.entity.getY();
                double lvt_5_1_ = this.wantedZ - this.entity.getZ();
                double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
                if (lvt_7_1_ < 2.500000277905201E-7D) {
                    this.mob.setZza(0.0F);
                } else {
                    float lvt_9_1_ = (float) (Mth.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
                    this.entity.setYRot(this.rotlerp(this.entity.getYRot(), lvt_9_1_, yawLimit));
                    this.entity.yBodyRot = this.entity.getYRot();
                    this.entity.yHeadRot = this.entity.getYRot();
                    float lvt_10_1_ = (float) (this.speedModifier * speedMulti * 3 * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.entity.isInWater()) {
                        if(lvt_3_1_ > 0 && entity.horizontalCollision){
                            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.08F, 0.0D));
                        }else{
                            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, (double) this.entity.getSpeed() * lvt_3_1_ * 0.6D * ySpeedMod, 0.0D));
                        }
                        this.entity.setSpeed(lvt_10_1_ * 0.02F);
                        float lvt_11_1_ = -((float) (Mth.atan2(lvt_3_1_, Mth.sqrt((float) (lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_))) * 57.2957763671875D));
                        lvt_11_1_ = Mth.clamp(Mth.wrapDegrees(lvt_11_1_), -85.0F, 85.0F);
                        this.entity.setXRot(this.rotlerp(this.entity.getXRot(), lvt_11_1_, 5.0F));
                        float lvt_12_1_ = Mth.cos(this.entity.getXRot() * 0.017453292F);
                        float lvt_13_1_ = Mth.sin(this.entity.getXRot() * 0.017453292F);
                        this.entity.zza = lvt_12_1_ * lvt_10_1_;
                        this.entity.yya = -lvt_13_1_ * lvt_10_1_;
                    } else {
                        this.entity.setSpeed(lvt_10_1_ * 0.1F);
                    }

                }
            } else {
                this.entity.setSpeed(0.0F);
                this.entity.setXxa(0.0F);
                this.entity.setYya(0.0F);
                this.entity.setZza(0.0F);
            }
        }
    }

}