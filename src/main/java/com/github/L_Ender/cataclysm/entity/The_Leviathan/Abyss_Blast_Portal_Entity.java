package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.init.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Abyss_Blast_Portal_Entity extends Entity {
	public float scaleOfPortal;
	public float scaleOfPortalPrev;

	private LivingEntity caster;
	private UUID casterUuid;


	public Abyss_Blast_Portal_Entity(EntityType<? extends Entity> type, Level level) {
		super(type, level);
	}

	public Abyss_Blast_Portal_Entity(Level worldIn, double x, double y, double z, LivingEntity casterIn) {
		this(ModEntities.ABYSS_BLAST_PORTAL.get(), worldIn);
		this.setCaster(casterIn);
		this.setPos(x, y, z);
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
		if (this.tickCount > 90) {
			this.discard();
		}
		if (this.tickCount < 250 && this.scaleOfPortal < 1.0F) {
			this.scaleOfPortal += 0.05F;
		}
		if (tickCount > 250 && this.scaleOfPortal > 0.0F) {
			this.scaleOfPortal -= 0.05F;
		}
		if (!this.level.isClientSide) {
			if (this.tickCount == 1){
				Portal_Abyss_Blast_Entity DeathBeam = new Portal_Abyss_Blast_Entity(ModEntities.MINI_ABYSS_BLAST.get(), this.level, this.getCaster(), this.getX(), this.getY(), this.getZ(), (float) ((this.getYRot() - 90) * Math.PI / 180), (float) (-this.getXRot() * Math.PI / 180), 80, 90);
				this.level.addFreshEntity(DeathBeam);



			}
		}

		this.scaleOfPortalPrev = this.scaleOfPortal;
	}


	protected void readAdditionalSaveData(CompoundTag compound) {
		this.tickCount = compound.getInt("Age");
		if (compound.hasUUID("Owner")) {
			this.casterUuid = compound.getUUID("Owner");
		}
	}

	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putInt("Age", this.tickCount);

		if (this.casterUuid != null) {
			compound.putUUID("Owner", this.casterUuid);
		}
	}

	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	@Override
	protected void defineSynchedData() {

	}
}
