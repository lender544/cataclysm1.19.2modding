package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
public abstract class AnimationGoal<T extends Boss_monster & IAnimatedEntity> extends Goal {
    protected final T entity;

    protected AnimationGoal(T entity) {
        this(entity, true);
    }

    protected AnimationGoal(T entity, boolean interruptsAI) {
        this.entity = entity;
        if (interruptsAI) this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.test(this.entity.getAnimation());
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.test(this.entity.getAnimation()) && this.entity.getAnimationTick() < this.entity.getAnimation().getDuration();
    }

    protected abstract boolean test(Animation animation);
}