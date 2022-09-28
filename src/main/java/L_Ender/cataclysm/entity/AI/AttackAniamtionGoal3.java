package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class AttackAniamtionGoal3<T extends Boss_monster & IAnimatedEntity> extends SimpleAnimationGoal<T> {
    public AttackAniamtionGoal3(T entity, Animation animation) {
        super(entity, animation);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
    }

    public void tick() {
        entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
    }
}
