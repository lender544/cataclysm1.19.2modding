package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.AI.EntityAINearestTarget3D;
import com.github.L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.Boss_monster;
import com.github.L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.*;
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
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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

    public static final Animation LEVIATHAN_GRAB = Animation.create(115);
    public static final Animation LEVIATHAN_GRAB_BITE = Animation.create(13);
    public static final Animation LEVIATHAN_ABYSS_BLAST = Animation.create(184);
    public static final Animation LEVIATHAN_RUSH = Animation.create(109);
    public static final Animation LEVIATHAN_ABYSS_BLAST_PORTAL = Animation.create(100);
    public float NoSwimProgress = 0;
    public float prevNoSwimProgress = 0;
    private boolean isLandNavigator;
    public Vec3 teleportPos = null;
    public Abyss_Portal_Entity portalTarget = null;
    public boolean fullyThrough = true;
    public boolean updatePostSummon = false;
    private int makePortalCooldown = 0;
    private int stillTicks = 0;
    public double endPosX, endPosY, endPosZ;
    public double collidePosX, collidePosY, collidePosZ;

    private static final EntityDataAccessor<Float> LIGHT = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> PORTAL_TICKS = SynchedEntityData.defineId(The_Leviathan_Entity.class, EntityDataSerializers.INT);

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
        this.entityData.define(PORTAL_TICKS, 0);
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LeviathanAIFindWaterAndPortal(this));
      //  this.goalSelector.addGoal(2, new AIEnterPortal(this));
        this.goalSelector.addGoal(4, new LeviathanAIRandomSwimming(this, 1F, 3, 15));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new LeviathanGrabAttackGoal(this,LEVIATHAN_GRAB));
        this.goalSelector.addGoal(0, new LeviathanGrabBiteAttackGoal(this,LEVIATHAN_GRAB_BITE));
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
        return new Animation[]{LEVIATHAN_GRAB,LEVIATHAN_GRAB_BITE,LEVIATHAN_ABYSS_BLAST,LEVIATHAN_RUSH};
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

    public boolean hurt(DamageSource source, float damage) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof Abyss_Portal_Entity || entity instanceof Portal_Abyss_Blast_Entity) {
            return false;
        } else {
            return super.hurt(source, damage);
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
        if (this.portalTarget != null && this.portalTarget.getLifespan() < 5) {
            this.portalTarget = null;
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
        if (this.getAnimation() == NO_ANIMATION && target!=null) {
            this.setAnimation(LEVIATHAN_GRAB);
        }


        if (teleportPos != null) {
            this.setPos(teleportPos.x, teleportPos.y, teleportPos.z);
            teleportPos = null;
        }
        if (makePortalCooldown > 0) {
            makePortalCooldown--;
        }


        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        if (Math.abs(xo - this.getX()) < 0.01F && Math.abs(yo - this.getY()) < 0.01F && Math.abs(zo - this.getZ()) < 0.01F) {
            stillTicks++;
        } else {
            stillTicks = 0;
        }
        if (stillTicks > 40 && makePortalCooldown == 0) {
           // createStuckPortal();
            //worminStuck
        }
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
                ScreenShake_Entity.ScreenShake(this.level, this.position(), 20, 0.1f, 90, 10);
            }
        }
        if(this.getAnimation() == LEVIATHAN_RUSH){
            if (this.getAnimationTick() > 48 && this.getAnimationTick() < 89) {
                charge();
            }
        }
        if(this.getAnimation() == LEVIATHAN_GRAB_BITE){
            if (this.getAnimationTick() == 2) {
               // charge();
                biteattack(6.5f);
            }
        }
    }

    private void charge() {
            if (!this.level.isClientSide) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                    boolean flag = false;
                    AABB aabb = this.getBoundingBox().inflate(2.0D, 0.2D, 2.0D);
                    for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                        BlockState blockstate = this.level.getBlockState(blockpos);
                        FluidState fluidState = level.getFluidState(blockpos);
                        if (blockstate.getMaterial() != Material.AIR && blockstate.canEntityDestroy(this.level, blockpos, this) && fluidState.isEmpty() && !blockstate.is(ModTag.LEVIATHAN_IMMUNE) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                            flag = this.level.destroyBlock(blockpos, false, this) || flag;

                        }
                    }
                }
            }
            if (this.tickCount % 6 == 0) {
                for (LivingEntity Lentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5D))) {
                    if (!isAlliedTo(Lentity) && !(Lentity instanceof The_Harbinger_Entity) && Lentity != this) {
                        boolean flag = Lentity.hurt(DamageSource.mobAttack(this),  (float) ((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) + this.random.nextInt(5) + Math.min(this.getAttributeValue(Attributes.ATTACK_DAMAGE), Lentity.getMaxHealth() * CMConfig.HarbingerChargeHpDamage)));
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


    private void biteattack(float radius) {
        double renderYaw = (this.yHeadRot +90) * Math.PI / 180.0d;
        double renderPitch = (float) (-this.getXRot() * Math.PI / 180.0d);

        endPosX = getX() + radius * Math.cos(renderYaw) * Math.cos(renderPitch);
        endPosZ = getZ() + radius * Math.sin(renderYaw) * Math.cos(renderPitch);
        endPosY = getY() + radius * Math.sin(renderPitch);
        if (!level.isClientSide) {
            List<LivingEntity> hit = raytraceEntities(level, new Vec3(getX(), getY(), getZ()), new Vec3(endPosX, endPosY, endPosZ)).entities;
            for (LivingEntity target : hit) {
                if (!isAlliedTo(target) && !(target instanceof The_Leviathan_Entity) && target != this) {
                    boolean flag = target.hurt(DamageSource.mobAttack(this), 3);
                }
            }
        }
    }

    private BiteHitResult raytraceEntities(Level world, Vec3 from, Vec3 to) {
        BiteHitResult result = new BiteHitResult();
        collidePosX = endPosX;
        collidePosY = endPosY;
        collidePosZ = endPosZ;

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AABB(Math.min(getX(), collidePosX), Math.min(getY(), collidePosY), Math.min(getZ(), collidePosZ), Math.max(getX(), collidePosX), Math.max(getY(), collidePosY), Math.max(getZ(), collidePosZ)).inflate(1, 1, 1));
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
        stillTicks = 0;
    }

    public void teleportTo(Vec3 vec) {
        teleportPos = vec;
        fullyThrough = false;

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
                entity.getLookControl().setLookAt(target, 30, 90);
            }
            super.start();
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
            if (this.entity.getAnimationTick() > 25 && this.entity.getAnimationTick() <= 95) {
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

        public void tick() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.getLookControl().setLookAt(target, 30, 90);
                if (this.entity.getAnimationTick() > 48 && this.entity.getAnimationTick() < 89) {
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


    static class LeviathanAbyssBlastPortalAttackGoal extends SimpleAnimationGoal<The_Leviathan_Entity> {

        public LeviathanAbyssBlastPortalAttackGoal(The_Leviathan_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
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

    static class AIEnterPortal extends Goal {
        private final The_Leviathan_Entity leviathan;
        public AIEnterPortal(The_Leviathan_Entity leviathan) {
            super();
            this.leviathan = leviathan;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return leviathan.portalTarget != null;
        }

        public void tick() {
            if (leviathan.portalTarget != null) {
                double centerX = leviathan.portalTarget.getX();
                double centerY = leviathan.portalTarget.getY(0.5F);
                double centerZ = leviathan.portalTarget.getZ();

                leviathan.getNavigation().moveTo(centerX, centerY, centerZ, 1D);
            }
        }

    }

    static class LeviathanAIFindWaterAndPortal extends Goal {
        private final The_Leviathan_Entity creature;
        private BlockPos targetPos;
        private final int executionChance = 30;

        public LeviathanAIFindWaterAndPortal(The_Leviathan_Entity creature) {
            this.creature = creature;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            if (this.creature.isOnGround() && !this.creature.level.getFluidState(this.creature.blockPosition()).is(FluidTags.WATER)) {
                if (this.creature.shouldEnterWater() && this.creature.getRandom().nextInt(executionChance) == 0) {
                    targetPos = generateTarget();
                    return targetPos != null;
                }
            }
            return false;
        }

        public void start() {
            if (targetPos != null) {
                Vec3 to = new Vec3(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                //this.creature.createPortal(this.creature.position().add(this.creature.getLookAngle().scale(8)), to, null);
                double distanceXZ = this.creature.distanceToSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                if(distanceXZ > 255){
                  //  this.creature.createPortal(this.creature.position().add(this.creature.getLookAngle().scale(8)), to, null);
                    this.creature.createPortal2(this.creature.getX() , this.creature.getY() + 0.1,this.creature.getZ(),Direction.UP, to, null);
                }
            }
        }


        public void tick() {
            if (targetPos != null) {
                if (this.creature.portalTarget != null) {
                    double centerX = this.creature.portalTarget.getX();
                    double centerY = this.creature.portalTarget.getY();
                    double centerZ = this.creature.portalTarget.getZ();
                    this.creature.getNavigation().moveTo(centerX, centerY, centerZ, 2D);
                   // this.creature.getMoveControl().setWantedPosition(centerX, centerY, centerZ, 1.0D);
                    this.creature.getLookControl().setLookAt(this.creature.portalTarget, 30, 90);
                } else {
                    this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 2D);
                //    this.creature.getMoveControl().setWantedPosition(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1.0D);
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

    static class LeviathanMoveController extends MoveControl {
        private final The_Leviathan_Entity entity;
        private final float speedMulti;
        private final float ySpeedMod;
        private final float yawLimit;

        public LeviathanMoveController(The_Leviathan_Entity entity, float speedMulti, float ySpeedMod, float yawLimit) {
            super(entity);
            this.entity = entity;
            this.speedMulti = speedMulti;
            this.ySpeedMod = ySpeedMod;
            this.yawLimit = yawLimit;
        }

        public void tick() {
            if (this.entity.isInWater() && this.entity.getAnimation() == NO_ANIMATION) {
                 this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
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
