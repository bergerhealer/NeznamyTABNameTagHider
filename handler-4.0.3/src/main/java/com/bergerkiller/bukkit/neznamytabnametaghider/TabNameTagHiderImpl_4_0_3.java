package com.bergerkiller.bukkit.neznamytabnametaghider;

import org.bukkit.entity.Player;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.nametag.NameTagManager;

public class TabNameTagHiderImpl_4_0_3 implements TabNameTagHider.TabPlayerNameTagHider {
    private final TabPlayer player;
    private final NameTagManager nametagManager;
    private boolean needToRestoreNametag = false;

    private TabNameTagHiderImpl_4_0_3(TabAPI tab, Player player) {
        this.player = tab.getPlayer(player.getUniqueId());
        this.nametagManager = tab.getNameTagManager();
    }

    @Override
    public void hide() {
        if (!nametagManager.hasHiddenNameTag(player)) {
            nametagManager.hideNameTag(player);
            needToRestoreNametag = true;
        }
    }

    @Override
    public void show() {
        if (needToRestoreNametag) {
            needToRestoreNametag = false;
            nametagManager.showNameTag(player);
        }
    }

    public static TabNameTagHider create() {
        final TabAPI tab = TabAPI.getInstance();
        tab.getNameTagManager(); // Fail early!
        return player -> new TabNameTagHiderImpl_4_0_3(tab, player);
    }
}
