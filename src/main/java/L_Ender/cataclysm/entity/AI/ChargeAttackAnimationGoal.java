package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;

public class ChargeAttackAnimationGoal<T extends Boss_monster & IAnimatedEntity> extends SimpleAnimationGoal<T> {
    private final int look1;
    private final int look2;
    private final int charge;
    private final int charge2;

    public ChargeAttackAnimationGoal(T entity, Animation animation, int look1, int look2, int charge, int charge2) {
        super(entity, animation);
        this.look1 = look1;
        this.look2 = look2;
        this.charge = charge;
        this.charge2 = charge2;
    }
    public void tick() {
        LivingEntity target = entity.getTarget();
        if (entity.getAnimationTick() < look1 && target != null || entity.getAnimationTick() > look2 && target != null) {
            entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
        } else {
            entity.setYRot(entity.yRotO);
        }
        if (entity.getAnimationTick() == charge) {
            float f1 = (float) Math.cos(Math.toRadians(entity.getYRot() + 90));
            float f2 = (float) Math.sin(Math.toRadians(entity.getYRot() + 90));
            entity.push(f1 * 1.5, 0, f2 * 1.5);
        }
        if(entity.getAnimationTick() > charge2 || entity.getAnimationTick() < charge){
            entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
        }
    }

}
