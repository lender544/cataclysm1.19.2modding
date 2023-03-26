package com.github.L_Ender.cataclysm.tileentities;

import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.entity.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.L_Ender.cataclysm.init.ModTileentites;
import com.github.L_Ender.cataclysm.message.MessageUpdateblockentity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;


public class TileEntityAltarOfVoid extends BlockEntity {

    protected static final int SHORT_RANGE = 6;

    protected boolean spawnedBoss = false;

    public TileEntityAltarOfVoid(BlockPos pos, BlockState state) {
        super(ModTileentites.ALTAR_OF_VOID.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityAltarOfVoid entity) {
        entity.tick(level,pos,state,entity);
    }

    public boolean anyPlayerInRange() {
        return level.hasNearbyAlivePlayer(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D, getRange());
    }

    public void tick(Level level, BlockPos pos, BlockState state, TileEntityAltarOfVoid te) {
        if (spawnedBoss || !anyPlayerInRange()) {
            return;
        }
        if (!level.isClientSide) {
            if (level.getDifficulty() != Difficulty.PEACEFUL) {
                if (spawnMyBoss((ServerLevel)level)) {
                    level.destroyBlock(pos, false);
                    spawnedBoss = true;
                }
            }
        }
    }

    protected boolean spawnMyBoss(ServerLevelAccessor world) {

        Ender_Guardian_Entity enderGuardian = ModEntities.ENDER_GUARDIAN.get().create(level);

        enderGuardian.moveTo(worldPosition, world.getLevel().random.nextFloat() * 360F, 0.0F);
        enderGuardian.finalizeSpawn(world, world.getCurrentDifficultyAt(worldPosition), MobSpawnType.SPAWNER, null, null);

        enderGuardian.restrictTo(worldPosition, 46);

        // spawn it
        return world.addFreshEntity(enderGuardian);
    }



    protected int getRange() {
        return SHORT_RANGE;
    }

}