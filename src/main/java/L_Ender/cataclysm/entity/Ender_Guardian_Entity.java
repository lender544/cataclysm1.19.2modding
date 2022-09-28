package L_Ender.cataclysm.entity;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.AI.*;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import L_Ender.cataclysm.entity.projectile.Ender_Guardian_Bullet_Entity;
import L_Ender.cataclysm.entity.projectile.Void_Rune_Entity;
import L_Ender.cataclysm.init.ModSounds;
import L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class Ender_Guardian_Entity extends Boss_monster {

    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    private static final EntityDataAccessor<Boolean> IS_HELMETLESS = SynchedEntityData.defineId(Ender_Guardian_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_AWAKEN = SynchedEntityData.defineId(Ender_Guardian_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> USED_MASS_DESTRUCTION = SynchedEntityData.defineId(Ender_Guardian_Entity.class, EntityDataSerializers.BOOLEAN);
    public static final Animation GUARDIAN_RIGHT_STRONG_ATTACK = Animation.create(60);
    public static final Animation GUARDIAN_LEFT_STRONG_ATTACK = Animation.create(60);
    public static final Animation GUARDIAN_RIGHT_ATTACK = Animation.create(40);
    public static final Animation GUARDIAN_LEFT_ATTACK = Animation.create(40);
    public static final Animation GUARDIAN_BURST_ATTACK = Animation.create(53);
    public static final Animation GUARDIAN_UPPERCUT_AND_BULLET = Animation.create(100);
    public static final Animation GUARDIAN_RAGE_UPPERCUT = Animation.create(120);
    public static final Animation GUARDIAN_STOMP = Animation.create(48);
    public static final Animation GUARDIAN_RAGE_STOMP = Animation.create(83);
    public static final Animation GUARDIAN_MASS_DESTRUCTION = Animation.create(75);
    public static final Animation GUARDIAN_FALLEN = Animation.create(196);
    public static final int STOMP_COOLDOWN = 400;
    public float deactivateProgress;
    public float prevdeactivateProgress;
    public boolean Breaking = CMConfig.EnderguardianBlockBreaking;

    private int stomp_cooldown = 0;

    public Ender_Guardian_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 300;
        this.maxUpStep = 1.75F;
        this.dropAfterDeathAnim = true;
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        setConfigattribute(this, CMConfig.EnderguardianHealthMultiplier, CMConfig.EnderguardianDamageMultiplier);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,
                GUARDIAN_RIGHT_STRONG_ATTACK,
                GUARDIAN_LEFT_STRONG_ATTACK,
                GUARDIAN_BURST_ATTACK,
                GUARDIAN_UPPERCUT_AND_BULLET,
                GUARDIAN_STOMP,
                GUARDIAN_RIGHT_ATTACK,
                GUARDIAN_LEFT_ATTACK,
                GUARDIAN_MASS_DESTRUCTION,
                GUARDIAN_RAGE_STOMP,
                GUARDIAN_RAGE_UPPERCUT,
                GUARDIAN_FALLEN};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new AttackMoveGoal(this,true,1.0));
        this.goalSelector.addGoal(1, new PunchAttackGoal(this));
        this.goalSelector.addGoal(1, new AttackAnimationGoal2<>(this, GUARDIAN_MASS_DESTRUCTION, 39, 50));
        this.goalSelector.addGoal(1, new AttackAnimationGoal2<>(this, GUARDIAN_BURST_ATTACK, 27, 47));
        this.goalSelector.addGoal(1, new StompAttackGoal(this));
        this.goalSelector.addGoal(1, new UppercutAndBulletGoal(this,GUARDIAN_UPPERCUT_AND_BULLET));
        this.goalSelector.addGoal(1, new RageUppercut(this,GUARDIAN_RAGE_UPPERCUT));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

    }

    public static AttributeSupplier.Builder ender_guardian() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.27F)
                .add(Attributes.ATTACK_DAMAGE, 16)
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_HELMETLESS, false);
        this.entityData.define(IS_AWAKEN, true);
        this.entityData.define(USED_MASS_DESTRUCTION, false);
    }

    private static Animation getRandomAttack(RandomSource rand) {
        switch (rand.nextInt(4)) {
            case 0:
                return GUARDIAN_RIGHT_STRONG_ATTACK;
            case 1:
                return GUARDIAN_LEFT_STRONG_ATTACK;
            case 2:
                return GUARDIAN_RIGHT_ATTACK;
            case 3:
                return GUARDIAN_LEFT_ATTACK;
        }
        return GUARDIAN_RIGHT_STRONG_ATTACK;
    }


    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("is_Helmetless", getIsHelmetless());
        compound.putBoolean("is_Awaken", getIsAwaken());
        compound.putBoolean("used_mass_destruction", getUsedMassDestruction());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setIsHelmetless(compound.getBoolean("is_Helmetless"));
        setIsAwaken(compound.getBoolean("is_Awaken"));
        setUsedMassDestruction(compound.getBoolean("used_mass_destruction"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    public void setIsHelmetless(boolean isHelmetless) {
        this.entityData.set(IS_HELMETLESS, isHelmetless);

    }

    public boolean getIsHelmetless() {
        return this.entityData.get(IS_HELMETLESS);

    }

    public void setUsedMassDestruction(boolean usedMassDestruction) {
        this.entityData.set(USED_MASS_DESTRUCTION, usedMassDestruction);
    }

    public boolean getUsedMassDestruction() {
        return this.entityData.get(USED_MASS_DESTRUCTION);
    }

    public void setIsAwaken(boolean isAwaken) {
        this.entityData.set(IS_AWAKEN, isAwaken);
    }

    public boolean getIsAwaken() {
        return this.entityData.get(IS_AWAKEN);
    }


    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (this.getAnimation() == GUARDIAN_MASS_DESTRUCTION && !source.isBypassInvul()) {
            return false;
        }
        if (!source.isBypassInvul()) {
            damage = Math.min(CMConfig.EnderguardianDamageCap, damage);
        }
        double range = calculateRange(source);

        if (range > CMConfig.EnderguardianLongRangelimit * CMConfig.EnderguardianLongRangelimit) {
            return false;
        }
        Entity entity = source.getDirectEntity();
        if (!this.getIsHelmetless()) {
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }
        if (entity instanceof ShulkerBullet || entity instanceof Ender_Guardian_Bullet_Entity) {
            return false;
        }
        if (entity instanceof AbstractGolem) {
            damage *= 0.5;
        }

        return super.hurt(source, damage);
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    public void tick() {
        super.tick();
        setYRot(yBodyRot);
        //prevgetYRot() = getYRot();
        if (!this.isSilent() && !level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 67);
        }
        repelEntities(1.8F, 4.0f, 1.8F, 1.8F);
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
        prevdeactivateProgress = deactivateProgress;
        if (!this.getIsAwaken() && deactivateProgress < 40F) {
            deactivateProgress = 40;
        }
        LivingEntity target = this.getTarget();
        Animation animation = getRandomAttack(random);
        if (this.isAlive()) {
            if (!this.getIsHelmetless() && this.isHelmetless()) {
                this.setIsHelmetless(true);
                BrokenHelmet();
            }
            if (!isNoAi() && this.getAnimation() == NO_ANIMATION && !this.getUsedMassDestruction() && this.isHelmetless()) {
                this.setAnimation(GUARDIAN_MASS_DESTRUCTION);
            }
            else if (target != null && target.isAlive()) {
                if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 2.75F) {
                    if (this.random.nextInt(2) == 0) {
                        this.setAnimation(GUARDIAN_BURST_ATTACK);
                    } else {
                        this.setAnimation(animation);
                    }
                } else if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.distanceTo(target) > 2.75F && this.distanceTo(target) < 4.3F && target.hasEffect(MobEffects.LEVITATION)) {
                    if (this.random.nextInt(3) == 0) {
                        if (this.getIsHelmetless()) {
                            this.setAnimation(GUARDIAN_RAGE_UPPERCUT);
                        } else {
                            this.setAnimation(GUARDIAN_UPPERCUT_AND_BULLET);
                        }
                    } else {
                        this.setAnimation(GUARDIAN_BURST_ATTACK);
                    }
                } else if (stomp_cooldown <= 0 && !isNoAi() && this.getAnimation() == NO_ANIMATION && target.isOnGround()&& (this.distanceTo(target) > 6F && this.distanceTo(target) < 13F || this.distanceTo(target) > 2.75F && this.distanceTo(target) < 4.3F && this.random.nextInt(12) == 0)) {
                    stomp_cooldown = STOMP_COOLDOWN;
                    if (this.getIsHelmetless()) {
                        this.setAnimation(GUARDIAN_RAGE_STOMP);
                    } else {
                        this.setAnimation(GUARDIAN_STOMP);

                    }
                } else if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.distanceTo(target) > 2.75F && this.distanceTo(target) < 4.3F) {
                    if (this.random.nextInt(4) == 0) {
                        if (this.getIsHelmetless()) {
                            this.setAnimation(GUARDIAN_RAGE_UPPERCUT);
                        } else {
                            this.setAnimation(GUARDIAN_UPPERCUT_AND_BULLET);

                        }
                    } else {
                        this.setAnimation(animation);
                    }
                }
            }
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (this.getIsHelmetless()) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(10F);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.29F);
        }

        if (this.getAnimation() == GUARDIAN_LEFT_STRONG_ATTACK) {
            if (this.getAnimationTick() < 2) {
                GravityPullparticle();
            }
            if (this.getAnimationTick() < 29) {
                GravityPull();
            }
            if (this.getAnimationTick() == 34) {
                this.playSound(ModSounds.ENDER_GUARDIAN_FIST.get(), 0.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(5.15f,5,70,1.1f,100);
                Attackparticle(2.2f,0);
                ScreenShake_Entity.ScreenShake(level, this.position(), 20, 0.2f, 0, 10);
            }

        }
        if (this.getAnimation() == GUARDIAN_RIGHT_STRONG_ATTACK) {
            if (this.getAnimationTick() < 2) {
                GravityPullparticle();
            }
            if (this.getAnimationTick() < 24) {
                GravityPull();
            }
            if (this.getAnimationTick() == 29) {
                AreaAttack(5.15f,5,70,1.1f,100);
                this.playSound(ModSounds.ENDER_GUARDIAN_FIST.get(), 0.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                Attackparticle(2.2f,0);
                ScreenShake_Entity.ScreenShake(level, this.position(), 20, 0.2f, 0, 10);
            }

        }

        if (this.getAnimation() == GUARDIAN_RIGHT_ATTACK) {
            if (this.getAnimationTick() == 22) {
                AreaAttack(5.85f,5,80,1,80);
                this.playSound(ModSounds.ENDER_GUARDIAN_FIST.get(), 0.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                Attackparticle(2.75f,0.5f);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 10);
            }
        }
        if (this.getAnimation() == GUARDIAN_LEFT_ATTACK) {
            if (this.getAnimationTick() == 19) {
                AreaAttack(5.85f,5,80,1,80);
                this.playSound(ModSounds.ENDER_GUARDIAN_FIST.get(), 0.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                Attackparticle(2.75f,-0.5f);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.1f, 0, 10);
            }
        }

        if (this.getAnimation() == GUARDIAN_BURST_ATTACK) {
            if (this.getAnimationTick() == 15) {
                Burstparticle();
            }
            if (this.getAnimationTick() == 27) {
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(7.5f,6,100,1,0);
            }
        }
        if (this.getAnimation() == GUARDIAN_UPPERCUT_AND_BULLET || this.getAnimation() == GUARDIAN_RAGE_UPPERCUT) {
            if (this.getAnimationTick() == 27) {
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(6.25f,6,60,1.5f,150);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.3f, 0, 10);
            }
        }
        if (this.getAnimation() == GUARDIAN_STOMP) {
            if (this.getAnimationTick() == 32) {
                StompAttack();
                Attackparticle(0.4f,0.8f);
                ScreenShake_Entity.ScreenShake(level, this.position(), 10, 0.1f, 0, 5);
            }
        }
        if (this.getAnimation() == GUARDIAN_RAGE_STOMP) {
            if (this.getAnimationTick() == 32 || this.getAnimationTick() == 53 || this.getAnimationTick() == 62) {
                StompAttack();
                Attackparticle(0.4f,0.8f);
                ScreenShake_Entity.ScreenShake(level, this.position(), 10, 0.1f, 0, 5);
            }
        }
        if (this.getAnimation() == GUARDIAN_RAGE_UPPERCUT) {
            if (this.getAnimationTick() == 84) {
                RageAttack();
                AreaAttack(5.5f,5,120,1.2f,100);
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5f, 1F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.2f, 0, 10);
            }
        }

        if (this.getAnimation() == GUARDIAN_MASS_DESTRUCTION) {
            this.setUsedMassDestruction(true);
            if (this.getAnimationTick() == 39) {
                Attackparticle(2.75f,2.25f);
                Attackparticle(2.75f,-2.25f);
                MassDestruction(5.0f, 1.1f,150);
                ScreenShake_Entity.ScreenShake(level, this.position(), 15, 0.3f, 0, 10);
                if (!this.level.isClientSide) {
                    if (Breaking) {
                        BlockBreaking(CMConfig.EnderguardianBlockBreakingX, CMConfig.EnderguardianBlockBreakingY, CMConfig.EnderguardianBlockBreakingZ);
                    } else {
                        if (ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                            BlockBreaking(CMConfig.EnderguardianBlockBreakingX, CMConfig.EnderguardianBlockBreakingY, CMConfig.EnderguardianBlockBreakingZ);
                        }
                    }
                }
            }
        }

        if (stomp_cooldown > 0) stomp_cooldown--;

    }

    public boolean isHelmetless() {
        return this.getHealth() <= this.getMaxHealth() / 2.0F;
    }

    @Override
    protected void onDeathAIUpdate() {
        super.onDeathAIUpdate();
        setDeltaMovement(0, Ender_Guardian_Entity.this.getDeltaMovement().y, 0);
        if (this.deathTime == 50) {
            this.playSound(ModSounds.MONSTROSITYLAND.get(), 1, 1);
        }
        if (this.deathTime == 100) {
            this.playSound(SoundEvents.SHULKER_TELEPORT, 1, 1);
        }

    }

    @Nullable
    public Animation getDeathAnimation()
    {
        return GUARDIAN_FALLEN;
    }


    private void AreaAttack(float range,float height,float arc ,float damage,int ticks) {
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
                if (!(entityHit instanceof Ender_Guardian_Entity)) {
                    entityHit.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage);
                    if (entityHit instanceof Player && entityHit.isBlocking() && !(this.getAnimation() == GUARDIAN_BURST_ATTACK)) {
                        disableShield(entityHit, ticks);
                    }
                    if(this.getAnimation() == GUARDIAN_UPPERCUT_AND_BULLET || this.getAnimation() == GUARDIAN_RAGE_UPPERCUT){
                        entityHit.setDeltaMovement(entityHit.getDeltaMovement().add(0.0D, 0.5F, 0.0D));
                    }
                    if(this.getAnimation() == GUARDIAN_BURST_ATTACK){
                        launch(entityHit);
                    }
                }
            }
        }
    }

    private void MassDestruction(float grow, float damage, int ticks) {
        this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5f, 1F + this.getRandom().nextFloat() * 0.1F);
        for (LivingEntity entityHit : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(grow))) {
            if (!isAlliedTo(entityHit) && !(entityHit instanceof Ender_Guardian_Entity) && entityHit != this) {
                entityHit.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage);
                if (entityHit instanceof Player && entityHit.isBlocking()) {
                    disableShield(entityHit, ticks);
                }
                launch(entityHit);
            }
        }
    }

    private void BlockBreaking(int x, int y, int z) {
        int MthX = Mth.floor(this.getX());
        int MthY = Mth.floor(this.getY());
        int MthZ = Mth.floor(this.getZ());
        boolean flag = false;
        for (int k2 = -x; k2 <= x; ++k2) {
            for (int l2 = -z; l2 <= z; ++l2) {
                for (int j = -y; j <= -1; ++j) {
                    int i3 = MthX + k2;
                    int k = MthY + j;
                    int l = MthZ + l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    BlockState block = this.level.getBlockState(blockpos);
                    if (block.getMaterial() != Material.AIR && block.is(ModTag.ENDER_GUARDIAN_CAN_DESTROY)) {
                        if (block.canEntityDestroy(this.level, blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, block)) {
                            flag = this.level.destroyBlock(blockpos, false, this) || flag;
                        }
                    }
                }
            }
        }
    }


    private void Burstparticle() {
        if (this.level.isClientSide) {
            double d0 = this.getX();
            double d1 = this.getY() + 2.0;
            double d2 = this.getZ();
            int size = (int) 5f;
            for (int i = -size; i <= size; ++i) {
                for (int j = -size; j <= size; ++j) {
                    for (int k = -size; k <= size; ++k) {
                        double d3 = (double) j + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                        double d4 = (double) i + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                        double d5 = (double) k + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                        double d6 = (double) Mth.sqrt((float) (d3 * d3 + d4 * d4 + d5 * d5)) / 0.5 + this.random.nextGaussian() * 0.05D;
                        this.level.addParticle(ParticleTypes.REVERSE_PORTAL, d0, d1, d2, d3 / d6, d4 / d6, d5 / d6);
                        if (i != -size && i != size && j != -size && j != size) {
                            k += size * 2 - 1;
                        }
                    }
                }
            }
        }
    }

    private void launch(Entity entityHit) {
        double d0 = entityHit.getX() - this.getX();
        double d1 = entityHit.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        entityHit.push(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
    }

    private void GravityPull() {
        List<LivingEntity> entities = getEntityLivingBaseNearby(12, 12, 12, 12);
        for (LivingEntity inRange : entities) {
            if (inRange instanceof Player && ((Player) inRange).getAbilities().invulnerable) continue;
            if (isAlliedTo(inRange)) continue;
            float angle = (0.01745329251F * this.yBodyRot);
            double extraX = Mth.sin((float) (Math.PI + angle));
            double extraZ = Mth.cos(angle);
            double theta = (yBodyRot) * (Math.PI / 180);
            theta += Math.PI / 2;
            double vecX = Math.cos(theta);
            double vecZ = Math.sin(theta);
            Vec3 diff = inRange.position().subtract(position().add(2.0 * vecX + extraX * 0.25, 0, 2.0 * vecZ + extraZ * 0.25));
            diff = diff.normalize().scale(0.085);
            inRange.setDeltaMovement(inRange.getDeltaMovement().subtract(diff));

        }
    }

    private void GravityPullparticle() {
        if (this.level.isClientSide) {
            for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                float angle = (0.01745329251F * this.yBodyRot) + i1;
                double extraX = Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = Mth.cos(angle);
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                this.level.addParticle(ParticleTypes.PORTAL, getX() + 2.2 * vecX + extraX * 0.75, this.getY() + extraY, getZ() + 2.2 * vecZ + extraZ * 0.75, (this.random.nextDouble() - 0.5D) * 12.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 12.0D);
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
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) ;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) ;
                double extraX = 1.2 * Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = 1.2 * Mth.cos(angle);
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

    private void StompAttack() {
        this.playSound(ModSounds.ENDER_GUARDIAN_FIST.get(), 0.3f, 1F + this.getRandom().nextFloat() * 0.1F);
        LivingEntity target = this.getTarget();
        if (target != null) {
            double d0 = Math.min(target.getY(), this.getY());
            double d1 = Math.max(target.getY(), this.getY()) + 1.0D;
            Vec3 looking = this.getLookAngle();
            Vec3[] all = new Vec3[]{looking, looking.yRot(0.40f), looking.yRot(-0.40f)};
            Vec3[] all2 = new Vec3[]{looking.yRot(0.10f), looking.yRot(-0.10f)};
            float f = (float) Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());

            for (int k = 0; k < 6; ++k) {
                float f2 = f + (float) k * (float) Math.PI * 2.0F / 6.0F + ((float) Math.PI * 2F / 5F);
                this.spawnFangs(this.getX() + (double) Mth.cos(f2) * 2.5D, this.getZ() + (double) Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
            }
            for (int k = 0; k < 11; ++k) {
                float f3 = f + (float) k * (float) Math.PI * 2.0F / 11.0F + ((float) Math.PI * 2F / 10F);
                this.spawnFangs(this.getX() + (double) Mth.cos(f3) * 3.5D, this.getZ() + (double) Mth.sin(f3) * 3.5D, d0, d1, f3, 10);
            }
            for (int k = 0; k < 14; ++k) {
                float f4 = f + (float) k * (float) Math.PI * 2.0F / 14.0F + ((float) Math.PI * 2F / 20F);
                this.spawnFangs(this.getX() + (double) Mth.cos(f4) * 4.5D, this.getZ() + (double) Mth.sin(f4) * 4.5D, d0, d1, f4, 15);
            }
            switch (random.nextInt(3)) {
                case 0:
                    for (Vec3 Vec3 : all) {
                        float f0 = (float) Mth.atan2(Vec3.z, Vec3.x);
                        for (int l = 0; l < 13; ++l) {
                            double d2 = 1.25D * (double) (l + 1);
                            int j = (int) (0.75f * l);
                            this.spawnFangs(this.getX() + (double) Mth.cos(f0) * d2, this.getZ() + (double) Mth.sin(f0) * d2, d0, d1, f0, j);
                        }
                    }
                    break;
                case 1:
                    for (Vec3 Vec3 : all2) {
                        float f0 = (float) Mth.atan2(Vec3.z, Vec3.x);
                        for (int l = 0; l < 13; ++l) {
                            double d2 = 1.25D * (double) (l + 1);
                            int j = (int) (0.25f * l);
                            this.spawnFangs(this.getX() + (double) Mth.cos(f0) * d2, this.getZ() + (double) Mth.sin(f0) * d2, d0, d1, f0, j);
                        }
                    }
                    break;
                case 2:
                    for (int l = 0; l < 13; ++l) {
                        double d2 = 1.25D * (double) (l + 1);
                        int j = (int) (0.5f * l);
                        this.spawnFangs(this.getX() + (double) Mth.cos(f) * d2, this.getZ() + (double) Mth.sin(f) * d2, d0, d1, f, j);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void RageAttack() {
        LivingEntity target = this.getTarget();
        if (target != null) {
            double d0 = Math.min(target.getY(), this.getY());
            double d1 = Math.max(target.getY(), this.getY()) + 1.0D;
            Vec3 looking = this.getLookAngle();
            Vec3[] all = new Vec3[]{looking, looking.yRot(0.3f), looking.yRot(-0.3f), looking.yRot(0.6f), looking.yRot(-0.6f), looking.yRot(0.9f), looking.yRot(-0.9f)};
            for (Vec3 Vec3 : all) {
                float f0 = (float) Mth.atan2(Vec3.z, Vec3.x);
                for (int l = 0; l < 10; ++l) {
                    double d2 = 1.25D * (double) (l + 1);
                    int j = (int) (0.75f * l);
                    this.spawnFangs(this.getX() + (double) Mth.cos(f0) * d2, this.getZ() + (double) Mth.sin(f0) * d2, d0, d1, f0, j);
                }
            }
        }
    }


    private void spawnFangs(double x, double z, double minY, double maxY, float rotation, int delay) {
        BlockPos blockpos = new BlockPos(x, maxY, z);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = this.level.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(this.level, blockpos1, Direction.UP)) {
                if (!this.level.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = this.level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(this.level, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(minY) - 1);

        if (flag) {
            this.level.addFreshEntity(new Void_Rune_Entity(this.level, x, (double)blockpos.getY() + d0, z, rotation, delay, this));
        }
    }

    private void BrokenHelmet() {
        if (!this.level.isClientSide) {
            double xx = Mth.cos(this.getYRot() % 360.0F / 180.0F * 3.1415927F) * 0.75F;
            double zz = Mth.sin(this.getYRot() % 360.0F / 180.0F * 3.1415927F) * 0.75F;
            this.level.explode(this, this.getX() + xx, this.getY() + (double) this.getEyeHeight(), getZ() + zz, 2.0F, Explosion.BlockInteraction.NONE);
        }
    }

    private void Bulletpattern() {
        LivingEntity target = this.getTarget();
        if (target != null) {
            BlockPos tgt = target.blockPosition();
            double tx = tgt.getX();
            double tz = tgt.getZ();
            double ty = target.getY() + 0.1;
            if (this.getAnimationTick() == 54) {
                if (!target.isOnGround() && !target.isInWater() && !this.level.getBlockState(tgt.below()).getMaterial().blocksMotion())
                    ty -= 1;
                {
                    BlockPos Pos = this.blockPosition();
                    double sx = Pos.getX();
                    double sz = Pos.getZ();
                    Direction dir = Direction.getNearest(tx - sx, 0, tz - sz);
                    double cx = dir.getStepX();
                    double cz = dir.getStepZ();
                    double offsetangle = toRadians(6.0);

                    for (int i = -4; i <= 4; i++) {
                        double angle = (i - (4 / 2)) * offsetangle;
                        double x = cx * cos(angle) + cz * sin(angle);
                        double z = -cx * sin(angle) + cz * cos(angle);
                        Ender_Guardian_Bullet_Entity bullet = new Ender_Guardian_Bullet_Entity(level, this, x, this.getY() + 2, z);
                        bullet.setOwner(this);
                        bullet.setPos(getX(), getY() - 2 + random.nextDouble() * 4, getZ());
                        bullet.setUp(30, cx, 0, cz, tx - 7 * cx + i * cz, ty, tz - 7 * cz + i * cx);
                       this.level.addFreshEntity(bullet);
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public ItemEntity spawnAtLocation(ItemStack stack) {
        ItemEntity itementity = this.spawnAtLocation(stack,0.0f);
        if (itementity != null) {
            itementity.setDeltaMovement(itementity.getDeltaMovement().multiply(0.0, 3.5, 0.0));
            itementity.setGlowingTag(true);
            itementity.setExtendedLifetime();
        }
        return itementity;
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        } else if (super.isAlliedTo(entityIn)) {
            return true;
        } else if (entityIn instanceof Ender_Guardian_Entity || entityIn instanceof Ender_Golem_Entity || entityIn instanceof Shulker || entityIn instanceof Endermaptera_Entity) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        this.playSound(ModSounds.ENDERGUARDIANHURT.get(), 1.0f, 1.0f);
        if (this.getIsHelmetless()) {
            this.playSound(SoundEvents.SHULKER_HURT, 1.0f, 0.8f);
        }
        return null;
    }

    protected SoundEvent getAmbientSound() {
        return this.getIsHelmetless() ? SoundEvents.SHULKER_AMBIENT : null;
    }

    protected SoundEvent getDeathSound() {
        this.playSound(ModSounds.ENDERGUARDIANDEATH.get(), 1.0f, 1.0f);
        if (this.getIsHelmetless()) {
            this.playSound(SoundEvents.SHULKER_DEATH, 2.0f, 0.8f);
        }
        return null;
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new CMPathNavigateGround(this, worldIn);
    }


    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    class PunchAttackGoal extends AnimationGoal<Ender_Guardian_Entity> {

        public PunchAttackGoal(Ender_Guardian_Entity entity) {
            super(entity);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        @Override
        protected boolean test(Animation animation) {
            return animation == GUARDIAN_LEFT_ATTACK
                    || animation == GUARDIAN_RIGHT_ATTACK
                    || animation == GUARDIAN_LEFT_STRONG_ATTACK
                    || animation == GUARDIAN_RIGHT_STRONG_ATTACK;
        }

        public void tick() {
            Ender_Guardian_Entity.this.setDeltaMovement(0, Ender_Guardian_Entity.this.getDeltaMovement().y, 0);
            LivingEntity target = Ender_Guardian_Entity.this.getTarget();
            if (Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_LEFT_ATTACK) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 17 && target != null || Ender_Guardian_Entity.this.getAnimationTick() > 27 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                   // Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
            if (Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_RIGHT_ATTACK) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 22 && target != null || Ender_Guardian_Entity.this.getAnimationTick() > 32 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                  //  Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
            if (Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_LEFT_STRONG_ATTACK) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 34 && target != null || Ender_Guardian_Entity.this.getAnimationTick() > 44 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                 //   Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
            if (Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_RIGHT_STRONG_ATTACK) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 29 && target != null || Ender_Guardian_Entity.this.getAnimationTick() > 39 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                  //  Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
        }
    }

    class StompAttackGoal extends AnimationGoal<Ender_Guardian_Entity> {

        public StompAttackGoal(Ender_Guardian_Entity entity) {
            super(entity);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        @Override
        protected boolean test(Animation animation) {
            return animation == GUARDIAN_STOMP
                    || animation == GUARDIAN_RAGE_STOMP;
        }

        public void tick() {
            Ender_Guardian_Entity.this.setDeltaMovement(0, Ender_Guardian_Entity.this.getDeltaMovement().y, 0);
            LivingEntity target = Ender_Guardian_Entity.this.getTarget();
            if(Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_STOMP) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 32 && target != null || Ender_Guardian_Entity.this.getAnimationTick() > 42 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                   // Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
            if(Ender_Guardian_Entity.this.getAnimation() == GUARDIAN_RAGE_STOMP) {
                if (Ender_Guardian_Entity.this.getAnimationTick() < 32 && target != null
                        || Ender_Guardian_Entity.this.getAnimationTick() > 42 && Ender_Guardian_Entity.this.getAnimationTick() < 53 && target != null
                        || Ender_Guardian_Entity.this.getAnimationTick() > 58 && target != null) {
                    Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                   // Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
                }
            }
        }
    }


    class UppercutAndBulletGoal extends SimpleAnimationGoal<Ender_Guardian_Entity> {

        public UppercutAndBulletGoal(Ender_Guardian_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ender_Guardian_Entity.this.getTarget();
            if (Ender_Guardian_Entity.this.getAnimationTick() < 29 && target != null
                    || Ender_Guardian_Entity.this.getAnimationTick() > 54 && target !=null) {
                Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
              //  Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
            }
            if (Ender_Guardian_Entity.this.getAnimationTick() == 26) {
                float f1 = (float) Math.cos(Math.toRadians(Ender_Guardian_Entity.this.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(Ender_Guardian_Entity.this.getYRot() + 90));
                Ender_Guardian_Entity.this.push(f1 * 2.0, 0, f2 * 2.0);
            }
            if(Ender_Guardian_Entity.this.getAnimationTick() > 32 || Ender_Guardian_Entity.this.getAnimationTick() < 26){
                Ender_Guardian_Entity.this.setDeltaMovement(0, Ender_Guardian_Entity.this.getDeltaMovement().y, 0);
            }

            Bulletpattern();

        }
    }

    class RageUppercut extends SimpleAnimationGoal<Ender_Guardian_Entity> {

        public RageUppercut(Ender_Guardian_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ender_Guardian_Entity.this.getTarget();
            if (Ender_Guardian_Entity.this.getAnimationTick() < 29 && target != null
                    || Ender_Guardian_Entity.this.getAnimationTick() > 54 && Ender_Guardian_Entity.this.getAnimationTick() < 84 && target != null
                    ||Ender_Guardian_Entity.this.getAnimationTick() > 104 && target !=null) {
                Ender_Guardian_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ender_Guardian_Entity.this.setYRot(Ender_Guardian_Entity.this.yRotO);
                //  Ender_Guardian_Entity.this.yBodyRot = Ender_Guardian_Entity.this.yBodyRotO;
            }
            if (Ender_Guardian_Entity.this.getAnimationTick() == 26) {
                float f1 = (float) Math.cos(Math.toRadians(Ender_Guardian_Entity.this.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(Ender_Guardian_Entity.this.getYRot() + 90));
                Ender_Guardian_Entity.this.push(f1 * 2.0, 0, f2 * 2.0);
            }
            if(Ender_Guardian_Entity.this.getAnimationTick() > 32 || Ender_Guardian_Entity.this.getAnimationTick() < 26){
                Ender_Guardian_Entity.this.setDeltaMovement(0, Ender_Guardian_Entity.this.getDeltaMovement().y, 0);
            }

            Bulletpattern();

        }
    }



    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 67) {
            cataclysm.PROXY.onEntityStatus(this, id);
        } else {
            super.handleEntityEvent(id);
        }
    }
}





