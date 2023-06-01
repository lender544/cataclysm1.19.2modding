package com.github.L_Ender.cataclysm.effects;


import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EffectAbyssal_Fear extends MobEffect {

    public EffectAbyssal_Fear() {
        super(MobEffectCategory.HARMFUL, 0x741aff);
    }

    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {

    }


    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

}
