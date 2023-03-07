package com.github.L_Ender.cataclysm.mixin;


import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.hoglin.HoglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HoglinAi.class)
public class HoglinAiMixin {

    @Inject(
            method = {"Lnet/minecraft/world/entity/monster/hoglin/HoglinAi;updateActivity(Lnet/minecraft/world/entity/monster/hoglin/Hoglin;)V"},
            remap = true,
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private static void cm_updateActivity(Hoglin hoglin, CallbackInfo ci) {
        Brain<Hoglin> brain = hoglin.getBrain();
        if(hoglin.hasEffect(ModEffect.EFFECTSTUN.get())){
            brain.eraseMemory(MemoryModuleType.ATTACK_TARGET);
            hoglin.setAggressive(false);
            ci.cancel();
        }
    }

}
