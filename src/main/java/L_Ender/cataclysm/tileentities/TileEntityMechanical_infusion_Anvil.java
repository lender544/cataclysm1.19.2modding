package L_Ender.cataclysm.tileentities;

import L_Ender.cataclysm.init.ModTileentites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityMechanical_infusion_Anvil extends BlockEntity {



    public TileEntityMechanical_infusion_Anvil(BlockPos pos, BlockState state) {
        super(ModTileentites.MECHANICAL_INFUSION_ANVIL.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityMechanical_infusion_Anvil entity) {
        entity.tick();

    }

    public void tick() {


    }

}
