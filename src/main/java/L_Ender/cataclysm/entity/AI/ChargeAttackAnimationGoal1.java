package L_Ender.cataclysm.entity.AI;

import L_Ender.cataclysm.entity.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;

public class ChargeAttackAnimationGoal1<T extends Boss_monster & IAnimatedEntity> extends SimpleAnimationGoal<T> {
    private final int look1;

    private final int charge;
    private final float motionx;
    private final float motionz;

    public ChargeAttackAnimationGoal1(T entity, Animation animation, int look1, int charge, float motionx, float motionz) {
        super(entity, animation);
        this.look1 = look1;
        this.charge = charge;
        this.motionx = motionx;
        this.motionz = motionz;
    }
    public void tick() {
        LivingEntity target = entity.getTarget();
        if (entity.getAnimationTick() < look1 && target != null) {
            entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
        } else {
            entity.setYRot(entity.yRotO);
        }
        if (entity.getAnimationTick() == charge && target != null){
            entity.setDeltaMovement((target.getX() - entity.getX()) * motionx, 0, (target.getZ() - entity.getZ()) * motionz);
        }
    }

}
