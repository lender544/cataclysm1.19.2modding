package L_Ender.cataclysm.util;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;

public class RendererUtils {

    private static final String USING_INCINERATOR = "UsingIncinerator";

    public static int getUsingIncineratorTime(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        return tag.getInt(USING_INCINERATOR);
    }

    public static void setUsingIncineratorTime(LivingEntity entity, int time) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        tag.putInt(USING_INCINERATOR, time);
        sync(entity, tag);
    }



    private static void sync(LivingEntity entity, CompoundTag tag) {
        CitadelEntityData.setCitadelTag(entity, tag);
        if (!entity.level.isClientSide) {
            Citadel.sendMSGToAll(new PropertiesMessage("Cataclysm_Citadel_Tag_Update", tag, entity.getId()));
        } else {
            Citadel.sendMSGToServer(new PropertiesMessage("Cataclysm_Citadel_Tag_Update", tag, entity.getId()));
        }
    }

}
