package com.github.L_Ender.cataclysm.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class CMDamageTypes {

    public static DamageSource causeLaserDamage(Entity entity, @Nullable LivingEntity livingentity) {
       return new IndirectEntityDamageSource("cataclysm.laser", entity, livingentity);
    }


    public static final DamageSource EMP = new DamageSource("cataclysm.emp").bypassArmor();

}
