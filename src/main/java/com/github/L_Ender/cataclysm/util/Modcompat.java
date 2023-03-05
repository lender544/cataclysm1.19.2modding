package com.github.L_Ender.cataclysm.util;

import com.github.L_Ender.cataclysm.entity.projectile.Void_Scatter_Arrow_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;


public class Modcompat {

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ModItems.VOID_SCATTER_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position position, ItemStack stack) {
                return new Void_Scatter_Arrow_Entity(ModEntities.VOID_SCATTER_ARROW.get(), position.x(), position.y(), position.z(), world);
            }
        });
    }
}
