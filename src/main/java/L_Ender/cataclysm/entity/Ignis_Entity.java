package L_Ender.cataclysm.entity;

import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.AI.*;
import L_Ender.cataclysm.entity.effect.Cm_Falling_Block_Entity;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import L_Ender.cataclysm.init.ModEffect;
import L_Ender.cataclysm.init.ModParticle;
import L_Ender.cataclysm.init.ModSounds;
import L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class Ignis_Entity extends Boss_monster {
    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    public static final Animation SWING_ATTACK = Animation.create(65);
    public static final Animation SWING_ATTACK_SOUL = Animation.create(56);
    public static final Animation HORIZONTAL_SWING_ATTACK = Animation.create(68);
    public static final Animation HORIZONTAL_SWING_ATTACK_SOUL = Animation.create(58);
    public static final Animation SHIELD_SMASH_ATTACK = Animation.create(70);
    public static final Animation PHASE_2 = Animation.create(68);
    public static final Animation POKE_ATTACK = Animation.create(65);
    public static final Animation POKE_ATTACK2 = Animation.create(56);
    public static final Animation POKE_ATTACK3 = Animation.create(50);
    public static final Animation POKED_ATTACK = Animation.create(65);
    public static final Animation PHASE_3 = Animation.create(120);
    public static final Animation MAGIC_ATTACK = Animation.create(95);
    public static final Animation SMASH_IN_AIR = Animation.create(105);
    public static final Animation SMASH = Animation.create(47);
    public static final Animation BODY_CHECK_ATTACK1 = Animation.create(62);
    public static final Animation BODY_CHECK_ATTACK2 = Animation.create(62);
    public static final Animation BODY_CHECK_ATTACK3 = Animation.create(62);
    public static final Animation BODY_CHECK_ATTACK4 = Animation.create(62);
    public static final Animation BODY_CHECK_ATTACK_SOUL1 = Animation.create(45);
    public static final Animation BODY_CHECK_ATTACK_SOUL2 = Animation.create(45);
    public static final Animation BODY_CHECK_ATTACK_SOUL3 = Animation.create(45);
    public static final Animation BODY_CHECK_ATTACK_SOUL4 = Animation.create(45);
    public static final Animation IGNIS_DEATH = Animation.create(124);
    public static final Animation BURNS_THE_EARTH = Animation.create(67);
    public static final Animation COUNTER = Animation.create(115);
    public static final Animation STRIKE = Animation.create(62);
    public static final Animation TRIPLE_ATTACK = Animation.create(139);
    public static final Animation FOUR_COMBO = Animation.create(141);
    public static final Animation BREAK_THE_SHIELD = Animation.create(87);
    public static final Animation SWING_UPPERCUT = Animation.create(65);
    public static final Animation SWING_UPPERSLASH = Animation.create(54);
    public static final Animation SPIN_ATTACK = Animation.create(56);
    public static final int AIR_SMASH_COOLDOWN = 160;
    public static final int BODY_CHECK_COOLDOWN = 200;
    public static final int POKE_COOLDOWN = 200;
    public static final int CONTER_STRIKE_COOLDOWN = 360;
    private static final EntityDataAccessor<Boolean> IS_BLOCKING = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SHIELD_BREAK = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> SHIELD_DURABILITY = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_SHIELD = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOW_SHIELD = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SWORD = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> BOSS_PHASE = SynchedEntityData.defineId(Ignis_Entity.class, EntityDataSerializers.INT);
    private Vec3 prevBladePos = new Vec3(0, 0, 0);

    private int air_smash_cooldown = 0;
    private int body_check_cooldown = 0;
    private int poke_cooldown = 0;
    private int counter_strike_cooldown = 0;

    private int timeWithoutTarget;
    public float blockingProgress;
    public float swordProgress;
    public float prevblockingProgress;
    public float prevswordProgress;

    public Ignis_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 500;
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.maxUpStep = 2.5F;
        if (world.isClientSide)
            socketPosArray = new Vec3[] {new Vec3(0, 0, 0)};
        setConfigattribute(this, CMConfig.IgnisHealthMultiplier, CMConfig.IgnisDamageMultiplier);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,
                SWING_ATTACK,
                SWING_ATTACK_SOUL,
                SWING_UPPERCUT,
                SWING_UPPERSLASH,
                SPIN_ATTACK,
                HORIZONTAL_SWING_ATTACK,
                HORIZONTAL_SWING_ATTACK_SOUL,
                POKE_ATTACK,
                POKE_ATTACK2,
                POKE_ATTACK3,
                POKED_ATTACK,
                MAGIC_ATTACK,
                PHASE_3,
                SHIELD_SMASH_ATTACK,
                PHASE_2,
                BODY_CHECK_ATTACK4,
                BODY_CHECK_ATTACK3,
                BODY_CHECK_ATTACK2,
                BODY_CHECK_ATTACK1,
                BODY_CHECK_ATTACK_SOUL1,
                BODY_CHECK_ATTACK_SOUL2,
                BODY_CHECK_ATTACK_SOUL3,
                BODY_CHECK_ATTACK_SOUL4,
                SMASH,
                COUNTER,
                STRIKE,
                SMASH_IN_AIR,
                BURNS_THE_EARTH,
                TRIPLE_ATTACK,
                BREAK_THE_SHIELD,
                FOUR_COMBO,
                IGNIS_DEATH};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new CmAttackGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new Hornzontal_SwingGoal(this,HORIZONTAL_SWING_ATTACK,31,51,20,36));
        this.goalSelector.addGoal(1, new Hornzontal_SwingGoal(this,HORIZONTAL_SWING_ATTACK_SOUL,27,47,16,31));
        this.goalSelector.addGoal(1, new PokeGoal(this,POKE_ATTACK, 39,59,34,42, 34,40));
        this.goalSelector.addGoal(1, new PokeGoal(this,POKE_ATTACK2, 33,53,28,36, 28,34));
        this.goalSelector.addGoal(1, new PokeGoal(this,POKE_ATTACK3, 29,49,24,33, 24,30));
        this.goalSelector.addGoal(1, new AttackAnimationGoal2<>(this, PHASE_2, 34, 54));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this,PHASE_3,34,true));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this,SWING_UPPERSLASH,23,true));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this,BREAK_THE_SHIELD,35,false));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal2<>(this,SWING_UPPERCUT,34,50,27,0.3f,0.3f));
        this.goalSelector.addGoal(1, new Shield_Smash(this,SHIELD_SMASH_ATTACK));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK1,25,20,0.25f,0.25f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK2,25,20,0.25f,0.25f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK3,25,20,0.25f,0.25f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK4,25,20,0.25f,0.25f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK_SOUL1,21,16,0.4f,0.4f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK_SOUL2,21,16,0.4f,0.4f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK_SOUL3,21,16,0.4f,0.4f));
        this.goalSelector.addGoal(1, new ChargeAttackAnimationGoal1<>(this, BODY_CHECK_ATTACK_SOUL4,21,16,0.4f,0.4f));
        this.goalSelector.addGoal(1, new Poked(this, POKED_ATTACK));
        this.goalSelector.addGoal(1, new Air_Smash(this,SMASH_IN_AIR));
        this.goalSelector.addGoal(1, new SimpleAnimationGoal<>(this, SMASH));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this,SPIN_ATTACK,10,true));
        this.goalSelector.addGoal(1, new Swing_Attack_Goal(this, SWING_ATTACK, 34,40));
        this.goalSelector.addGoal(1, new Swing_Attack_Goal(this, SWING_ATTACK_SOUL, 28,34));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this, COUNTER, 105,true));
        this.goalSelector.addGoal(1, new AttackAnimationGoal1<>(this, STRIKE, 34,true));
        this.goalSelector.addGoal(1, new Triple_Attack(this, TRIPLE_ATTACK));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

    }


    @Override
    public boolean hurt(DamageSource source, float damage) {
        Entity entity = source.getDirectEntity();
        LivingEntity target = this.getTarget();
        double range = calculateRange(source);
        if(entity != null && !isNoAi() && (blockingProgress == 10 || swordProgress == 10)) {
            if(target !=null && target.isAlive()) {
                if (this.getAnimation() == NO_ANIMATION) {
                    if (this.getRandom().nextFloat() * 100.0F < 20f && counter_strike_cooldown <= 0 && range < 225) {
                        counter_strike_cooldown = CONTER_STRIKE_COOLDOWN;
                        this.setAnimation(COUNTER);
                    }
                }
            }
            if(this.getAnimation() == COUNTER) {
                if (this.getAnimationTick() > 20 && this.getAnimationTick() <= 100) {
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, STRIKE);
                    this.playSound(SoundEvents.BLAZE_HURT, 0.5f, 0.4F + this.getRandom().nextFloat() * 0.1F);
                    return false;
                }
            }
        }
        if (range > CMConfig.IgnisLongRangelimit * CMConfig.IgnisLongRangelimit) {
            return false;
        }

        if (!source.isBypassInvul()) {
            damage = Math.min(CMConfig.IgnisDamageCap, damage);
        }
        if((this.getBossPhase() == 1 && this.getHealth() <= this.getMaxHealth() * 1/3 || this.getBossPhase() == 0 && this.getHealth() <= this.getMaxHealth() * 2/3) && !source.isBypassInvul()){
            damage *= 0.5;
        }

        if ((this.getAnimation() == PHASE_3 || this.getAnimation() == PHASE_2 || this.getAnimation() == STRIKE) && !source.isBypassInvul()) {
            return false;
        }
        if (damage > 0.0F && this.canBlockDamageSource(source)) {
            this.hurtCurrentlyUsedShield(damage);
            if (!source.isProjectile()) {
                if (entity instanceof LivingEntity) {
                    this.blockUsingShield((LivingEntity) entity);
                }
            }
            if(source.getDirectEntity() instanceof ThrownTrident) {
                if(source.getEntity() instanceof Player) {
                    this.playSound(ModSounds.IGNIS_SHIELD_BREAK.get(), 1.0f, 0.8F);
                    if (!level.isClientSide) {
                        if (this.getShieldDurability() < 3) {
                            this.setShieldDurability(this.getShieldDurability() + 1);
                        }
                    }
                }
            }
            this.playSound(SoundEvents.BLAZE_HURT, 0.5f, 0.4F + this.getRandom().nextFloat() * 0.1F);
            return false;
        }

        Ignis_Entity.Crackiness ignis$crackiness = this.getCrackiness();
        if (this.getBossPhase() > 0 && super.hurt(source, damage) && this.getCrackiness() != ignis$crackiness) {
            this.playSound(ModSounds.IGNIS_ARMOR_BREAK.get(), 1.0F, 0.8F);
        }

        return super.hurt(source, damage);
    }

    private boolean canBlockDamageSource(DamageSource damageSourceIn) {
        Entity entity = damageSourceIn.getDirectEntity();
        boolean flag = false;
        if (entity instanceof AbstractArrow) {
            AbstractArrow abstractarrowentity = (AbstractArrow)entity;
            if (abstractarrowentity.getPierceLevel() > 0) {
                flag = true;
            }
        }

        if (!damageSourceIn.isBypassArmor() && !flag && this.getIsShield()) {
            Vec3 vector3d2 = damageSourceIn.getSourcePosition();
            if (vector3d2 != null) {
                Vec3 vector3d = this.getViewVector(1.0F);
                Vec3 vector3d1 = vector3d2.vectorTo(this.position()).normalize();
                vector3d1 = new Vec3(vector3d1.x, 0.0D, vector3d1.z);
                return vector3d1.dot(vector3d) < 0.0D;
            }
        }
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_BLOCKING, false);
        this.entityData.define(IS_SHIELD, false);
        this.entityData.define(IS_SHIELD_BREAK, false);
        this.entityData.define(SHIELD_DURABILITY, 0);
        this.entityData.define(IS_SWORD, false);
        this.entityData.define(SHOW_SHIELD, true);
        this.entityData.define(BOSS_PHASE, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("BossPhase", this.getBossPhase());
        compound.putBoolean("Is_Shield_Break", this.getIsShieldBreak());
        compound.putInt("Shield_Durability", this.getShieldDurability());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setBossPhase(compound.getInt("BossPhase"));
        this.setIsShieldBreak(compound.getBoolean("Is_Shield_Break"));
        this.setShieldDurability(compound.getInt("Shield_Durability"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    public void setIsBlocking(boolean isBlocking) {
         this.entityData.set(IS_BLOCKING, isBlocking);
    }

    public boolean getIsBlocking() {
        return this.entityData.get(IS_BLOCKING);
    }

    public void setIsShield(boolean isShield) {
        this.entityData.set(IS_SHIELD, isShield);
    }

    public boolean getIsShield() {
        return this.entityData.get(IS_SHIELD);
    }

    public void setIsSword(boolean isSword) {
        this.entityData.set(IS_SWORD, isSword);
    }

    public boolean getIsSword() {
        return this.entityData.get(IS_SWORD);
    }

    public void setIsShieldBreak(boolean isShieldBreak) {
        this.entityData.set(IS_SHIELD_BREAK, isShieldBreak);
    }

    public boolean getIsShieldBreak() {
        return this.entityData.get(IS_SHIELD_BREAK);
    }

    public void setShieldDurability(int ShieldDurability) {
        this.entityData.set(SHIELD_DURABILITY, ShieldDurability);
    }

    public int getShieldDurability() {
        return this.entityData.get(SHIELD_DURABILITY);
    }

    public void setShowShield(boolean showShield) {
        this.entityData.set(SHOW_SHIELD, showShield);
    }

    public boolean getShowShield() {
        return this.entityData.get(SHOW_SHIELD);
    }

    public void setBossPhase(int bossPhase) {
        this.entityData.set(BOSS_PHASE, bossPhase);
    }

    public int getBossPhase() {
        return this.entityData.get(BOSS_PHASE);
    }

    public Ignis_Entity.Crackiness getCrackiness() {
        return Ignis_Entity.Crackiness.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public static AttributeSupplier.Builder ignis() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33F)
                .add(Attributes.ATTACK_DAMAGE, 14)
                .add(Attributes.MAX_HEALTH, 450)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    protected int decreaseAirSupply(int air) {
        return air;
    }

    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.IGNIS_AMBIENT.get();
    }

    private static Animation getRandomPoke(RandomSource rand) {
        switch (rand.nextInt(3)) {
            case 0:
                return POKE_ATTACK;
            case 1:
                return POKE_ATTACK2;
            case 2:
                return POKE_ATTACK3;
        }
        return POKE_ATTACK;
    }

    public void tick() {
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
        prevblockingProgress = blockingProgress;
        prevswordProgress = swordProgress;
        if (this.getIsBlocking() && blockingProgress < 10F) {
            blockingProgress++;
        }
        if (!this.getIsBlocking() && blockingProgress > 0F) {
            blockingProgress--;
        }
        if (this.getIsSword() && swordProgress < 10F) {
            swordProgress++;
        }
        if (!this.getIsSword() && swordProgress > 0F) {
            swordProgress--;
        }

        if (!this.getPassengers().isEmpty() && this.getPassengers().get(0).isShiftKeyDown()) {
            this.getPassengers().get(0).setShiftKeyDown(false);
        }

        LivingEntity target = this.getTarget();
        SwingParticles();
        if (this.level.isClientSide) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.level.playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }
            if(this.getBossPhase() > 1){
                int i = this.getCrackiness() == Crackiness.NONE ? 5 : this.getCrackiness() == Crackiness.LOW ? 4 : this.getCrackiness() == Crackiness.MEDIUM ? 3 : 2;
                if (random.nextInt(i) == 0) {
                    this.level.addParticle(ModParticle.SOUL_LAVA.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }
            }else{
                for(int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }
            }

        }else{
            timeWithoutTarget++;
            if (target != null) {
                timeWithoutTarget = 0;
                if(this.getIsShieldBreak()) {
                    if (!this.getIsSword()) {
                        this.setIsSword(true);
                    }
                    if (this.getIsBlocking()) {
                        this.setIsBlocking(false);
                    }
                }else{
                    if (!this.getIsBlocking()) {
                        this.setIsBlocking(true);
                    }
                    if (this.getIsSword()) {
                        this.setIsSword(false);
                    }
                }
            }

            if (this.getAnimation() == NO_ANIMATION && timeWithoutTarget > 150 && (this.getIsBlocking() || this.getIsSword()) && target == null) {
                timeWithoutTarget = 0;
                this.setIsSword(false);
                this.setIsBlocking(false);
            }

            if(this.getIsShieldBreak()) {
                if (this.getIsBlocking()) {
                    this.setIsBlocking(false);
                    this.setIsSword(true);
                }
                this.setShieldDurability(3);
                this.setShowShield(false);
            }

            if (this.getBossPhase() > 0){
                bossInfo.setColor(BossEvent.BossBarColor.BLUE);
            }
            if (this.getBossPhase() > 1){
                bossInfo.setDarkenScreen(true);
                if (this.getAnimation() != PHASE_3) {
                    this.setIsShieldBreak(true);
                }
            }

            if(this.getIsBlocking() && blockingProgress == 10){
                if(this.getAnimation() == NO_ANIMATION) {
                    setIsShield(true);
                }
                if(this.getAnimation() == COUNTER) {
                    setIsShield(true);
                }
                if(this.getAnimation() == STRIKE) {
                    setIsShield(false);
                }
                else if(this.getAnimation() == POKED_ATTACK) {
                    setIsShield(false);
                }
                else if(this.getAnimation() == BREAK_THE_SHIELD) {
                    setIsShield(false);
                }
                else if (this.getAnimation() == HORIZONTAL_SWING_ATTACK) {
                    setIsShield(this.getAnimationTick() > 31);
                }
                else if (this.getAnimation() == HORIZONTAL_SWING_ATTACK_SOUL) {
                    setIsShield(this.getAnimationTick() > 27);
                }
                else if (this.getAnimation() == BODY_CHECK_ATTACK1 || this.getAnimation() == BODY_CHECK_ATTACK2 ||
                        this.getAnimation() == BODY_CHECK_ATTACK3 || this.getAnimation() == BODY_CHECK_ATTACK4) {
                    setIsShield(this.getAnimationTick() < 25);
                }
                else if (this.getAnimation() == BODY_CHECK_ATTACK_SOUL1 || this.getAnimation() == BODY_CHECK_ATTACK_SOUL2 ||
                        this.getAnimation() == BODY_CHECK_ATTACK_SOUL3 || this.getAnimation() == BODY_CHECK_ATTACK_SOUL4) {
                    setIsShield(this.getAnimationTick() < 21);
                }
                else if(this.getAnimation() == POKE_ATTACK) {
                    setIsShield(this.getAnimationTick() < 39);
                }
                else if(this.getAnimation() == POKE_ATTACK2) {
                    setIsShield(this.getAnimationTick() < 34);
                }
                else if(this.getAnimation() == POKE_ATTACK3) {
                    setIsShield(this.getAnimationTick() < 29);
                }
                else if (this.getAnimation() == SWING_ATTACK) {
                    setIsShield(this.getAnimationTick() < 34);
                }
                else if (this.getAnimation() == SWING_ATTACK_SOUL) {
                    setIsShield(this.getAnimationTick() < 28);
                }
                else if (this.getAnimation() == SWING_UPPERSLASH) {
                    setIsShield(this.getAnimationTick() > 27);
                }
            }else{
                setIsShield(false);
            }
        }
        if (body_check_cooldown > 0) body_check_cooldown--;
        if (air_smash_cooldown > 0) air_smash_cooldown--;
        if (counter_strike_cooldown > 0) counter_strike_cooldown--;
        if (poke_cooldown > 0) poke_cooldown--;
        repelEntities(1.4F, 4, 1.4F, 1.4F);

        setYRot(yBodyRot);
        Animation animation = getRandomPoke(random);
        if (this.isAlive()) {
            if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.getShieldDurability() > 2 && !this.getIsShieldBreak()) {
                this.setAnimation(BREAK_THE_SHIELD);
            }else if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.getHealth() <= this.getMaxHealth() * 2/3 && this.getBossPhase() < 1) {
                this.setAnimation(PHASE_2);
            }else if (!isNoAi() && this.getAnimation() == NO_ANIMATION && this.getHealth() <= this.getMaxHealth() * 1/3 && this.getBossPhase() < 2) {
                this.setAnimation(PHASE_3);
            } else if (target != null && target.isAlive() && (blockingProgress == 10 || swordProgress == 10) && !isNoAi()) {
                if (this.getAnimation() == NO_ANIMATION && this.distanceToSqr(target) >= 225 && this.distanceToSqr(target) <= 1024.0D && target.isOnGround() && !this.getIsShieldBreak() && air_smash_cooldown <= 0) {
                    air_smash_cooldown = AIR_SMASH_COOLDOWN;
                    this.setAnimation(SMASH_IN_AIR);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 9F && this.getRandom().nextFloat() * 100.0F < 0.9f) {
                    this.setAnimation(animation);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 9F && this.getRandom().nextFloat() * 100.0F < 15F && poke_cooldown <= 0 && target.hasEffect(ModEffect.EFFECTSTUN.get())) {
                    poke_cooldown = POKE_COOLDOWN;
                    this.setAnimation(animation);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 6.5F && this.getRandom().nextFloat() * 100.0F < 6f) {
                    Animation animation2 = this.getBossPhase() > 0 ? HORIZONTAL_SWING_ATTACK_SOUL : HORIZONTAL_SWING_ATTACK;
                    this.setAnimation(animation2);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 4.75F && this.getRandom().nextFloat() * 100.0F < 12f) {
                    Animation animation3 = this.getBossPhase() > 0 ? SWING_ATTACK_SOUL : SWING_ATTACK;
                    this.setAnimation(animation3);
                //} else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 5F && this.getRandom().nextFloat() * 100.0F < 6f && this.getIsShieldBreak()) {
                //    if (this.getBossPhase() < 2) {
                //        this.setAnimation(TRIPLE_ATTACK);
                //    }else{
                        //this.setAnimation(FOUR_COMBO);
                //    }
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 3F && this.getRandom().nextFloat() * 100.0F < 20f && !this.getIsShieldBreak()) {
                    this.setAnimation(SHIELD_SMASH_ATTACK);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 5F && this.getRandom().nextFloat() * 100.0F < 0.7f && counter_strike_cooldown <= 0) {
                    counter_strike_cooldown = CONTER_STRIKE_COOLDOWN;
                    this.setAnimation(COUNTER);
                } else if (this.getAnimation() == NO_ANIMATION && this.distanceTo(target) < 3F && this.getRandom().nextFloat() * 100.0F < 10f && body_check_cooldown <= 0) {
                    body_check_cooldown = BODY_CHECK_COOLDOWN;
                    Animation animation3 = this.getBossPhase() > 0 ? BODY_CHECK_ATTACK_SOUL1 : BODY_CHECK_ATTACK1;
                    this.setAnimation(animation3);
                }
            }
        }
        super.tick();
        AnimationHandler.INSTANCE.updateAnimations(this);
    }
    public void aiStep() {
        super.aiStep();
        if (this.getAnimation() == SWING_ATTACK) {
            if (this.getAnimationTick() == 34) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(6.5f,6,70,1.1f,0.05f,80,2,150,0);
            }
        }
        if (this.getAnimation() == HORIZONTAL_SWING_ATTACK) {
            if (this.getAnimationTick() == 31) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(5.25f,6,210,1.0f,0.06f,80,3 ,150,0);
            }
        }
        if (this.getAnimation() == SWING_ATTACK_SOUL) {
            if (this.getAnimationTick() == 28) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(6.5f,6,70,1.1f,0.05f,80,2,150,0);
            }
        }
        if (this.getAnimation() == HORIZONTAL_SWING_ATTACK_SOUL) {
            if (this.getAnimationTick() == 27) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(5.25f,6,210,1.0f,0.06f,80,3 ,150,0);
            }
        }
        if (this.getAnimation() == BREAK_THE_SHIELD) {
            if (this.getAnimationTick() == 25){
                this.setShowShield(false);
                ShieldExplode(-2.75f,1.5f,2);
            }
            if (this.getAnimationTick() == 79){
                this.setIsShieldBreak(true);
            }
            if (this.getAnimationTick() == 55){
                ScreenShake_Entity.ScreenShake(level, this.position(), 30, 0.15f, 0, 50);
                List<LivingEntity> entities = getEntityLivingBaseNearby(12, 12, 12, 12);
                this.playSound(ModSounds.FLAME_BURST.get(), 1.0f, 0.8F);
                for (LivingEntity inRange : entities) {
                    if (inRange instanceof Player && ((Player) inRange).getAbilities().invulnerable) continue;
                    if (isAlliedTo(inRange)) continue;
                    inRange.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 60));
                }
            }
        }
        if (this.getAnimation() == PHASE_2) {
            if (this.getAnimationTick() == 29){
                this.playSound(ModSounds.FLAME_BURST.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
            }
            if (this.getAnimationTick() > 29 && this.getAnimationTick() < 39){
                Sphereparticle(2, 0,5);
                Phase_Transition(14,0.4f,0.03f,5,150);
            }
            if (this.getAnimationTick() == 34) {
                setBossPhase(1);
            }
        }
        if (this.getAnimation() == PHASE_3) {
            if (this.getAnimationTick() == 58){
                this.setBossPhase(2);
                this.setShowShield(false);
                if(!this.getIsShieldBreak()) {
                    ShieldExplode(2,0.575f,2);
                }
                this.playSound(ModSounds.FLAME_BURST.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                this.playSound(ModSounds.SWORD_STOMP.get(), 1.0f, 0.75F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 30, 0.15f, 0, 10);
                ShieldSmashparticle(0.5f,1.0f,-0.15f);
            }
            if (this.getAnimationTick() > 58 && this.getAnimationTick() < 68){
                Sphereparticle(0.5f, 1.0f, 6);
                Phase_Transition(27, 0.6f, 0.05f, 5, 150);
            }
        }

        if (this.getAnimation() == SHIELD_SMASH_ATTACK) {
            if (this.getAnimationTick() == 34){
                this.playSound(SoundEvents.TOTEM_USE, 1.5f, 0.8F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 20, 0.3f, 0, 20);
                AreaAttack(4.85f,2.5f,45,1.5f,0.15f,200,0,0,0);
                ShieldSmashDamage(2,4,1.5f, 2.75f,false,0,1,0.02f);
                ShieldSmashparticle(1.3f, 2.75f,-0.1f);
            }
            if (this.getAnimationTick() == 37) {
                ShieldSmashDamage(2,5,1.5f,2.75f,false,0,1,0.02f);
            }
            if (this.getAnimationTick() == 40) {
                ShieldSmashDamage(2,6,1.5f,2.75f,false,0,1,0.02f);
            }
        }
        if (this.getAnimation() == SMASH) {
            if (this.getAnimationTick() == 5){
                this.playSound(SoundEvents.TOTEM_USE, 1.5f, 0.8F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 20, 0.3f, 0, 20);
                AreaAttack(4.85f,2.5f,45,1.5f,0.15f,200,0,0,0);
                ShieldSmashDamage(2,3,1.5f,1.5f, false,0,1,0.02f);
                ShieldSmashparticle(1.3f,1.5f,0.0f);
            }
            if (this.getAnimationTick() == 8) {
                ShieldSmashDamage(2,4,1.5f,1.5f, false,0,1,0.02f);
            }
            if (this.getAnimationTick() == 11) {
                ShieldSmashDamage(2,5,1.5f,1.5f, false,0,1,0.02f);
            }
            if (this.getAnimationTick() == 14) {
                ShieldSmashDamage(2,6,1.5f,1.5f, false,0,1,0.02f);
            }
        }

        if (this.getAnimation() == STRIKE) {
            if (this.getAnimationTick() == 31) {
                AreaAttack(5.25f,6,120,1.1f,0.1f,100,5 ,150,0);
            }

            if (this.getAnimationTick() == 36) {
                ShieldSmashDamage(0.75f,4,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,5,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(4.5f);
            }
            if (this.getAnimationTick() == 38) {
                ShieldSmashDamage(0.75f,6,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,7,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(6.5f);
            }
            if (this.getAnimationTick() == 40) {
                ShieldSmashDamage(0.75f,8,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,9,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(8.5f);
            }
            if (this.getAnimationTick() == 42) {
                ShieldSmashDamage(0.75f,10,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,11,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(10.5f);
            }
            if (this.getAnimationTick() == 44) {
                ShieldSmashDamage(0.75f,12,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,13,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(12.5f);
            }
            if (this.getAnimationTick() == 46) {
                ShieldSmashDamage(0.75f,14,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,15,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(14.5f);
            }
            if (this.getAnimationTick() == 48) {
                ShieldSmashDamage(0.75f,16,2.5f,0,true,150,1.1f,0.12f);
                ShieldSmashDamage(0.75f,17,2.5f,0,true,150,1.1f,0.12f);
                earthquakesound(16.5f);
            }

            if(this.getAnimationTick() > 31 && this.getAnimationTick() < 35){
                StrikeParticle(0.75f,5,0);
            }
        }

        if (this.getAnimation() == POKE_ATTACK) {
            if (this.getAnimationTick() == 37) {
                this.playSound(ModSounds.IGNIS_POKE.get(), 1.0f, 0.75F + this.getRandom().nextFloat() * 0.1F);
            }
            if (this.getAnimationTick() == 39) {
                Poke(7, 70,60);
            }
        }

        if (this.getAnimation() == POKE_ATTACK2) {
            if (this.getAnimationTick() == 32) {
                this.playSound(ModSounds.IGNIS_POKE.get(), 1.0f, 0.75F + this.getRandom().nextFloat() * 0.1F);
            }
            if (this.getAnimationTick() == 34) {
                Poke(7, 65,50);
            }
        }

        if (this.getAnimation() == POKE_ATTACK3) {
            if (this.getAnimationTick() == 27) {
                this.playSound(ModSounds.IGNIS_POKE.get(), 1.0f, 0.75F + this.getRandom().nextFloat() * 0.1F);
            }
            if (this.getAnimationTick() == 29) {
                Poke(7, 60,40);
            }
        }

        if (this.getAnimation() == BODY_CHECK_ATTACK1
                || this.getAnimation() == BODY_CHECK_ATTACK2
                || this.getAnimation() == BODY_CHECK_ATTACK3
                || this.getAnimation() == BODY_CHECK_ATTACK4) {
            if (this.getAnimationTick() == 25) {
                BodyCheckAttack(3.0f,6,120,0.8f,0.03f,40,80,0.2f);
            }
        }
        if (this.getAnimation() == BODY_CHECK_ATTACK_SOUL1
                || this.getAnimation() == BODY_CHECK_ATTACK_SOUL2
                || this.getAnimation() == BODY_CHECK_ATTACK_SOUL3
                || this.getAnimation() == BODY_CHECK_ATTACK_SOUL4) {
            if (this.getAnimationTick() == 21) {
                BodyCheckAttack(3.0f,6,120,0.9f,0.03f,60,100,0.2f);
            }
        }
        if (this.getAnimation() == TRIPLE_ATTACK) {
            if (this.getAnimationTick() == 30) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(5.5f,6,100,1.0f,0.05f,80,3 ,150,0);
            }
            if (this.getAnimationTick() == 73) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(5.5f,6,45,1.1f,0.06f,120,5 ,150,0);
            }

            if (this.getAnimationTick() == 108) {
                BodyCheckAttack(4.85f,6,60,1.0f,0.01f,40,0,0.2f);
            }
        }

        if (this.getAnimation() == SWING_UPPERCUT) {
            if (this.getAnimationTick() == 32) {
                BodyCheckAttack(3.5f,8,70,1.0f,0.03f,60,70,0.8);
            }
        }
        if (this.getAnimation() == SWING_UPPERSLASH) {
            if (this.getAnimationTick() == 24) {
                this.playSound(ModSounds.STRONGSWING.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                AreaAttack(4.5f, 8, 100, 1.0f, 0.05f, 80, 3, 150, 0.65f);
            }
            if (this.getAnimationTick() == 26) {
                ShieldSmashDamage(0.4f,3,2.5f,0,false,80,1.0f,0.03f);
                earthquakesound(3.5f);
                ShieldSmashDamage(0.4f,4,2.5f,0,false,80,1.0f,0.03f);
            }
            if (this.getAnimationTick() == 28) {
                ShieldSmashDamage(0.4f,5,2.5f,0,false,80,1.0f,0.03f);
                earthquakesound(5.5f);
                ShieldSmashDamage(0.4f,6,2.5f,0,false,80,1.0f,0.03f);
            }
            if (this.getAnimationTick() == 30) {
                ShieldSmashDamage(0.4f,7,2.5f,0,false,80,1.0f,0.03f);
                earthquakesound(6.5f);
                ShieldSmashDamage(0.4f,8,2.5f,0,false,80,1.0f,0.03f);
            }
        }
        if (this.getAnimation() == FOUR_COMBO) {
            if (this.getAnimationTick() == 115) {
                this.playSound(ModSounds.FLAME_BURST.get(), 1.0f, 1F + this.getRandom().nextFloat() * 0.1F);
                this.playSound(ModSounds.SWORD_STOMP.get(), 1.0f, 0.75F + this.getRandom().nextFloat() * 0.1F);
                ScreenShake_Entity.ScreenShake(level, this.position(), 30, 0.15f, 0, 50);
                AreaAttack(4f,4f,45,1.2f,0.1f,200,5,150,0);
                ShieldSmashparticle(0.5f,1.0f,-0.15f);
            }

            if (this.getAnimationTick() > 115 && this.getAnimationTick() < 125){
                Sphereparticle(0.5f,1.0f,6);
                Phase_Transition(56,0.4f,0.03f,5,150);
            }
        }
    }

    @Nullable
    public Animation getDeathAnimation()
    {
        return IGNIS_DEATH;
    }

    private void AreaAttack(float range,float height,float arc ,float damage, float hpdamage ,int shieldbreakticks, int firetime, int brandticks, float airborne) {
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
                if (!(entityHit instanceof Ignis_Entity)) {
                    boolean flag = entityHit.hurt(DamageSource.mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + entityHit.getMaxHealth() * hpdamage));
                    if (entityHit instanceof Player && entityHit.isBlocking() && shieldbreakticks > 0) {
                        disableShield(entityHit, shieldbreakticks);
                    }
                    if (flag) {
                        entityHit.setSecondsOnFire(firetime);
                        if (brandticks > 0){
                            MobEffectInstance effectinstance1 = entityHit.getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                            int i = 1;
                            if (effectinstance1 != null) {
                                i += effectinstance1.getAmplifier();
                                entityHit.removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                            } else {
                                --i;
                            }

                            i = Mth.clamp(i, 0, 4);
                            MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), brandticks, i, false, false, true);
                            entityHit.addEffect(effectinstance);
                            this.heal(5 * (i + 1));
                        }
                        if (airborne > 0){
                            entityHit.setDeltaMovement(entityHit.getDeltaMovement().add(0.0D, airborne, 0.0D));
                        }
                    }
                }
            }
        }
    }

    private void BodyCheckAttack(float range, float height, float arc, float damage, float hpdamage, int shieldbreakticks, int slowticks, double airborne) {
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
                if (!isAlliedTo(entityHit) && !(entityHit instanceof Ignis_Entity)) {
                    boolean flag = entityHit.hurt(DamageSource.mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + entityHit.getMaxHealth() * hpdamage));
                    if (entityHit instanceof Player) {
                        if (entityHit.isBlocking() && shieldbreakticks > 0) {
                            disableShield(entityHit, shieldbreakticks);
                        }
                    }
                    if (flag) {
                        this.playSound(SoundEvents.ANVIL_LAND, 1.5f, 0.8F + this.getRandom().nextFloat() * 0.1F);
                        double d0 = entityHit.getX() - this.getX();
                        double d1 = entityHit.getZ() - this.getZ();
                        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                        entityHit.push(d0 / d2 * 2.5D, airborne, d1 / d2 * 2.5D);
                        if (slowticks > 0) {
                            entityHit.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), slowticks));
                        }
                    }
                }
            }
        }
    }

    private void Poke(float range, float arc, int shieldbreakticks){
        LivingEntity target = this.getTarget();
        if (target != null) {
            float entityHitAngle = (float) ((Math.atan2(target.getZ() - this.getZ(), target.getX() - this.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingAngle = this.yBodyRot % 360;
            if (entityHitAngle < 0) {
                entityHitAngle += 360;
            }
            if (entityAttackingAngle < 0) {
                entityAttackingAngle += 360;
            }
            float entityRelativeAngle = entityHitAngle - entityAttackingAngle;
            if (this.distanceTo(target) <= range && (entityRelativeAngle <= arc / 2 && entityRelativeAngle >= -arc / 2) || (entityRelativeAngle >= 360 - arc / 2 || entityRelativeAngle <= -360 + arc / 2)) {
                boolean flag = target.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) + target.getMaxHealth() * 0.1f);
                if (target instanceof Player) {
                    if (target.isBlocking() && shieldbreakticks > 0) {
                        disableShield(target, shieldbreakticks);
                    }
                }

                if (flag && !target.getType().is(ModTag.IGNIS_CANT_POKE)) {
                    if(target.isShiftKeyDown()) {
                        target.setShiftKeyDown(false);
                    }
                    target.startRiding(this, true);
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, POKED_ATTACK);
                }
            }
        }
    }

    private void Flameswing(){
        Vec3 bladePos = socketPosArray[0];
        int snowflakeDensity = 4;
        float snowflakeRandomness = 0.5f;
        double length = prevBladePos.subtract(bladePos).length();
        int numClouds = (int) Math.floor(2 * length);
        for (int i = 0; i < numClouds; i++) {
            double x = prevBladePos.x + i * (bladePos.x - prevBladePos.x) / numClouds;
            double y = prevBladePos.y + i * (bladePos.y - prevBladePos.y) / numClouds;
            double z = prevBladePos.z + i * (bladePos.z - prevBladePos.z) / numClouds;
            for (int j = 0; j < snowflakeDensity; j++) {
                float xOffset = snowflakeRandomness * (2 * random.nextFloat() - 1);
                float yOffset = snowflakeRandomness * (2 * random.nextFloat() - 1);
                float zOffset = snowflakeRandomness * (2 * random.nextFloat() - 1);
                ParticleOptions type = this.getBossPhase() > 0 ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
                level.addParticle(type, x + xOffset, y + yOffset, z + zOffset, 0, 0, 0);
            }
        }
    }

    private void SwingParticles() {
        if (level.isClientSide) {
            Vec3 bladePos = socketPosArray[0];
            if(this.getAnimation() == HORIZONTAL_SWING_ATTACK) {
                if (this.getAnimationTick() > 27 && this.getAnimationTick() < 33) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == SWING_ATTACK) {
                if (this.getAnimationTick() > 25 && this.getAnimationTick() < 37) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == HORIZONTAL_SWING_ATTACK_SOUL) {
                if (this.getAnimationTick() > 24 && this.getAnimationTick() < 28) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == SWING_ATTACK_SOUL) {
                if (this.getAnimationTick() > 26 && this.getAnimationTick() < 29) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == BURNS_THE_EARTH) {
                if (this.getAnimationTick() > 35 && this.getAnimationTick() < 52) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == TRIPLE_ATTACK) {
                Flameswing();
            }
            if(this.getAnimation() == PHASE_3) {
                if (this.getAnimationTick() > 96 && this.getAnimationTick() < 100) {
                    Flameswing();
                }
            }
            if(this.getAnimation() == FOUR_COMBO) {
                Flameswing();
            }
            if(this.getAnimation() == STRIKE) {
                if (this.getAnimationTick() > 28 && this.getAnimationTick() < 33) {
                    Flameswing();
                }
            }
            if (this.getAnimation() == SWING_UPPERSLASH) {
                if (this.getAnimationTick() > 23 && this.getAnimationTick() < 28) {
                    Flameswing();
                }
            }

            prevBladePos = bladePos;
        }
    }

    private void ShieldSmashparticle(float radius,float vec, float math) {
        if (this.level.isClientSide) {
            for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                double motionX = getRandom().nextGaussian() * 0.07D;
                double motionY = getRandom().nextGaussian() * 0.07D;
                double motionZ = getRandom().nextGaussian() * 0.07D;
                float angle = (0.01745329251F * this.yBodyRot) + i1;
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) ;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) ;
                double extraX = radius * Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = radius * Mth.cos(angle);
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                int hitX = Mth.floor(getX() + vec * vecX+ extraX);
                int hitY = Mth.floor(getY());
                int hitZ = Mth.floor(getZ() + vec * vecZ + extraZ);
                BlockPos hit = new BlockPos(hitX, hitY, hitZ);
                BlockState block = level.getBlockState(hit.below());
                this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, block), getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, motionX, motionY, motionZ);

            }
        }
    }

    private void ShieldExplode(float radius, float math, float y) {
        if (!this.level.isClientSide) {
            float angle = (0.01745329251F * this.yBodyRot);
            float f = Mth.cos(this.getYRot() * ((float) Math.PI / 180F));
            float f1 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F));
            double extraX = radius * Mth.sin((float) (Math.PI + angle));
            double extraZ = radius * Mth.cos(angle);
            this.level.explode(this, this.getX() + extraX + f * math, this.getY() + y, this.getZ() + extraZ + f1 * math, 2.0F, Explosion.BlockInteraction.NONE);
        }
    }


    private void ShieldSmashDamage(float spreadarc,int distance, float mxy, float vec, boolean grab, int shieldbreakticks, float damage, float hpdamage) {
        double perpFacing = this.yBodyRot * (Math.PI / 180);
        double facingAngle = perpFacing + Math.PI / 2;
        int hitY = Mth.floor(this.getBoundingBox().minY - 0.5);
        double spread = Math.PI * spreadarc;
        int arcLen = Mth.ceil(distance * spread);
        double minY = this.getY() - 1;
        double maxY = this.getY() + mxy;
        for (int i = 0; i < arcLen; i++) {
            double theta = (i / (arcLen - 1.0) - 0.5) * spread + facingAngle;
            double vx = Math.cos(theta);
            double vz = Math.sin(theta);
            double px = this.getX() + vx * distance + vec * Math.cos((yBodyRot + 90) * Math.PI / 180);
            double pz = this.getZ() + vz * distance + vec * Math.sin((yBodyRot + 90) * Math.PI / 180);
            float factor = 1 - distance / (float) 12;
            if (!this.level.isClientSide) {
                int hitX = Mth.floor(px);
                int hitZ = Mth.floor(pz);
                BlockPos pos = new BlockPos(hitX, hitY, hitZ);
                BlockPos abovePos = new BlockPos(pos).above();
                BlockState block = level.getBlockState(pos);
                BlockState blockAbove = level.getBlockState(abovePos);
                if (block.getMaterial() != Material.AIR && !block.hasBlockEntity() && !blockAbove.getMaterial().blocksMotion() && !block.is(ModTag.NETHERITE_MONSTROSITY_IMMUNE)) {
                    Cm_Falling_Block_Entity fallingBlockEntity = new Cm_Falling_Block_Entity(level, hitX + 0.5D, hitY + 0.5D, hitZ + 0.5D, block);
                    level.setBlock(pos, block.getFluidState().createLegacyBlock(), 3);
                    fallingBlockEntity.push(0, 0.2D + getRandom().nextGaussian() * 0.15D, 0);
                    level.addFreshEntity(fallingBlockEntity);
                }
            }
            AABB selection = new AABB(px - 0.5, minY, pz - 0.5, px + 0.5, maxY, pz + 0.5);
            List<LivingEntity> hit = level.getEntitiesOfClass(LivingEntity.class, selection);
            for (LivingEntity entity : hit) {
                if (!isAlliedTo(entity) && !(entity instanceof Ignis_Entity) && entity != this) {
                    if (entity instanceof Player) {
                        if (entity.isBlocking() && shieldbreakticks > 0) {
                            disableShield(entity, shieldbreakticks);
                        }
                    }
                    boolean flag = entity.hurt(DamageSource.mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + entity.getMaxHealth() * hpdamage));
                    if (flag) {
                        if (grab) {
                            double magnitude = -4;
                            double x = vx * (1 - factor) * magnitude;
                            double y = 0;
                            if (entity.isOnGround()) {
                                y += 0.15;
                            }
                            double z = vz * (1 - factor) * magnitude;
                            entity.setDeltaMovement(entity.getDeltaMovement().add(x, y, z));
                        }else{
                            double airborne = 0.1 * distance + level.random.nextDouble() * 0.15;
                            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, airborne, 0.0D));
                        }
                    }

                }
            }
        }
    }

    private void earthquakesound(float distance){
        double theta = (yBodyRot) * (Math.PI / 180);
        theta += Math.PI / 2;
        double vecX = Math.cos(theta);
        double vecZ = Math.sin(theta);
        this.level.playLocalSound(this.getX() + distance * vecX, this.getY(), this.getZ() + distance * vecZ, SoundEvents.TOTEM_USE, this.getSoundSource(), 1.5f, 0.8F + this.getRandom().nextFloat() * 0.1F, false);
    }

    private void StrikeParticle(float spreadarc,int distance, float vec) {
        double perpFacing = this.yBodyRot * (Math.PI / 180);
        double facingAngle = perpFacing + Math.PI / 2;
        double spread = Math.PI * spreadarc;
        int arcLen = Mth.ceil((distance) * spread);
        for (int i = 0; i < arcLen; i++) {
            double theta = (i / (arcLen - 1.0) - 0.5) * spread + facingAngle;
            double vx = Math.cos(theta);
            double vz = Math.sin(theta);
            double vy = Mth.sqrt((float) (vx * distance * vx * distance + vz * distance * vz * distance));
            double px = this.getX() + vx * distance + vec * Math.cos((yBodyRot + 90) * Math.PI / 180);
            double pz = this.getZ() + vz * distance + vec * Math.sin((yBodyRot + 90) * Math.PI / 180);
            if (this.level.isClientSide) {
                if (this.tickCount % 2 == 0) {
                    for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                        double motionX = 0.2D * Mth.lerp(1, vx * distance + 3, vx * distance);
                        double motionY = 0.2D * Mth.lerp(1.5, vy * 0.1, vy * 0.1);
                        double motionZ = 0.2D * Mth.lerp(1, vz * distance + 3, vz * distance);
                        double spreads = 10 + this.getRandom().nextDouble() * 2.5;
                        double velocity = 0.5 + this.getRandom().nextDouble() * 0.15;

                        // spread flame
                        motionX += this.getRandom().nextGaussian() * 0.007499999832361937D * spreads;
                        motionZ += this.getRandom().nextGaussian() * 0.007499999832361937D * spreads;
                        motionX *= velocity;
                        ;
                        motionZ *= velocity;
                        ParticleOptions type = this.getBossPhase() > 0 ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
                        this.level.addParticle(type, px, this.getY() + 1.3f, pz, motionX, motionY, motionZ);

                    }
                }
            }
        }
    }


    private void Sphereparticle(float height, float vec, float size) {
        if (this.level.isClientSide) {
            if (this.tickCount % 2 == 0) {
                double d0 = this.getX();
                double d1 = this.getY() + height;
                double d2 = this.getZ();
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                for (float i = -size; i <= size; ++i) {
                    for (float j = -size; j <= size; ++j) {
                        for (float k = -size; k <= size; ++k) {
                            double d3 = (double) j + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                            double d4 = (double) i + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                            double d5 = (double) k + (this.random.nextDouble() - this.random.nextDouble()) * 0.5D;
                            double d6 = (double) Mth.sqrt((float) (d3 * d3 + d4 * d4 + d5 * d5)) / 0.5 + this.random.nextGaussian() * 0.05D;

                            ParticleOptions type = this.getBossPhase() > 0 ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
                            this.level.addParticle(type, d0 + vec * vecX, d1, d2 + vec * vecZ, d3 / d6, d4 / d6, d5 / d6);

                            if (i != -size && i != size && j != -size && j != size) {
                                k += size * 2 - 1;
                            }
                        }
                    }
                }
            }
        }
    }

    private void Phase_Transition(int dist, float damage,float hpdamage, int firetime, int brandticks) {
        if (this.getAnimationTick() % 2 == 0) {
            int distance = this.getAnimationTick() / 2 - dist;
            List<LivingEntity> entitiesHit = this.getEntityLivingBaseNearby(distance, distance, distance, distance);
            for (LivingEntity entityHit : entitiesHit) {
                if (!isAlliedTo(entityHit) && !(entityHit instanceof Ignis_Entity) && entityHit != this) {
                    boolean flag = entityHit.hurt(DamageSource.indirectMagic(this,this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + hpdamage));
                    if (flag) {
                        entityHit.setSecondsOnFire(firetime);
                        if (brandticks > 0){
                            MobEffectInstance effectinstance1 = entityHit.getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                            int i = 1;
                            if (effectinstance1 != null) {
                                i += effectinstance1.getAmplifier();
                                entityHit.removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                            } else {
                                --i;
                            }

                            i = Mth.clamp(i, 0, 4);
                            MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), brandticks, i, false, false, true);
                            entityHit.addEffect(effectinstance);
                        }
                    }
                }
            }
        }
    }

    public void positionRider(Entity passenger) {
        super.positionRider(passenger);
        if (hasPassenger(passenger)) {
            int tick = 5;
            if (this.getAnimation() == POKED_ATTACK) {
                tick = this.getAnimationTick();
                if(this.getAnimationTick() == 46) {
                    passenger.stopRiding();
                    float f1 = (float) Math.cos(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                    float f2 = (float) Math.sin(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                    //passenger.push(f1 * 2.5, 0.8, f2 * 2.5);
                }
                this.setYRot(this.yRotO);
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.getYRot();
            }
            float radius = 4F;
            float angle = (0.01745329251F * this.yBodyRot);
            double extraX = radius * Mth.sin((float) (Math.PI + angle));
            double extraZ = radius * Mth.cos(angle);
            double extraY = tick < 10 ? 0 : 0.2F * Mth.clamp(tick - 10, 0, 15);
            passenger.setPos(this.getX() + extraX, this.getY() + extraY + 1.2F, this.getZ() + extraZ);
            if ((tick - 10) % 4 == 0) {
                LivingEntity target = this.getTarget();
                if (target != null) {
                    if(passenger == target) {
                        boolean flag = target.hurt(DamageSource.mobAttack(this), 4 + target.getMaxHealth() * 0.02f);
                        if (flag) {
                            MobEffectInstance effectinstance1 = target.getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                            int i = 1;
                            if (effectinstance1 != null) {
                                i += effectinstance1.getAmplifier();
                                target.removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                            } else {
                                --i;
                            }

                            i = Mth.clamp(i, 0, 4);
                            MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 150, i, false, false, true);
                            target.addEffect(effectinstance);
                            this.heal(2f * (i + 1));
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29678_, DifficultyInstance p_29679_, MobSpawnType p_29680_, @Nullable SpawnGroupData p_29681_, @Nullable CompoundTag p_29682_) {
        return super.finalizeSpawn(p_29678_, p_29679_, p_29680_, p_29681_, p_29682_);
    }

    public void travel(Vec3 travelVector) {
        this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * (isInLava() ? 0.2F : 1F));
        if (this.getAnimation() == POKED_ATTACK) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            travelVector = Vec3.ZERO;
            super.travel(travelVector);
            return;
        }
        super.travel(travelVector);

    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.IGNIS_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.IGNIS_DEATH.get();
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


    public enum Crackiness {
        NONE(1.0F),
        LOW(0.3F),
        MEDIUM(0.2F),
        HIGH(0.1F);

        private static final List<Ignis_Entity.Crackiness> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((p_28904_) -> {
            return (double)p_28904_.fraction;
        })).collect(ImmutableList.toImmutableList());
        private final float fraction;

        private Crackiness(float p_28900_) {
            this.fraction = p_28900_;
        }

        public static Ignis_Entity.Crackiness byFraction(float p_28902_) {
            for(Ignis_Entity.Crackiness ignis$crackiness : BY_DAMAGE) {
                if (p_28902_ < ignis$crackiness.fraction) {
                    return ignis$crackiness;
                }
            }

            return NONE;
        }
    }

    private static Animation getRandomFollow(RandomSource rand) {
        switch (rand.nextInt(2)) {
            case 0:
                return SWING_UPPERSLASH;
            case 1:
                return SWING_UPPERCUT;
        }
        return SWING_UPPERSLASH;
    }


    private boolean shouldFollowUp(float Range) {
        LivingEntity target = this.getTarget();
        if (target != null && target.isAlive()) {
            Vec3 targetMoveVec = target.getDeltaMovement();
            Vec3 betweenEntitiesVec = this.position().subtract(target.position());
            boolean targetComingCloser = targetMoveVec.dot(betweenEntitiesVec) > 0;
            return this.distanceTo(target) < Range || (this.distanceTo(target) < 5 + Range && targetComingCloser);
        }
        return false;
    }

    class Hornzontal_SwingGoal extends SimpleAnimationGoal<Ignis_Entity> {
        private final int look1;
        private final int look2;
        private final int charge;
        private final int bodycheck;


        public Hornzontal_SwingGoal(Ignis_Entity entity, Animation animation, int look1, int look2, int charge, int bodycheck) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
            this.look1 = look1;
            this.look2 = look2;
            this.charge = charge;
            this.bodycheck = bodycheck;

        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (Ignis_Entity.this.getAnimationTick() < look1 && target != null || Ignis_Entity.this.getAnimationTick() > look2 && target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ignis_Entity.this.setYRot(Ignis_Entity.this.yRotO);
            }
            if (Ignis_Entity.this.getAnimationTick() == charge) {
                float f1 = (float) Math.cos(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                if(target != null) {
                    float r = Ignis_Entity.this.distanceTo(target);
                    r = Mth.clamp(r, 0, 10);
                    Ignis_Entity.this.push(f1 * 0.3 * r, 0, f2 * 0.3 * r);
                }

            }
            if (Ignis_Entity.this.getAnimationTick() == bodycheck && shouldFollowUp(3.5f) && Ignis_Entity.this.random.nextInt(3) == 0 && body_check_cooldown <= 0) {
                body_check_cooldown = BODY_CHECK_COOLDOWN;
                Animation bodycheck = Ignis_Entity.this.getBossPhase() > 0 ? BODY_CHECK_ATTACK_SOUL2 : BODY_CHECK_ATTACK2;
                AnimationHandler.INSTANCE.sendAnimationMessage(Ignis_Entity.this, bodycheck);
            }
        }
    }


    class PokeGoal extends SimpleAnimationGoal<Ignis_Entity> {
        private final int look1;
        private final int look2;
        private final int charge;
        private final int bodycheck;
        private final int motion1;
        private final int motion2;

        public PokeGoal(Ignis_Entity entity, Animation animation, int look1, int look2, int charge, int bodycheck, int motion1, int motion2) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
            this.look1 = look1;
            this.look2 = look2;
            this.charge = charge;
            this.bodycheck = bodycheck;
            this.motion1 = motion1;
            this.motion2 = motion2;
        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            float f1 = (float) Math.cos(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
            float f2 = (float) Math.sin(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
            if (Ignis_Entity.this.getAnimationTick() < look1 && target != null || Ignis_Entity.this.getAnimationTick() > look2 && target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ignis_Entity.this.setYRot(Ignis_Entity.this.yRotO);
            }
            if (Ignis_Entity.this.getAnimationTick() == charge) {
                if (target != null) {
                    float r =  Ignis_Entity.this.distanceTo(target);
                    r = Mth.clamp(r, 0, 15);
                    Ignis_Entity.this.push(f1 * 0.3 * r, 0, f2 * 0.3 * r);
                }else{
                    Ignis_Entity.this.push(f1,0, f2);
                }
            }
            if (Ignis_Entity.this.getAnimationTick() == bodycheck && shouldFollowUp(3.0f) && Ignis_Entity.this.random.nextInt(2) == 0 && body_check_cooldown <= 0) {
                body_check_cooldown = BODY_CHECK_COOLDOWN;
                Animation bodycheck = Ignis_Entity.this.getBossPhase() > 0 ? BODY_CHECK_ATTACK_SOUL4 : BODY_CHECK_ATTACK4;
                AnimationHandler.INSTANCE.sendAnimationMessage(Ignis_Entity.this, bodycheck);
            }
            if (Ignis_Entity.this.getAnimationTick() < motion1 || Ignis_Entity.this.getAnimationTick() > motion2) {
                Ignis_Entity.this.setDeltaMovement(0, Ignis_Entity.this.getDeltaMovement().y, 0);
            }
        }
    }


    class Poked extends SimpleAnimationGoal<Ignis_Entity> {

        public Poked(Ignis_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 20.0F, 20.0F);
            }
            Ignis_Entity.this.setDeltaMovement(0, Ignis_Entity.this.getDeltaMovement().y, 0);
        }
    }

    class Shield_Smash extends SimpleAnimationGoal<Ignis_Entity> {

        public Shield_Smash(Ignis_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (Ignis_Entity.this.getAnimationTick() < 34 && target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ignis_Entity.this.setYRot(Ignis_Entity.this.yRotO);
            }
            Ignis_Entity.this.setDeltaMovement(0, Ignis_Entity.this.getDeltaMovement().y, 0);

            if (Ignis_Entity.this.getAnimationTick() == 45 && shouldFollowUp(4.0f) && Ignis_Entity.this.random.nextInt(3) == 0 && body_check_cooldown <= 0) {
                body_check_cooldown = BODY_CHECK_COOLDOWN;
                Animation bodycheck = Ignis_Entity.this.getBossPhase() > 0 ? BODY_CHECK_ATTACK_SOUL3 : BODY_CHECK_ATTACK3;
                AnimationHandler.INSTANCE.sendAnimationMessage(Ignis_Entity.this, bodycheck);
            }
        }
    }

    class Air_Smash extends SimpleAnimationGoal<Ignis_Entity> {

        public Air_Smash(Ignis_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (target != null) {
                Ignis_Entity.this.lookAt(target, 30.0F, 30.0F);
            }
            if (Ignis_Entity.this.getAnimationTick() == 19) {
                if (target != null) {
                    Ignis_Entity.this.setDeltaMovement((target.getX() - Ignis_Entity.this.getX()) * 0.15D, 1.3D, (target.getZ() - Ignis_Entity.this.getZ()) * 0.15D);
                }else{
                    Ignis_Entity.this.setDeltaMovement(0, 1.4D, 0);
                }
            }

            if (Ignis_Entity.this.getAnimationTick() > 19 && Ignis_Entity.this.isOnGround()){
                AnimationHandler.INSTANCE.sendAnimationMessage(Ignis_Entity.this, SMASH);
            }

        }
    }

    class Swing_Attack_Goal extends SimpleAnimationGoal<Ignis_Entity> {
        private final int look1;
        private final int follow_through_tick;

        public Swing_Attack_Goal(Ignis_Entity entity, Animation animation, int look1, int follow_through_tick) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
            this.look1 = look1;
            this.follow_through_tick = follow_through_tick;

        }
        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (Ignis_Entity.this.getAnimationTick() < look1 && target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ignis_Entity.this.setYRot(Ignis_Entity.this.yRotO);
            }
            if (Ignis_Entity.this.getAnimationTick() == (follow_through_tick + Ignis_Entity.this.random.nextInt(9)) && shouldFollowUp(7.0f) && Ignis_Entity.this.random.nextDouble() < 0.75D) {
                Animation animation = getRandomFollow(random);
                AnimationHandler.INSTANCE.sendAnimationMessage(Ignis_Entity.this, animation);
            }
            Ignis_Entity.this.setDeltaMovement(0, Ignis_Entity.this.getDeltaMovement().y, 0);
        }

    }

    class Triple_Attack extends SimpleAnimationGoal<Ignis_Entity> {

        public Triple_Attack(Ignis_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = Ignis_Entity.this.getTarget();
            if (Ignis_Entity.this.getAnimationTick() < 30 && target != null
                    || Ignis_Entity.this.getAnimationTick() < 69 && Ignis_Entity.this.getAnimationTick() > 42 && target != null
                    || Ignis_Entity.this.getAnimationTick() < 108 && Ignis_Entity.this.getAnimationTick() > 84 && target != null
                    || Ignis_Entity.this.getAnimationTick() > 124 && target != null) {
                Ignis_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                Ignis_Entity.this.setYRot(Ignis_Entity.this.yRotO);
            }
            if (Ignis_Entity.this.getAnimationTick() == 27 || Ignis_Entity.this.getAnimationTick() == 105) {
                float f1 = (float) Math.cos(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(Ignis_Entity.this.getYRot() + 90));
                if(target != null) {
                    if (Ignis_Entity.this.distanceTo(target) > 3.5F) {
                        Ignis_Entity.this.push(f1 * 1.5, 0, f2 * 1.5);
                    }
                }else{
                    Ignis_Entity.this.push(f1 * 1.5, 0, f2 * 1.5);
                }
            }
        }
    }

}
