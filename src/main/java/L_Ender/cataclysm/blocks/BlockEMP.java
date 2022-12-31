package L_Ender.cataclysm.blocks;

import L_Ender.cataclysm.init.ModParticle;
import L_Ender.cataclysm.init.ModTileentites;
import L_Ender.cataclysm.tileentities.TileEntityEMP;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BlockEMP extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty OVERLOAD = BooleanProperty.create("overload");

    public BlockEMP() {
        super(Properties.of(Material.HEAVY_METAL)
                .noOcclusion()
                .lightLevel((block) -> 7)
                .noLootTable()
                .emissiveRendering((block, world, pos) -> true)
                .strength(-1.0F, 3600000.0F)
                .sound(SoundType.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false).setValue(OVERLOAD, false));
    }


    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if(!worldIn.isClientSide){
            this.updateState(state, worldIn, pos, blockIn);
        }
    }

    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if(!worldIn.isClientSide){
            this.updateState(state, worldIn, pos, state.getBlock());
        }
    }

    public void updateState(BlockState state, Level worldIn, BlockPos pos, Block blockIn) {
        boolean flag = state.getValue(POWERED);
        boolean flag1 = worldIn.hasNeighborSignal(pos);

        if (flag1 != flag) {
            worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(flag1)), 3);
            worldIn.updateNeighborsAt(pos.below(), this);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(OVERLOAD, false);
    }


    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        Direction direction = Direction.getRandom(rand);
        double d0 = 0.5625D;
        double d = (rand.nextFloat() - 0.5F);
        if (direction != Direction.UP) {
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = worldIn.getBlockState(blockpos);
            if (!stateIn.canOcclude() || !blockstate.isFaceSturdy(worldIn, blockpos, direction.getOpposite())) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + d0 * (double)direction.getStepX() : (double)rand.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + d0 * (double)direction.getStepZ() : (double)rand.nextFloat();
                if (stateIn.getValue(OVERLOAD)) {
                    for (int i1 = 0; i1 < 20; i1++) {
                        worldIn.addParticle(DustParticleOptions.REDSTONE, (double) pos.getX() + d1, (double) pos.getY() + 0.75D, (double) pos.getZ() + d3, 0, 0, 0);
                    }
                }else{
                    worldIn.addParticle(ModParticle.LIGHTNING.get(), (double) pos.getX() + 0.5D, (double) pos.getY() + 0.75D, (double) pos.getZ() + 0.5D, d * 2.0D, d, d * 2.0D);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityEMP(pos, state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED,OVERLOAD);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return createTickerHelper(p_152182_, ModTileentites.EMP.get(), TileEntityEMP::commonTick);
    }
}
