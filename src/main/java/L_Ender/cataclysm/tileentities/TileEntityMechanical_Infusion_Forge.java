package L_Ender.cataclysm.tileentities;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.entity.Ignis_Entity;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModItems;
import L_Ender.cataclysm.init.ModTag;
import L_Ender.cataclysm.init.ModTileentites;
import L_Ender.cataclysm.message.MessageUpdateblockentity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class TileEntityMechanical_Infusion_Forge extends BlockEntity {



    public TileEntityMechanical_Infusion_Forge(BlockPos pos, BlockState state) {
        super(ModTileentites.MECHANICAL_INFUSION_FORGE.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityMechanical_Infusion_Forge entity) {
        entity.tick();

    }

    public void tick() {


    }

}
