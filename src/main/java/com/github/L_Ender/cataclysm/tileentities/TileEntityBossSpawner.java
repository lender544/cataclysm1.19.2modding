package com.github.L_Ender.cataclysm.tileentities;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public abstract class TileEntityBossSpawner<T extends Mob> extends BlockEntity {

	protected static final int SHORT_RANGE = 6, LONG_RANGE = 50;

	protected final EntityType<T> entityType;
	protected boolean spawnedBoss = false;

	protected TileEntityBossSpawner(BlockEntityType<?> type, EntityType<T> entityType, BlockPos pos, BlockState state) {
		super(type, pos, state);
		this.entityType = entityType;
	}

	public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityBossSpawner entity) {
		entity.tick(level,pos,state,entity);
	}

	public boolean anyPlayerInRange() {
		return level.hasNearbyAlivePlayer(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D, getRange());
	}

	public void tick(Level level, BlockPos pos, BlockState state, TileEntityBossSpawner<?> te) {
		if (spawnedBoss || !anyPlayerInRange()) {
			return;
		}
		if (level.isClientSide) {
			// particles
			double rx = (pos.getX() - 0.2F) + (level.random.nextFloat() * 1.25F);
			double ry = (pos.getY() - 0.2F) + (level.random.nextFloat() * 1.25F);
			double rz = (pos.getZ() - 0.2F) + (level.random.nextFloat() * 1.25F);
			level.addParticle(ParticleTypes.SMOKE, rx, ry, rz, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.PORTAL, rx, ry, rz, 0.0D, 0.0D, 0.0D);
		} else {
			if (level.getDifficulty() != Difficulty.PEACEFUL) {
				if (spawnMyBoss((ServerLevel)level)) {
					level.destroyBlock(pos, false);
					spawnedBoss = true;
				}
			}
		}
	}

	protected boolean spawnMyBoss(ServerLevelAccessor world) {
		// create creature
		T myCreature = makeMyCreature();

		myCreature.moveTo(worldPosition, world.getLevel().random.nextFloat() * 360F, 0.0F);
		myCreature.finalizeSpawn(world, world.getCurrentDifficultyAt(worldPosition), MobSpawnType.SPAWNER, null, null);

		// set creature's home to this
		initializeCreature(myCreature);

		// spawn it
		return world.addFreshEntity(myCreature);
	}

	protected void initializeCreature(T myCreature) {
		myCreature.restrictTo(worldPosition, 46);
	}

	protected int getRange() {
		return SHORT_RANGE;
	}

	protected T makeMyCreature() {
		return entityType.create(level);
	}
}
