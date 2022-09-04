package L_Ender.cataclysm.entity.partentity;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.entity.Netherite_Monstrosity_Entity;
import L_Ender.cataclysm.message.MessageHurtMultipart;
import L_Ender.cataclysm.message.MessageInteractMultipart;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.entity.PartEntity;

public class Netherite_Monstrosity_Part extends PartEntity<Netherite_Monstrosity_Entity> {

    private final EntityDimensions size;
    public float scale = 1;

    public Netherite_Monstrosity_Part(Netherite_Monstrosity_Entity parent, float sizeX, float sizeY) {
        super(parent);
        this.size = EntityDimensions.scalable(sizeX, sizeY);
        this.refreshDimensions();
    }

    public Netherite_Monstrosity_Part(Netherite_Monstrosity_Entity entityCachalotWhale, float sizeX, float sizeY, EntityDimensions size) {
        super(entityCachalotWhale);
        this.size = size;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {

    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean isPickable() {
        return true;
    }

    public void tick(){
        super.tick();
    }

    protected void collideWithNearbyEntities() {

    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(level.isClientSide && this.getParent() != null){
            cataclysm.sendMSGToServer(new MessageInteractMultipart(this.getParent().getId(), hand == InteractionHand.OFF_HAND));
        }

        return this.getParent() == null ? InteractionResult.PASS : this.getParent().mobInteract(player, hand);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(level.isClientSide && this.getParent() != null && !this.getParent().isInvulnerableTo(source)){
            cataclysm.sendMSGToServer(new MessageHurtMultipart(this.getId(), this.getParent().getId(), amount, source.msgId));
        }

        return !this.isInvulnerableTo(source) && this.getParent().attackEntityFromPart(this, source, amount * 1.6F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }

    public boolean is(Entity entityIn) {
        return this == entityIn || this.getParent() == entityIn;
    }

    public Packet<?> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return this.size.scale(scale);
    }

}
