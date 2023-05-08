package com.github.L_Ender.cataclysm.entity.The_Leviathan;

import com.github.L_Ender.cataclysm.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Abyss_Portal_Entity extends Entity {

    protected static final EntityDataAccessor<Direction> ATTACHED_FACE = SynchedEntityData.defineId(Abyss_Portal_Entity.class, EntityDataSerializers.DIRECTION);
    protected static final EntityDataAccessor<Integer> LIFESPAN = SynchedEntityData.defineId(Abyss_Portal_Entity.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Boolean> ENTRANCE = SynchedEntityData.defineId(Abyss_Portal_Entity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Optional<BlockPos>> DESTINATION = SynchedEntityData.defineId(Abyss_Portal_Entity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Optional<UUID>> SISTER_UUID = SynchedEntityData.defineId(Abyss_Portal_Entity.class, EntityDataSerializers.OPTIONAL_UUID);

    private boolean madeOpenNoise = false;
    private boolean madeCloseNoise = false;
    private boolean isDummy = false;
    private boolean hasClearedObstructions;


    public Abyss_Portal_Entity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Abyss_Portal_Entity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntities.ABYSS_PORTAL.get(), level);
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void tick() {
        super.tick();
        if (this.tickCount == 1) {
            if(this.getLifespan() == 0){
                this.setLifespan(2000);
            }
        }
        if(!madeOpenNoise){
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.playSound(SoundEvents.END_PORTAL_SPAWN, 1.0F, 1 + random.nextFloat() * 0.2F);
            madeOpenNoise = true;
        }
        Direction direction2 = this.getAttachmentFacing().getOpposite();
        float minX = -0.15f;
        float minY = -0.15f;
        float minZ = -0.15f;
        float maxX = 0.15f;
        float maxY = 0.15f;
        float maxZ = 0.15f;
        switch (direction2) {
            case NORTH:
            case SOUTH:
                minX = -3;
                maxX = 3;
                minY = -3;
                maxY = 3;
                break;
            case EAST:
            case WEST:
                minZ = -3;
                maxZ = 3;
                minY = -3;
                maxY = 3;
                break;
            case UP:
            case DOWN:
                minX = -3;
                maxX = 3;
                minZ = -3;
                maxZ = 3;
                break;
        }
        AABB bb = new AABB(this.getX() + minX, this.getY() + minY, this.getZ() + minZ, this.getX() + maxX, this.getY() + maxY, this.getZ() + maxZ);
        this.setBoundingBox(bb);
        if(random.nextFloat() < 0.5F && level.isClientSide && Math.min(tickCount, this.getLifespan()) >= 20){
            double particleX = this.getBoundingBox().minX + random.nextFloat() * (this.getBoundingBox().maxX - this.getBoundingBox().minX);
            double particleY = this.getBoundingBox().minY + random.nextFloat() * (this.getBoundingBox().maxY - this.getBoundingBox().minY);
            double particleZ = this.getBoundingBox().minZ + random.nextFloat() * (this.getBoundingBox().maxZ - this.getBoundingBox().minZ);
            level.addParticle(ParticleTypes.PORTAL, particleX, particleY, particleZ, 0.1 * random.nextGaussian(), 0.1 * random.nextGaussian(), 0.1 * random.nextGaussian());
        }
        List<Entity> entities = new ArrayList<>();
        entities.addAll(this.level.getEntities(this, bb.deflate(0.2F)));
        entities.addAll(this.level.getEntitiesOfClass(The_Leviathan_Entity.class, bb.inflate(3)));
        if (!level.isClientSide) {
            if (this.getDestination() != null && this.getLifespan() > 20 && tickCount > 20 && this.getEntrance()) {
                BlockPos offsetPos = this.getDestination().relative(this.getAttachmentFacing().getOpposite(), 2);
                for (Entity e : entities) {
                    if(e.isOnPortalCooldown() || e.isShiftKeyDown() || e instanceof Abyss_Portal_Entity ){
                        continue;
                    }
                     if (e instanceof The_Leviathan_Entity) {
                        ((The_Leviathan_Entity) e).teleportTo(Vec3.atCenterOf(this.getDestination()));
                        e.setPortalCooldown();
                        ((The_Leviathan_Entity) e).resetPortalLogic();
                    }else {
                        e.teleportToWithTicket(offsetPos.getX() + 0.5f, offsetPos.getY() + 0.5f, offsetPos.getZ() + 0.5f);
                        e.setPortalCooldown();
                     }
                }
            }
        }
        this.setLifespan(this.getLifespan() - 1);
        if(this.getLifespan() <= 20){
            if(!madeCloseNoise){
                this.gameEvent(GameEvent.ENTITY_PLACE);
              //  this.playSound(AMSoundRegistry.VOID_PORTAL_CLOSE.get(), 1.0F, 1 + random.nextFloat() * 0.2F);
                madeCloseNoise = true;
            }
        }
        if (this.getLifespan() <= 0) {
            this.remove(RemovalReason.DISCARDED);
        }
    }




    public Direction getAttachmentFacing() {
        return this.entityData.get(ATTACHED_FACE);
    }

    public void setAttachmentFacing(Direction facing) {
        this.entityData.set(ATTACHED_FACE, facing);
    }

    public int getLifespan() {
        return this.entityData.get(LIFESPAN);
    }

    public void setLifespan(int i) {
        this.entityData.set(LIFESPAN, i);
    }


    public boolean getEntrance() {
        return this.entityData.get(ENTRANCE);
    }

    public void setEntrance(boolean entrance) {
        this.entityData.set(ENTRANCE, entrance);
    }

    public BlockPos getDestination() {
        return this.entityData.get(DESTINATION).orElse(null);
    }

    public void setDestination(BlockPos destination) {
        this.entityData.set(DESTINATION, Optional.ofNullable(destination));
        if (this.getSisterId() == null) {
            createAndSetSister(level, null);
        }
    }

    public void createAndSetSister(Level world, Direction dir){
        Abyss_Portal_Entity portal = ModEntities.ABYSS_PORTAL.get().create(world);
        portal.setAttachmentFacing(dir != null ? dir : this.getAttachmentFacing().getOpposite());
        BlockPos safeDestination = this.getDestination();
        portal.teleportToWithTicket(safeDestination.getX() + 0.5f, safeDestination.getY() + 0.5f, safeDestination.getZ() + 0.5f);
        portal.link(this);
        portal.setEntrance(false);
        world.addFreshEntity(portal);
    }

    public void setDestination(BlockPos destination, Direction dir) {
        this.entityData.set(DESTINATION, Optional.ofNullable(destination));
        if (this.getSisterId() == null ) {
            createAndSetSister(level, dir);
        }
    }

    public void link(Abyss_Portal_Entity portal) {
        this.setSisterId(portal.getUUID());
        portal.setSisterId(this.getUUID());
        portal.setLifespan(this.getLifespan());
        this.setDestination(portal.blockPosition());
        portal.setDestination(this.blockPosition());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ATTACHED_FACE, Direction.DOWN);
        this.entityData.define(LIFESPAN, 300);
        this.entityData.define(SISTER_UUID, Optional.empty());
        this.entityData.define(DESTINATION, Optional.empty());
        this.entityData.define(ENTRANCE, true);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.entityData.set(ATTACHED_FACE, Direction.from3DDataValue(compound.getByte("AttachFace")));
        this.setLifespan(compound.getInt("Lifespan"));
        if (compound.contains("DX")) {
            int i = compound.getInt("DX");
            int j = compound.getInt("DY");
            int k = compound.getInt("DZ");
            this.entityData.set(DESTINATION, Optional.of(new BlockPos(i, j, k)));
        } else {
            this.entityData.set(DESTINATION, Optional.empty());
        }
        if (compound.hasUUID("SisterUUID")) {
            this.setSisterId(compound.getUUID("SisterUUID"));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putByte("AttachFace", (byte) this.entityData.get(ATTACHED_FACE).get3DDataValue());
        compound.putInt("Lifespan", getLifespan());
        BlockPos blockpos = this.getDestination();
        if (blockpos != null) {
            compound.putInt("DX", blockpos.getX());
            compound.putInt("DY", blockpos.getY());
            compound.putInt("DZ", blockpos.getZ());
        }
        if (this.getSisterId() != null) {
            compound.putUUID("SisterUUID", this.getSisterId());
        }
    }

    public Entity getSister() {
        UUID id = getSisterId();
        if (id != null && !level.isClientSide) {
            return ((ServerLevel) level).getEntity(id);
        }
        return null;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {

        return distance < 1024;
    }

    @Nullable
    public UUID getSisterId() {
        return this.entityData.get(SISTER_UUID).orElse(null);
    }

    public void setSisterId(@Nullable UUID uniqueId) {
        this.entityData.set(SISTER_UUID, Optional.ofNullable(uniqueId));
    }

}
