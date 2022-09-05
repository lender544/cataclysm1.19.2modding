package L_Ender.cataclysm.message;

import L_Ender.cataclysm.entity.partentity.Cm_Part_Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MessageCMMultipart {

    private int id;
    private Entity entity;
    private int len;
    private List<PartDataHolder> data = new ArrayList<>();

    public MessageCMMultipart(FriendlyByteBuf buf) {
        this.id = buf.readInt();
        this.len = buf.readInt();
        for (int i = 0; i < len; i++) {
            if (buf.readBoolean()) {
                data.add(PartDataHolder.decode(buf));
            }
        }
    }

    public MessageCMMultipart(Entity entity) {
        this.entity = entity;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entity.getId());
        PartEntity<?>[] parts = this.entity.getParts();
        // We assume the client and server part arrays are identical, else everything will crash and burn. Don't even bother handling it.
        if (parts != null) {
            buf.writeInt(parts.length);
            for (PartEntity<?> part : parts) {
                if (part instanceof Cm_Part_Entity<?> tfPart) {
                    buf.writeBoolean(true);
                    tfPart.writeData().encode(buf);
                } else {
                    buf.writeBoolean(false);
                }
            }
        } else {
            buf.writeInt(0);
        }
    }

    public static class Handler {

        @SuppressWarnings("Convert2Lambda")
        public static boolean onMessage(MessageCMMultipart message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(new Runnable() {
                @Override
                public void run() {
                    Level world = Minecraft.getInstance().level;
                    if (world == null)
                        return;
                    Entity ent = world.getEntity(message.id);
                    if (ent != null && ent.isMultipartEntity()) {
                        PartEntity<?>[] parts = ent.getParts();
                        if (parts == null)
                            return;
                        int index = 0;
                        for (PartEntity<?> part : parts) {
                            if (part instanceof Cm_Part_Entity<?> tfPart) {
                                tfPart.readData(message.data.get(index));
                                index++;
                            }
                        }
                    }
                }
            });
            ctx.get().setPacketHandled(true);
            return true;
        }
    }

    public record PartDataHolder(double x,
                                 double y,
                                 double z,
                                 float yRot,
                                 float xRot,
                                 float width,
                                 float height,
                                 boolean fixed,
                                 boolean dirty,
                                 List<SynchedEntityData.DataItem<?>> data) {


        public void encode(FriendlyByteBuf buffer) {
            buffer.writeDouble(x);
            buffer.writeDouble(y);
            buffer.writeDouble(z);
            buffer.writeFloat(yRot);
            buffer.writeFloat(xRot);
            buffer.writeFloat(width);
            buffer.writeFloat(height);
            buffer.writeBoolean(fixed);
            buffer.writeBoolean(dirty);
            if (dirty) {
                SynchedEntityData.pack(data, buffer);
            }
        }

        static PartDataHolder decode(FriendlyByteBuf buffer) {
            boolean dirty;
            return new PartDataHolder(
                    buffer.readDouble(),
                    buffer.readDouble(),
                    buffer.readDouble(),
                    buffer.readFloat(),
                    buffer.readFloat(),
                    buffer.readFloat(),
                    buffer.readFloat(),
                    buffer.readBoolean(),
                    dirty = buffer.readBoolean(),
                    dirty ? SynchedEntityData.unpack(buffer) : null
            );
        }

    }
}

