package com.github.L_Ender.cataclysm.mixin;

import com.github.L_Ender.cataclysm.util.PlayerProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerProperties {

    private static final EntityDataAccessor<Boolean> CM_HOOK_TRACKER = SynchedEntityData.defineId(PlayerMixin.class, EntityDataSerializers.BOOLEAN);

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(
            method = {"Lnet/minecraft/world/entity/player/Player;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"},
            remap = true,
            at = @At(
                    value = "TAIL"
            ),
            cancellable = true)
    public void readAdditional(CompoundTag compound, CallbackInfo info) {
        this.setHasHook(compound.getBoolean("CmhasHook"));
    }


    @Inject(
            method = {"Lnet/minecraft/world/entity/player/Player;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"},
            remap = true,
            at = @At(
                    value = "TAIL"
            ),
            cancellable = true)
    public void writeNbt(CompoundTag tag, CallbackInfo info) {
        tag.putBoolean("CmhasHook", this.hasHook());
    }


    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"Lnet/minecraft/world/entity/player/Player;defineSynchedData()V"}
    )
    private void di_registerData(CallbackInfo ci) {
        this.entityData.define(CM_HOOK_TRACKER, false);
    }


    public boolean hasHook(){
        return this.entityData.get(CM_HOOK_TRACKER);
    }

    public void setHasHook(boolean hasHook){
        this.entityData.set(CM_HOOK_TRACKER, hasHook);
    }

}


