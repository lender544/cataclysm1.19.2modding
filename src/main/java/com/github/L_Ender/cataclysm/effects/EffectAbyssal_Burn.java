package com.github.L_Ender.cataclysm.effects;


import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EffectAbyssal_Burn extends MobEffect {

    public EffectAbyssal_Burn() {
        super(MobEffectCategory.HARMFUL, 0x6500ff);
    }

    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {
        LivingEntityIn.hurt(CMDamageTypes.ABYSSAL_BURN, 1.0F);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        int k = 50 >> amplifier;
        if (k > 0) {
            return duration % k == 0;
        } else {
            return true;
        }
    }

}
