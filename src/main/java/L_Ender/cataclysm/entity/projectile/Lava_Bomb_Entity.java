package L_Ender.cataclysm.entity.projectile;

import L_Ender.cataclysm.config.CMConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class Lava_Bomb_Entity extends ThrowableProjectile {


    private boolean hasHit;
    public double prevDeltaMovementX, prevDeltaMovementY, prevDeltaMovementZ;

    public Lava_Bomb_Entity(EntityType type, Level world) {
        super(type, world);
    }

    public Lava_Bomb_Entity(EntityType type, Level world, LivingEntity thrower) {
        super(type, thrower, world);
    }


    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void onHit(HitResult ray) {
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);
        this.hasHit = true;
        if (!this.level.isClientSide) {
            this.doTerrainEffects();
        }
    }

    private void doTerrainEffects() {

        final int range = 0;

        int ix = Mth.floor(this.xOld);
        int iy = Mth.floor(this.yOld);
        int iz = Mth.floor(this.zOld);

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = new BlockPos(ix + x, iy + y, iz + z);
                    this.doTerrainEffect(pos);
                }
            }
        }
    }

    private void doTerrainEffect(BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getMaterial() == Material.WATER) {
            this.level.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
        }
        if (this.level.isEmptyBlock(pos) && Blocks.LAVA.defaultBlockState().canSurvive(this.level, pos)) {
            this.level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
        }
    }

    @Override
    public void tick() {
        super.tick();
        prevDeltaMovementX = getDeltaMovement().x;
        prevDeltaMovementY = getDeltaMovement().y;
        prevDeltaMovementZ = getDeltaMovement().z;

        setYRot(-((float) Mth.atan2(getDeltaMovement().x, getDeltaMovement().z)) * (180F / (float)Math.PI)) ;

        if (this.hasHit) {
            this.getDeltaMovement().multiply(0.1D, 0.1D, 0.1D);


            if (!level.isClientSide) {
                this.playSound(SoundEvents.GENERIC_BURN, 1.5f, 0.75f);
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), CMConfig.Lavabombradius, Explosion.BlockInteraction.NONE);
                discard();
            }
        } else {
            makeTrail();
        }
    }

    public void makeTrail() {
        for (int i = 0; i < 5; i++) {
            double dx = getX() + 1.5F * (random.nextFloat() - 0.5F);
            double dy = getY() + 1.5F * (random.nextFloat() - 0.5F);
            double dz = getZ() + 1.5F * (random.nextFloat() - 0.5F);

            level.addParticle(ParticleTypes.FLAME, dx, dy, dz, -getDeltaMovement().x(), -getDeltaMovement().y(), -getDeltaMovement().z());
        }
    }

    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected float getGravity() {
        return this.hasHit ? 0F : 0.025F;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}