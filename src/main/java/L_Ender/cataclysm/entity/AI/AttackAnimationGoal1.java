package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import L_Ender.cataclysm.entity.Ignis_Entity;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;

import java.util.EnumSet;

public class AttackAnimationGoal1<T extends Boss_monster & IAnimatedEntity> extends SimpleAnimationGoal<T> {
    private final int look1;

    public AttackAnimationGoal1(T entity, Animation animation, int look1) {
        super(entity, animation);
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.LOOK, Flag.MOVE));
        this.look1 = look1;

    }
    public void tick() {
        LivingEntity target = entity.getTarget();
        if (entity.getAnimationTick() < look1 && target != null) {
            entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
        } else {
            entity.setYRot(entity.yRotO);
        }
        entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
    }
}
