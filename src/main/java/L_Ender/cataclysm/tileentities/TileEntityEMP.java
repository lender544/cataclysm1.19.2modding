package L_Ender.cataclysm.tileentities;

import L_Ender.cataclysm.blocks.BlockEMP;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.init.ModParticle;
import L_Ender.cataclysm.init.ModSounds;
import L_Ender.cataclysm.init.ModTileentites;
import L_Ender.cataclysm.util.CMDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TileEntityEMP extends BlockEntity {

    private float chompProgress;
    private float prevChompProgress;
    public int ticksExisted;

    public TileEntityEMP(BlockPos pos, BlockState state) {
        super(ModTileentites.EMP.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityEMP entity) {
        entity.tick();

    }

    public void tick() {
        prevChompProgress = chompProgress;
        boolean powered = false;
        if(getBlockState().getBlock() instanceof BlockEMP){
            powered = getBlockState().getValue(BlockEMP.POWERED);
        }
        boolean overload = false;
        if(getBlockState().getBlock() instanceof BlockEMP){
            overload = getBlockState().getValue(BlockEMP.OVERLOAD);
        }

        if(powered && chompProgress < 20F){
            chompProgress++;
        }
        if(!powered && chompProgress > 0F){
            chompProgress--;
        }
        float x = this.getBlockPos().getX() + 0.5F;
        float y = this.getBlockPos().getY() + 0.5f;
        float z = this.getBlockPos().getZ() + 0.5F;
        if(!overload && chompProgress == 15F ){
            level.addParticle(ModParticle.EM_PULSE.get(), x, y, z, 0, 0, 0);
            ScreenShake_Entity.ScreenShake(this.level, Vec3.atCenterOf(this.getBlockPos()), 20, 0.01f, 0, 20);
            level.playSound((Player)null, this.getBlockPos(), ModSounds.EMP_ACTIVATED.get(), SoundSource.BLOCKS, 4F, level.random.nextFloat() * 0.2F + 1.0F);
            level.setBlockAndUpdate(this.getBlockPos(), getBlockState().setValue(BlockEMP.OVERLOAD, true));
            AABB screamBox = new AABB(this.getBlockPos().getX() - 5, this.getBlockPos().getY() - 5F, this.getBlockPos().getZ() - 5, this.getBlockPos().getX() + 5, this.getBlockPos().getY() + 5F, this.getBlockPos().getZ() + 5F);
            for(LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, screamBox)){
                entity.hurt(CMDamageTypes.EMP, 6 + entity.getRandom().nextInt(3));

            }
        }
        ticksExisted++;
    }

    public float getChompProgress(float partialTick){
        return prevChompProgress + (chompProgress - prevChompProgress) * partialTick;
    }

}
