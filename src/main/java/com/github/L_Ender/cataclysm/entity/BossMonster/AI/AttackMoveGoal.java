package com.github.L_Ender.cataclysm.entity.BossMonster.AI;



import com.github.L_Ender.cataclysm.entity.BossMonster.Boss_monster;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class AttackMoveGoal extends Goal {
    private final Boss_monster Boss_monster;
    private LivingEntity target;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private int delayCounter;
    protected final double moveSpeed;


    public AttackMoveGoal(Boss_monster boss, boolean followingTargetEvenIfNotSeen, double moveSpeed) {
        this.Boss_monster = boss;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
        this.moveSpeed = moveSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }


    public boolean canUse() {
        this.target = this.Boss_monster.getTarget();
        return this.target != null && target.isAlive() && this.Boss_monster.getAnimation() == IAnimatedEntity.NO_ANIMATION;
    }


    public void stop() {
        this.Boss_monster.getNavigation().stop();
        if (this.Boss_monster.getTarget() == null) {
            this.Boss_monster.setAggressive(false);
            this.Boss_monster.getNavigation().stop();
        }
    }

    public boolean canContinueToUse() {
        if (target == null) {
            return false;
        } else if (!target.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.Boss_monster.getNavigation().isDone();
        } else if (!this.Boss_monster.isWithinRestriction(target.blockPosition())) {
            return false;
        } else {
            return !(target instanceof Player) || !target.isSpectator() && !((Player)target).isCreative();
        }
    }

    public void start() {
        this.Boss_monster.getNavigation().moveTo(this.path, this.moveSpeed);
        this.Boss_monster.setAggressive(true);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (target == null) {
            return;
        }
        this.Boss_monster.getLookControl().setLookAt(target, 30.0F, 30.0F);
        double distSq = this.Boss_monster.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ());
        if (--this.delayCounter <= 0) {
            this.delayCounter = 4 + this.Boss_monster.getRandom().nextInt(7);
            if (distSq > Math.pow(this.Boss_monster.getAttribute(Attributes.FOLLOW_RANGE).getValue(), 2.0D)) {
                if (!this.Boss_monster.isPathFinding()) {
                    if (!this.Boss_monster.getNavigation().moveTo(target, 1.0D)) {
                        this.delayCounter += 5;
                    }
                }
            } else {
                this.Boss_monster.getNavigation().moveTo(target, this.moveSpeed);
            }
        }
    }
}
