package L_Ender.cataclysm.blocks;

import L_Ender.cataclysm.init.ModTileentites;
import L_Ender.cataclysm.inventory.WeaponInfusionMenu;
import L_Ender.cataclysm.tileentities.TileEntityAltarOfFire;
import L_Ender.cataclysm.tileentities.TileEntityEMP;
import L_Ender.cataclysm.tileentities.TileEntityMechanical_Infusion_Forge;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class Mechanical_Infusion_Forge extends BaseEntityBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("cataclysm.container.weapon_infusion");

    public Mechanical_Infusion_Forge() {
        super(Properties.of(Material.HEAVY_METAL)
                .lightLevel((block) -> 7)
                .emissiveRendering((block, world, pos) -> true)
                .strength(50.0F, 1200.0F)
                .sound(SoundType.METAL));

    }

    public InteractionResult use(BlockState p_56428_, Level p_56429_, BlockPos p_56430_, Player p_56431_, InteractionHand p_56432_, BlockHitResult p_56433_) {
        if (p_56429_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_56431_.openMenu(p_56428_.getMenuProvider(p_56429_, p_56430_));
            p_56431_.awardStat(Stats.INTERACT_WITH_SMITHING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity te = level.getBlockEntity(pos);
        return new SimpleMenuProvider((i, inv, player) -> {
            return new WeaponInfusionMenu(i, inv, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityMechanical_Infusion_Forge(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return createTickerHelper(p_152182_, ModTileentites.MECHANICAL_INFUSION_FORGE.get(), TileEntityMechanical_Infusion_Forge::commonTick);
    }
}
