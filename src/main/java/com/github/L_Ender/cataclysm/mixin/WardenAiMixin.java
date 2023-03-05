package com.github.L_Ender.cataclysm.mixin;


import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WardenAi.class)
public class WardenAiMixin {

    @Inject(
            method = {"Lnet/minecraft/world/entity/monster/warden/WardenAi;updateActivity(Lnet/minecraft/world/entity/monster/warden/Warden;)V"},
            remap = true,
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private static void di_updateActivity(Warden warden, CallbackInfo ci) {
        Brain<Warden> brain = warden.getBrain();
        if(warden.hasEffect(ModEffect.EFFECTSTUN.get())){
            brain.eraseMemory(MemoryModuleType.ROAR_TARGET);
            brain.eraseMemory(MemoryModuleType.ATTACK_TARGET);
            warden.setAggressive(false);
            ci.cancel();
        }
    }

}
