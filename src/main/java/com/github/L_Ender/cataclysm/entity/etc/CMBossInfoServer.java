package com.github.L_Ender.cataclysm.entity.etc;

import com.github.L_Ender.cataclysm.entity.Boss_monster;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CMBossInfoServer extends ServerBossEvent {
    private final Boss_monster entity;

    private final Set<ServerPlayer> unseen = new HashSet<>();

    public CMBossInfoServer(Boss_monster entity,boolean dark) {
        super(entity.getDisplayName(), entity.bossBarColor(), BossBarOverlay.PROGRESS);
        this.setVisible(true);
        this.setDarkenScreen(dark);
        this.entity = entity;
    }

    public void update() {
        this.setProgress(this.entity.getHealth() / this.entity.getMaxHealth());
        Iterator<ServerPlayer> it = this.unseen.iterator();
        while (it.hasNext()) {
            ServerPlayer player = it.next();
            if (this.entity.getSensing().hasLineOfSight(player)) {
                super.addPlayer(player);
                it.remove();
            }
        }
    }

    @Override
    public void addPlayer(ServerPlayer player) {
        if (this.entity.getSensing().hasLineOfSight(player)) {
            super.addPlayer(player);
        } else {
            this.unseen.add(player);
        }
    }

    @Override
    public void removePlayer(ServerPlayer player) {
        super.removePlayer(player);
        this.unseen.remove(player);
    }
}
