package L_Ender.cataclysm.tileentities;

import L_Ender.cataclysm.entity.Ender_Guardian_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModTileentites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityEnderGuardianSpawner extends TileEntityBossSpawner<Ender_Guardian_Entity> {

	public TileEntityEnderGuardianSpawner(BlockPos pos, BlockState state) {
		super(ModTileentites.ENDER_GUARDIAN_SPAWNER.get(), ModEntities.ENDER_GUARDIAN.get(), pos, state);
	}


	@Override
	protected int getRange() {
		return SHORT_RANGE;
	}
}
