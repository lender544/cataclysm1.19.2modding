package L_Ender.cataclysm.tileentities;

import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.Ignis_Entity;
import L_Ender.cataclysm.entity.effect.Flame_Strike_Entity;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.init.ModEntities;
import L_Ender.cataclysm.init.ModItems;
import L_Ender.cataclysm.init.ModTag;
import L_Ender.cataclysm.init.ModTileentites;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TileEntityAltarOfFire extends BaseContainerBlockEntity {

    public int tickCount;
    private static final int NUM_SLOTS = 1;
    private NonNullList<ItemStack> stacks = NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
    public boolean summoningthis = false;
    public int summoningticks = 0;
    private final RandomSource rnd = RandomSource.create();

    public TileEntityAltarOfFire(BlockPos pos, BlockState state) {
        super(ModTileentites.ALTAR_OF_FIRE.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityAltarOfFire entity) {
        entity.tick();

    }

    public void tick() {
        tickCount++;
        summoningthis = false;
        if (!this.getItem(0).isEmpty()) {
            if(this.getItem(0).getItem() == ModItems.BURNING_ASHES.get()){
                summoningthis = true;
                if(summoningticks == 1) {
                    ScreenShake_Entity.ScreenShake(this.level, Vec3.atCenterOf(this.getBlockPos()), 20, 0.05f, 0, 150);
                 //   this.level.addFreshEntity(new Flame_Strike_Entity(this.level, this.getBlockPos().getX() + 0.5F, this.getBlockPos().getY(), this.getBlockPos().getZ() + 0.5F, 0, 0, 100, 0, 2.5F, false, null));
                }
                if(summoningticks > 118 && summoningticks < 121) {
                    Sphereparticle(3,3);
                }
                if(summoningticks > 121) {
                    this.setItem(0, ItemStack.EMPTY);
                    BlockBreaking(3, 3, 3);
                    Ignis_Entity ignis = ModEntities.IGNIS.get().create(level);
                    ignis.setPos(this.getBlockPos().getX() + 0.5F, this.getBlockPos().getY() + 3, this.getBlockPos().getZ() + 0.5F);
                    if (!level.isClientSide) {
                        level.addFreshEntity(ignis);
                    }
                }

            }
        }
        if(!summoningthis){
            summoningticks = 0;
        }else{
            summoningticks++;
        }
    }

    private void BlockBreaking(int x, int y, int z) {
        //this.level.destroyBlock(this.getBlockPos(), false);
        int MthX = Mth.floor(this.getBlockPos().getX());
        int MthY = Mth.floor(this.getBlockPos().getY());
        int MthZ = Mth.floor(this.getBlockPos().getZ());
        for (int k2 = -x; k2 <= x; ++k2) {
            for (int l2 = -z; l2 <= z; ++l2) {
                for (int j = -1; j <= y; ++j) {
                    int i3 = MthX + k2;
                    int k = MthY + j;
                    int l = MthZ + l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    BlockState block = this.level.getBlockState(blockpos);
                    if (block.getMaterial() != Material.AIR && !block.is(ModTag.ALTAR_DESTROY_IMMUNE)) {
                        this.level.destroyBlock(blockpos, false);
                    }

                }
            }
        }
    }

    private void Sphereparticle(float height, float size) {
        double d0 = this.getBlockPos().getX() + 0.5F;
        double d1 = this.getBlockPos().getY() + height;
        double d2 = this.getBlockPos().getZ() + 0.5F;
        for (float i = -size; i <= size; ++i) {
            for (float j = -size; j <= size; ++j) {
                for (float k = -size; k <= size; ++k) {
                    double d3 = (double) j + (this.rnd.nextDouble() - this.rnd.nextDouble()) * 0.5D;
                    double d4 = (double) i + (this.rnd.nextDouble() - this.rnd.nextDouble()) * 0.5D;
                    double d5 = (double) k + (this.rnd.nextDouble() - this.rnd.nextDouble()) * 0.5D;
                    double d6 = (double) Mth.sqrt((float) (d3 * d3 + d4 * d4 + d5 * d5)) / 0.5 + this.rnd.nextGaussian() * 0.05D;

                    this.level.addParticle(ParticleTypes.FLAME, d0, d1, d2, d3 / d6, d4 / d6, d5 / d6);

                    if (i != -size && i != size && j != -size && j != size) {
                        k += size * 2 - 1;

                    }
                }
            }
        }
    }

    @Override
    public int getContainerSize() {
        return this.stacks.size();
    }

    @Override
    public ItemStack getItem(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        if (!this.stacks.get(index).isEmpty()) {
            ItemStack itemstack;

            if (this.stacks.get(index).getCount() <= count) {
                itemstack = this.stacks.get(index);
                this.stacks.set(index, ItemStack.EMPTY);
            } else {
                itemstack = this.stacks.get(index).split(count);

                if (this.stacks.get(index).isEmpty()) {
                    this.stacks.set(index, ItemStack.EMPTY);
                }

            }
            return itemstack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        boolean flag = !stack.isEmpty() && stack.sameItem(this.stacks.get(index)) && ItemStack.tagMatches(stack, this.stacks.get(index));
        this.stacks.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        ContainerHelper.saveAllItems(compound, this.stacks);
    }

    @Override
    public void startOpen(Player player) {
    }

    @Override
    public void stopOpen(Player player) {
    }



    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        if (packet != null && packet.getTag() != null) {
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
            ContainerHelper.loadAllItems(packet.getTag(), this.stacks);
        }
    }

    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack lvt_2_1_ = this.stacks.get(index);
        if (lvt_2_1_.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            this.stacks.set(index, ItemStack.EMPTY);
            return lvt_2_1_;
        }
    }

    @Override
    public Component getDisplayName() {
        return getDefaultName();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.cataclysm.altar_of_fire");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.getContainerSize(); i++) {
            if (!this.getItem(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
