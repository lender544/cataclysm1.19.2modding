package com.github.L_Ender.cataclysm.entity.AI;

import com.github.L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SimpleAnimationGoal<T extends Boss_monster & IAnimatedEntity> extends AnimationGoal<T> {

    private final Animation animation;

    public SimpleAnimationGoal(T entity, Animation animation) {
        super(entity);
        this.animation = animation;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
    }

    @Override
    protected boolean test(Animation animation) {
        return animation == this.animation;
    }


}