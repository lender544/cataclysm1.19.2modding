package com.github.L_Ender.cataclysm.blocks;

import com.github.L_Ender.cataclysm.init.ModTileentites;
import com.github.L_Ender.cataclysm.tileentities.TileEntityAltarOfFire;
import com.github.L_Ender.cataclysm.tileentities.TileEntityAltarOfVoid;
import com.github.L_Ender.cataclysm.tileentities.TileEntityEMP;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BlockAltarOfVoid extends BaseEntityBlock {
    private static final VoxelShape BASE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);
    private static final VoxelShape MID = Block.box(2.0D, 4.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    private static final VoxelShape TOP = Block.box(0.0D, 10.0D, 0.0D, 16.0D, 14.0D, 16.0D);
    private static final VoxelShape AXIS_AABB = Shapes.or(BASE,MID, TOP);

    public BlockAltarOfVoid() {
        super(Properties.of(Material.HEAVY_METAL)
                .noOcclusion()
                .lightLevel((block) -> 7)
                .emissiveRendering((block, world, pos) -> true)
                .strength(-1.0F, 3600000.0F)
                .noLootTable()
                .sound(SoundType.METAL));
    }

    public VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
        return AXIS_AABB;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityAltarOfVoid(pos, state);
    }


    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return createTickerHelper(p_152182_, ModTileentites.ALTAR_OF_VOID.get(), TileEntityAltarOfVoid::commonTick);
    }
}