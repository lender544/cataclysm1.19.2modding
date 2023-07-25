package com.github.L_Ender.cataclysm.capabilities;

import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class HookCapability {
    public static ResourceLocation ID = new ResourceLocation(cataclysm.MODID, "hook_cap");

    public interface IHookCapability extends INBTSerializable<CompoundTag> {


        void setEntity(LivingEntity entity);

        void setHasHook(boolean hasHook);

        boolean hasHook();
    }

    public static class HookCapabilityImp implements IHookCapability {


        private LivingEntity host;
        private boolean hook;

        @Override
        public void setEntity(LivingEntity entity) {
            this.host = entity;
        }


        @Override
        public void setHasHook(boolean hook) {
            this.hook = hook;
        }

        @Override
        public boolean hasHook() {
            return this.hook;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("hasHook", this.hasHook());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.setHasHook(nbt.getBoolean("hasHook"));
        }

        public static class HookProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
            private final LazyOptional<IHookCapability> instance = LazyOptional.of(HookCapabilityImp::new);

            @Override
            public CompoundTag serializeNBT() {
                return instance.orElseThrow(NullPointerException::new).serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
            }

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                return ModCapabilities.HOOK_CAPABILITY.orEmpty(cap, instance.cast());
            }
        }
    }
}
