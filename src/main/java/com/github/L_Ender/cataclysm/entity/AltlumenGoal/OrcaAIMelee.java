package com.github.L_Ender.cataclysm.entity.AltlumenGoal;

import com.github.L_Ender.cataclysm.entity.The_Leviathan_Entity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class OrcaAIMelee extends MeleeAttackGoal {

    public OrcaAIMelee(The_Leviathan_Entity orca, double v, boolean b) {
        super(orca, v, b);
    }

    public boolean canUse(){
        if(this.mob.getTarget() == null || ((The_Leviathan_Entity)this.mob).shouldUseJumpAttack(this.mob.getTarget())){
            return false;
        }
        return super.canUse();
    }
}
