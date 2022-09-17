package L_Ender.cataclysm.entity;

import L_Ender.cataclysm.entity.AI.AttackMoveGoal;
import L_Ender.cataclysm.entity.AI.SimpleAnimationGoal;
import L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import L_Ender.cataclysm.entity.projectile.Ashen_Breath_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;


public class Ignited_Revenant_Entity extends Boss_monster {

    public static final Animation ASH_BREATH_ATTACK = Animation.create(53);

    private int timeWithoutTarget;
    private static final EntityDataAccessor<Boolean> ANGER = SynchedEntityData.defineId(Ignited_Revenant_Entity.class, EntityDataSerializers.BOOLEAN);

    public float angerProgress;
    public float prevangerProgress;


    public Ignited_Revenant_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
        this.maxUpStep = 1.5F;
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,
                ASH_BREATH_ATTACK};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ChargeGoal());
        this.goalSelector.addGoal(2, new Ignited_Revenant_Goal());
        this.goalSelector.addGoal(0, new ShootGoal(this, ASH_BREATH_ATTACK));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder ignited_revenant() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.ARMOR, 12)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    protected int decreaseAirSupply(int air) {
        return air;
    }

    public MobType getMobType() {
        return MobType.UNDEAD;
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
        this.entityData.define(ANGER, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void setIsAnger(boolean isAnger) {
        this.entityData.set(ANGER, isAnger);
    }

    public boolean getIsAnger() {
        return this.entityData.get(ANGER);
    }


    public void tick() {
        super.tick();
       // setYRot(yBodyRot);
        AnimationHandler.INSTANCE.updateAnimations(this);
        LivingEntity target = this.getTarget();
        prevangerProgress = angerProgress;
        if (this.getIsAnger() && angerProgress < 5F) {
            angerProgress++;
        }
        if (!this.getIsAnger() && angerProgress > 0F) {
            angerProgress--;
        }


        if(this.getAnimation() == ASH_BREATH_ATTACK){
            if (this.getAnimationTick() == 21) {
                this.playSound(ModSounds.REVENANT_BREATH.get(), 1.0f, 1.0f);

            }
        }


    }

    protected SoundEvent getAmbientSound() {
        this.playSound(ModSounds.REVENANT_IDLE.get(), 1.0f, 0.75f);
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        this.playSound(ModSounds.REVENANT_HURT.get(), 1.0f, 0.75f);
        return null;
    }

    protected SoundEvent getDeathSound() {
        this.playSound(ModSounds.REVENANT_DEATH.get(), 1.0f, 0.75f);
        return null;
    }


    @Override
    protected void onDeathAIUpdate() {
        super.onDeathAIUpdate();

    }


    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new CMPathNavigateGround(this, worldIn);
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    class Ignited_Revenant_Goal extends AttackMoveGoal {


        public Ignited_Revenant_Goal() {
            super(Ignited_Revenant_Entity.this, true, 1.1);
        }

        @Override
        public void start() {
            super.start();
            Ignited_Revenant_Entity.this.setIsAnger(true);

        }
        @Override
        public void stop() {
            super.stop();
            Ignited_Revenant_Entity.this.setIsAnger(false);
        }
    }

    class ChargeGoal extends Goal {
        public ChargeGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        private LivingEntity attackTarget;

        @Override
        public boolean canUse() {
            this.attackTarget = getTarget();
            return this.attackTarget != null
                     && this.attackTarget.isAlive() && Ignited_Revenant_Entity.this.distanceTo(attackTarget) <= 4F;
        }

        @Override
        public void start() {
            Ignited_Revenant_Entity.this.setIsAnger(false);
        }

        @Override
        public void tick() {
            if (angerProgress == 0 && Ignited_Revenant_Entity.this.getAnimation() == NO_ANIMATION ){
                Ignited_Revenant_Entity.this.setAnimation(ASH_BREATH_ATTACK);
            }
        }

        @Override
        public void stop() {
            this.attackTarget = null;
        }

        public boolean requiresUpdateEveryTick() {
            return false;
        }
    }

    class ShootGoal extends SimpleAnimationGoal<Ignited_Revenant_Entity> {

        public ShootGoal(Ignited_Revenant_Entity entity, Animation animation) {
            super(entity, animation);
        }

        public void tick() {
            LivingEntity target = Ignited_Revenant_Entity.this.getTarget();

            if (target != null) {
                if (Ignited_Revenant_Entity.this.getAnimationTick() < 27) {
                    Ignited_Revenant_Entity.this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                }else{
                    Ignited_Revenant_Entity.this.getLookControl().setLookAt(target, 3.0F, 30.0F);
                }

            }
            Vec3 mouthPos = new Vec3(0, 2.3, 0);
            mouthPos = mouthPos.yRot((float) Math.toRadians(-getYRot() - 90));
            mouthPos = mouthPos.add(position());
            mouthPos = mouthPos.add(new Vec3(0, 0, 0).xRot((float) Math.toRadians(-getXRot())).yRot((float) Math.toRadians(-yHeadRot)));
            Ashen_Breath_Entity breath = new Ashen_Breath_Entity(ModEntities.ASHEN_BREATH.get(), Ignited_Revenant_Entity.this.level, Ignited_Revenant_Entity.this);
            if (Ignited_Revenant_Entity.this.getAnimationTick() == 27) {
                breath.absMoveTo(mouthPos.x, mouthPos.y, mouthPos.z, Ignited_Revenant_Entity.this.yHeadRot, Ignited_Revenant_Entity.this.getXRot());
                Ignited_Revenant_Entity.this.level.addFreshEntity(breath);
            }

        }
    }

}





