package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, cataclysm.MODID);


    public static final RegistryObject<SimpleParticleType> SOUL_LAVA = PARTICLE.register("soul_lava", ()-> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> LIGHTNING = PARTICLE.register("lightning", () -> new SimpleParticleType(false));
}
