package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SimpleAnimationGoal<T extends Boss_monster & IAnimatedEntity> extends AnimationGoal<T> {

    private final Animation animation;

    public SimpleAnimationGoal(T entity, Animation animation) {
        super(entity);
        this.animation = animation;
    }
    public SimpleAnimationGoal(T entity, Animation animation, boolean interruptsAI) {
        super(entity, interruptsAI);
        this.animation = animation;
    }

    public SimpleAnimationGoal(T entity, Animation animation, EnumSet<Flag> interruptFlagTypes) {
        super(entity);
        this.animation = animation;
        setFlags(interruptFlagTypes);
    }

    @Override
    protected boolean test(Animation animation) {
        return animation == this.animation;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}