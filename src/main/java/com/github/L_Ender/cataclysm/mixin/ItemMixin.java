package com.github.L_Ender.cataclysm.mixin;


import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.mixin.LevelMixin;
import net.minecraft.world.damagesource.BadRespawnPointDamage;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemMixin{

    @Shadow public abstract ItemStack getItem();

    @Inject(
            method = {"hurt"},
            remap = true,
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)

    public void Cmhurt(DamageSource damageSource, float p_32014_, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getItem().isEmpty() && damageSource.isExplosion() && this.getItem().is(ModTag.EXPLOSION_IMMUNE_ITEM)) {
            cir.setReturnValue(false);
        }

    }

}

