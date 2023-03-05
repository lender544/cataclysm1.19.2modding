package com.github.L_Ender.cataclysm.init;


import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.effects.EffectBlazing_Brand;
import com.github.L_Ender.cataclysm.effects.EffectMonstrous;
import com.github.L_Ender.cataclysm.effects.EffectStun;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffect {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            cataclysm.MODID);

    public static final RegistryObject<MobEffect> EFFECTMONSTROUS = EFFECTS.register("monstrous", EffectMonstrous::new);

    public static final RegistryObject<MobEffect> EFFECTBLAZING_BRAND = EFFECTS.register("blazing_brand", EffectBlazing_Brand::new);

    public static final RegistryObject<MobEffect> EFFECTSTUN = EFFECTS.register("stun", EffectStun::new);
}
