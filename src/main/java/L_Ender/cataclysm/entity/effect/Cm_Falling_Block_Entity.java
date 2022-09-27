package L_Ender.cataclysm.entity.effect;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.init.ModEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

public class Cm_Falling_Block_Entity extends Entity {
    public int duration;
    protected static final EntityDataAccessor<BlockPos> DATA_START_POS = SynchedEntityData.defineId(Cm_Falling_Block_Entity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Optional<BlockState>> BLOCK_STATE = SynchedEntityData.defineId(Cm_Falling_Block_Entity.class, EntityDataSerializers.BLOCK_STATE);

    public Cm_Falling_Block_Entity(EntityType<Cm_Falling_Block_Entity> type, Level level) {
        super(type, level);
        this.duration = 20;
    }

    public Cm_Falling_Block_Entity(Level p_31953_, double p_31954_, double p_31955_, double p_31956_, BlockState p_31957_, int duration) {
        this(ModEntities.CM_FALLING_BLOCK.get(), p_31953_);
        this.setBlock(p_31957_);
        this.setPos(p_31954_, p_31955_ + (double)((1.0F - this.getBbHeight()) / 2.0F), p_31956_);
        this.setDeltaMovement(Vec3.ZERO);
        this.duration = duration;
        this.xo = p_31954_;
        this.yo = p_31955_;
        this.zo = p_31956_;
        this.setStartPos(this.blockPosition());
    }


    public void setStartPos(BlockPos p_31960_) {
        this.entityData.set(DATA_START_POS, p_31960_);
    }

    public BlockPos getStartPos() {
        return this.entityData.get(DATA_START_POS);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_START_POS, BlockPos.ZERO);
        getEntityData().define(BLOCK_STATE, Optional.of(Blocks.DIRT.defaultBlockState()));
    }

    public BlockState getBlock() {
        Optional<BlockState> bsOp = getEntityData().get(BLOCK_STATE);
        return bsOp.orElse(null);
    }

    public void setBlock(BlockState block) {
        getEntityData().set(BLOCK_STATE, Optional.of(block));
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));

        if (this.onGround && tickCount > duration) {
            discard();
        }
        if (tickCount > 300) {
            discard();
        }

    }

    protected void addAdditionalSaveData(CompoundTag p_31973_) {
        BlockState blockState = getBlock();
        if (blockState != null) p_31973_.put("block", NbtUtils.writeBlockState(blockState));
        p_31973_.putInt("Time", this.duration);

    }

    protected void readAdditionalSaveData(CompoundTag p_31964_) {
        Tag blockStateCompound = p_31964_.get("block");
        if (blockStateCompound != null) {
            BlockState blockState = NbtUtils.readBlockState((CompoundTag) blockStateCompound);
            setBlock(blockState);
        }
        this.duration = p_31964_.getInt("Time");

    }

    public boolean displayFireAnimation() {
        return false;
    }

    public BlockState getBlockState() {
        Optional<BlockState> bsOp = getEntityData().get(BLOCK_STATE);
        return bsOp.orElse(null);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
