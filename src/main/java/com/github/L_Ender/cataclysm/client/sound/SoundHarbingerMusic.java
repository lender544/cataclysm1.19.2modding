package com.github.L_Ender.cataclysm.client.sound;

import com.github.L_Ender.cataclysm.ClientProxy;
import com.github.L_Ender.cataclysm.entity.BossMonster.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;

import java.util.Map;

public class SoundHarbingerMusic extends AbstractTickableSoundInstance {
    private final The_Harbinger_Entity Guardian;
    private int ticksExisted = 0;
    public SoundHarbingerMusic(The_Harbinger_Entity guardian) {
        super(ModSounds.HARBINGER_MUSIC.get(), SoundSource.RECORDS, guardian.getRandom());
        this.Guardian = guardian;
        this.attenuation = Attenuation.NONE;
        this.looping = true;
        this.delay = 0;
        this.x = this.Guardian.getX();
        this.y = this.Guardian.getY();
        this.z = this.Guardian.getZ();
    }

    public boolean shouldPlaySound() {
        return !this.Guardian.isSilent() && ClientProxy.HARBINGER_SOUND_MAP.get(this.Guardian.getId()) == this;
    }

    public boolean isNearest() {
        float dist = 400;
        for(Map.Entry<Integer, SoundHarbingerMusic> entry : ClientProxy.HARBINGER_SOUND_MAP.entrySet()){
            SoundHarbingerMusic GuardianMusic = entry.getValue();
            if(GuardianMusic != this && distanceSq(GuardianMusic.x, GuardianMusic.y, GuardianMusic.z) < dist * dist && GuardianMusic.shouldPlaySound()){
                return false;
            }
        }
        return true;
    }


    public double distanceSq(double p_218140_1_, double p_218140_3_, double p_218140_5_) {
        double lvt_10_1_ = (double)this.getX() - p_218140_1_;
        double lvt_12_1_ = (double)this.getY() - p_218140_3_;
        double lvt_14_1_ = (double)this.getZ() - p_218140_5_;
        return lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_ + lvt_14_1_ * lvt_14_1_;
    }

    public void tick() {
        if(ticksExisted % 100 == 0){
            Minecraft.getInstance().getMusicManager().stopPlaying();

        }
        if (!this.Guardian.isRemoved() && this.Guardian.isAlive()) {
            this.volume = 1;
            this.pitch = 1;
            this.x = this.Guardian.getX();
            this.y = this.Guardian.getY();
            this.z = this.Guardian.getZ();
        } else {
            this.stop();
            ClientProxy.HARBINGER_SOUND_MAP.remove(Guardian.getId());
        }
        ticksExisted++;
    }

}
