package L_Ender.cataclysm.entity.AI;



import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

class AttackMoveGoal extends Goal {

    private PathfinderMob creatureEntity;
    private LivingEntity target;
    private int repath;
    private double targetX;
    private double targetY;
    private double targetZ;


    public AttackMoveGoal(PathfinderMob creatureEntity) {
        this.creatureEntity = creatureEntity;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.target = creatureEntity.getTarget();
        return this.target != null && target.isAlive(); //&& this.creatureEntity.getAnimation() == IAnimatedEntity.NO_ANIMATION;
    }

    @Override
    public void start() {
        this.repath = 0;
    }

    @Override
    public void stop() {
        this.creatureEntity.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = this.creatureEntity.getTarget();
        if (target == null) return;
        double dist = this.creatureEntity.distanceToSqr(this.targetX, this.targetY, this.targetZ);
        this.creatureEntity.getLookControl().setLookAt(target, 30.0F, 30.0F);
        if (--this.repath <= 0 && (
                this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D ||
                        target.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D) ||
                this.creatureEntity.getNavigation().isDone()) {
            this.targetX = target.getX();
            this.targetY = target.getY();
            this.targetZ = target.getZ();
            this.repath = 4 + this.creatureEntity.getRandom().nextInt(7);
            if (dist > 32.0D * 32.0D) {
                this.repath += 10;
            } else if (dist > 16.0D * 16.0D) {
                this.repath += 5;
            }
            if (!this.creatureEntity.getNavigation().moveTo(target, 1.0D)) {
                this.repath += 15;
            }
        }
    }
}
