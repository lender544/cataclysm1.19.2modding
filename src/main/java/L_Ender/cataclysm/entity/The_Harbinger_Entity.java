package L_Ender.cataclysm.entity;

import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.AI.AttackMoveGoal;
import L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import L_Ender.cataclysm.entity.projectile.Ashen_Breath_Entity;
import L_Ender.cataclysm.entity.projectile.Blazing_Bone_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;


public class The_Harbinger_Entity extends Boss_monster implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(The_Harbinger_Entity.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);
    private final float[] xRotHeads = new float[2];
    private final float[] yRotHeads = new float[2];
    private final float[] xRotOHeads = new float[2];
    private final float[] yRotOHeads = new float[2];
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];
    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (p_31504_) -> {
        return p_31504_.getMobType() != MobType.UNDEAD && p_31504_.attackable();
    };
    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(LIVING_ENTITY_SELECTOR);


    public The_Harbinger_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
    }

    public static AttributeSupplier.Builder harbinger() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0D).add(Attributes.MOVEMENT_SPEED, (double)0.6F).add(Attributes.FLYING_SPEED, (double)0.6F).add(Attributes.FOLLOW_RANGE, 40.0D).add(Attributes.ARMOR, 4.0D);
    }


    protected int decreaseAirSupply(int air) {
        return air;
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    public ItemEntity spawnAtLocation(ItemStack stack) {
        ItemEntity itementity = this.spawnAtLocation(stack,0.0f);
        if (itementity != null) {
            itementity.setGlowingTag(true);
            itementity.setExtendedLifetime();
        }
        return itementity;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void aiStep() {
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
            Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double d0 = vec3.y;
                if ( this.getY() < entity.getY() + 5.0D) {
                    d0 = Math.max(0.0D, d0);
                    d0 += 0.3D - d0 * (double)0.6F;
                }

                vec3 = new Vec3(vec3.x, d0, vec3.z);
                Vec3 vec31 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                if (vec31.horizontalDistanceSqr() > 9.0D) {
                    Vec3 vec32 = vec31.normalize();
                    vec3 = vec3.add(vec32.x * 0.3D - vec3.x * 0.6D, 0.0D, vec32.z * 0.3D - vec3.z * 0.6D);
                }
            }
        }

        this.setDeltaMovement(vec3);
        if (vec3.horizontalDistanceSqr() > 0.05D) {
            this.setYRot((float)Mth.atan2(vec3.z, vec3.x) * (180F / (float)Math.PI) - 90.0F);
        }

        super.aiStep();
        AnimationHandler.INSTANCE.updateAnimations(this);
        for(int i = 0; i < 2; ++i) {
            this.yRotOHeads[i] = this.yRotHeads[i];
            this.xRotOHeads[i] = this.xRotHeads[i];
        }

        for(int j = 0; j < 2; ++j) {
            int k = this.getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level.getEntity(k);
            }

            if (entity1 != null) {
                double d9 = this.getHeadX(j + 1);
                double d1 = this.getHeadY(j + 1);
                double d3 = this.getHeadZ(j + 1);
                double d4 = entity1.getX() - d9;
                double d5 = entity1.getEyeY() - d1;
                double d6 = entity1.getZ() - d3;
                double d7 = Math.sqrt(d4 * d4 + d6 * d6);
                float f = (float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)) - 90.0F;
                float f1 = (float)(-(Mth.atan2(d5, d7) * (double)(180F / (float)Math.PI)));
                this.xRotHeads[j] = this.m_31442_(this.xRotHeads[j], f1, 40.0F);
                this.yRotHeads[j] = this.m_31442_(this.yRotHeads[j], f, 10.0F);
            } else {
                this.yRotHeads[j] = this.m_31442_(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }
        }


        for(int l = 0; l < 3; ++l) {
            double d8 = this.getHeadX(l);
            double d10 = this.getHeadY(l);
            double d2 = this.getHeadZ(l);
            this.level.addParticle(ParticleTypes.SMOKE, d8 + this.random.nextGaussian() * (double)0.3F, d10 + this.random.nextGaussian() * (double)0.3F, d2 + this.random.nextGaussian() * (double)0.3F, 0.0D, 0.0D, 0.0D);

        }

    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        for (int i = 1; i < 3; ++i) {
            if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
                this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
                if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
                    int i3 = i - 1;
                    int j3 = this.idleHeadUpdates[i - 1];
                    this.idleHeadUpdates[i3] = this.idleHeadUpdates[i - 1] + 1;
                    if (j3 > 15) {
                        float f = 10.0F;
                        float f1 = 5.0F;
                        double d0 = Mth.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                        double d1 = Mth.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                        double d2 = Mth.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);
                        this.performRangedAttack(i + 1, d0, d1, d2, true);
                        this.idleHeadUpdates[i - 1] = 0;
                    }
                }

                int l1 = this.getAlternativeTarget(i);
                if (l1 > 0) {
                    LivingEntity livingentity = (LivingEntity) this.level.getEntity(l1);
                    if (livingentity != null && this.canAttack(livingentity) && !(this.distanceToSqr(livingentity) > 900.0D) && this.hasLineOfSight(livingentity)) {
                        this.performRangedAttack(i + 1, livingentity);
                        this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
                        this.idleHeadUpdates[i - 1] = 0;
                    } else {
                        this.setAlternativeTarget(i, 0);
                    }
                } else {
                    List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));
                    if (!list.isEmpty()) {
                        LivingEntity livingentity1 = list.get(this.random.nextInt(list.size()));
                        this.setAlternativeTarget(i, livingentity1.getId());
                    }
                }
            }
        }

        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        } else {
            this.setAlternativeTarget(0, 0);
        }
        if (this.tickCount % 20 == 0) {
            this.heal(1.0F);
        }
    }



    protected SoundEvent getAmbientSound() {
        this.playSound(SoundEvents.WITHER_AMBIENT, 1.0f, 0.5f);
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        this.playSound(SoundEvents.WITHER_HURT, 1.0f, 0.5f);
        return null;
    }

    protected SoundEvent getDeathSound() {
        this.playSound(SoundEvents.WITHER_HURT, 1.0f, 0.5f);
        return null;
    }


    @Override
    protected void onDeathAIUpdate() {
        super.onDeathAIUpdate();

    }

    private double getHeadX(int p_31515_) {
        if (p_31515_ <= 0) {
            return this.getX();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_31515_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);
            return this.getX() + (double)f1 * 1.3D;
        }
    }

    private double getHeadY(int p_31517_) {
        return p_31517_ <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
    }

    private double getHeadZ(int p_31519_) {
        if (p_31519_ <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_31519_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            return this.getZ() + (double)f1 * 1.3D;
        }
    }

    private float m_31442_(float p_31443_, float p_31444_, float p_31445_) {
        float f = Mth.wrapDegrees(p_31444_ - p_31443_);
        if (f > p_31445_) {
            f = p_31445_;
        }

        if (f < -p_31445_) {
            f = -p_31445_;
        }

        return p_31443_ + f;
    }

    private void performRangedAttack(int p_31458_, LivingEntity p_31459_) {
        this.performRangedAttack(p_31458_, p_31459_.getX(), p_31459_.getY() + (double)p_31459_.getEyeHeight() * 0.5D, p_31459_.getZ(), p_31458_ == 0 && this.random.nextFloat() < 0.001F);
    }

    private void performRangedAttack(int p_31449_, double p_31450_, double p_31451_, double p_31452_, boolean p_31453_) {
        if (!this.isSilent()) {
            this.level.levelEvent((Player)null, 1024, this.blockPosition(), 0);
        }

        double d0 = this.getHeadX(p_31449_);
        double d1 = this.getHeadY(p_31449_);
        double d2 = this.getHeadZ(p_31449_);
        double d3 = p_31450_ - d0;
        double d4 = p_31451_ - d1;
        double d5 = p_31452_ - d2;
        WitherSkull witherskull = new WitherSkull(this.level, this, d3, d4, d5);
        witherskull.setOwner(this);
        if (p_31453_) {
            witherskull.setDangerous(true);
        }

        witherskull.setPosRaw(d0, d1, d2);
        this.level.addFreshEntity(witherskull);
    }

    public void performRangedAttack(LivingEntity p_31468_, float p_31469_) {
        this.performRangedAttack(0, p_31468_);
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    public float getHeadYRot(int p_31447_) {
        return this.yRotHeads[p_31447_];
    }

    public float getHeadXRot(int p_31481_) {
        return this.xRotHeads[p_31481_];
    }

    public int getAlternativeTarget(int p_31513_) {
        return this.entityData.get(DATA_TARGETS.get(p_31513_));
    }

    public void setAlternativeTarget(int p_31455_, int p_31456_) {
        this.entityData.set(DATA_TARGETS.get(p_31455_), p_31456_);
    }

    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    public boolean canBeAffected(MobEffectInstance p_31495_) {
        return p_31495_.getEffect() != MobEffects.WITHER && super.canBeAffected(p_31495_);
    }


}





