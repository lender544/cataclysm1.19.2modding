package com.github.L_Ender.cataclysm.mixin;


import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.PiglinBruteAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBruteAi.class)
public class PiglinBruteAiMixin {
    
    @Inject(
            method = {"Lnet/minecraft/world/entity/monster/piglin/PiglinBruteAi;updateActivity(Lnet/minecraft/world/entity/monster/piglin/PiglinBrute;)V"},
            remap = true,
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private static void cm_updateActivity(PiglinBrute warden, CallbackInfo ci) {
        Brain<PiglinBrute> brain = warden.getBrain();
        if(warden.hasEffect(ModEffect.EFFECTSTUN.get())){
            brain.eraseMemory(MemoryModuleType.ATTACK_TARGET);
            warden.setAggressive(false);
            ci.cancel();
        }
    }
}
