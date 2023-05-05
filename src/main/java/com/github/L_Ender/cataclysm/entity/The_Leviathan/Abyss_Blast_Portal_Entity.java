package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.init.ModEntities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Abyss_Blast_Portal_Entity extends Entity {


	private LivingEntity caster;
	private UUID casterUuid;
	protected static final EntityDataAccessor<Integer> LIFESPAN = SynchedEntityData.defineId(Abyss_Blast_Portal_Entity.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> LASERFIRE = SynchedEntityData.defineId(Abyss_Blast_Portal_Entity.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> LASERDURATION = SynchedEntityData.defineId(Abyss_Blast_Portal_Entity.class, EntityDataSerializers.INT);


	public Abyss_Blast_Portal_Entity(EntityType<? extends Entity> type, Level level) {
		super(type, level);
	}

	public Abyss_Blast_Portal_Entity(Level worldIn, double x, double y, double z, LivingEntity casterIn) {
		this(ModEntities.ABYSS_BLAST_PORTAL.get(), worldIn);
		this.setCaster(casterIn);
		this.setPos(x, y, z);
	}

    public Abyss_Blast_Portal_Entity(PlayMessages.SpawnEntity spawnEntity, Level level) {
		this(ModEntities.ABYSS_BLAST_PORTAL.get(), level);
    }

    @Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}


	public void setCaster(@Nullable LivingEntity p_190549_1_) {
		this.caster = p_190549_1_;
		this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUUID();
	}

	@Nullable
	public LivingEntity getCaster() {
		if (this.caster == null && this.casterUuid != null && this.level instanceof ServerLevel) {
			Entity entity = ((ServerLevel)this.level).getEntity(this.casterUuid);
			if (entity instanceof LivingEntity) {
				this.caster = (LivingEntity)entity;
			}
		}

		return this.caster;
	}

	public void tick() {
		super.tick();
		if (this.tickCount > getLifespan()) {
			this.discard();
		}
		if (!this.level.isClientSide) {
			if (this.tickCount == getLaserFire()){
				Portal_Abyss_Blast_Entity DeathBeam = new Portal_Abyss_Blast_Entity(ModEntities.MINI_ABYSS_BLAST.get(), this.level, this.getCaster(), this.getX(), this.getY(), this.getZ(), (float) ((this.getYRot() - 90) * Math.PI / 180), (float) (-this.getXRot() * Math.PI / 180), getLaserDuration(), 90);
				this.level.addFreshEntity(DeathBeam);
			}
		}
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {

		return distance < 1024;
	}

	public int getLifespan() {
		return this.entityData.get(LIFESPAN);
	}

	public void setLifespan(int i) {
		this.entityData.set(LIFESPAN, i);
	}

	public int getLaserFire() {
		return this.entityData.get(LASERFIRE);
	}

	public void setLaserFire(int i) {
		this.entityData.set(LASERFIRE, i);
	}

	public int getLaserDuration() {
		return this.entityData.get(LASERDURATION);
	}

	public void setLaserDuration(int i) {
		this.entityData.set(LASERDURATION, i);
	}


	@Override
	protected void defineSynchedData() {
		this.entityData.define(LIFESPAN, 300);
		this.entityData.define(LASERFIRE, 1);
		this.entityData.define(LASERDURATION, 90);

	}



	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("Owner")) {
			this.casterUuid = compound.getUUID("Owner");
		}
		this.setLifespan(compound.getInt("Lifespan"));
		this.setLaserFire(compound.getInt("LaserFire"));
		this.setLaserDuration(compound.getInt("LaserDuration"));
	}

	protected void addAdditionalSaveData(CompoundTag compound) {

		if (this.casterUuid != null) {
			compound.putUUID("Owner", this.casterUuid);
		}
		compound.putInt("Lifespan", getLifespan());
		compound.putInt("LaserFire", getLaserFire());
		compound.putInt("LaserDuration", getLaserDuration());
	}

	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

}
