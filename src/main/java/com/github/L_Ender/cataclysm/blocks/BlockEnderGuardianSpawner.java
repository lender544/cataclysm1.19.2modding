package com.github.L_Ender.cataclysm.blocks;

import com.github.L_Ender.cataclysm.init.ModTileentites;
import com.github.L_Ender.cataclysm.tileentities.TileEntityBossSpawner;
import com.github.L_Ender.cataclysm.tileentities.TileEntityEnderGuardianSpawner;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockEnderGuardianSpawner extends BaseEntityBlock {


	public BlockEnderGuardianSpawner(Properties props) {
		super(props);
	}



	public RenderShape getRenderShape(BlockState p_56794_) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntityEnderGuardianSpawner(pos,state);
	}

	@Override
	public boolean canEntityDestroy(BlockState state, BlockGetter world, BlockPos pos, Entity entity) {
		return state.getDestroySpeed(world, pos) >= 0f;
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
		return createTickerHelper(p_152182_, ModTileentites.ENDER_GUARDIAN_SPAWNER.get(), TileEntityBossSpawner::commonTick);
	}

}
