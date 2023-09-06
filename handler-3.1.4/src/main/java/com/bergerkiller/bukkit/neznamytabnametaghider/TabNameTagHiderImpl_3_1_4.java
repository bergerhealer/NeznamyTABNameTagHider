package com.bergerkiller.bukkit.neznamytabnametaghider;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.team.TeamManager;
import org.bukkit.entity.Player;

public class TabNameTagHiderImpl_3_1_4 implements TabNameTagHider.TabPlayerNameTagHider {
    private final TabPlayer player;
    private final TeamManager teamManager;
    private boolean needToRestoreNametag = false;

    private TabNameTagHiderImpl_3_1_4(TabAPI tab, Player player) {
        this.player = tab.getPlayer(player.getUniqueId());
        this.teamManager = tab.getTeamManager();
    }

    @Override
    public void hide() {
        if (!teamManager.hasHiddenNametag(player)) {
            teamManager.hideNametag(player);
            needToRestoreNametag = true;
        }
    }

    @Override
    public void show() {
        if (needToRestoreNametag) {
            needToRestoreNametag = false;
            teamManager.showNametag(player);
        }
    }

    public static TabNameTagHider create() {
        final TabAPI tab = TabAPI.getInstance();
        tab.getTeamManager(); // Fail early!
        return player -> new TabNameTagHiderImpl_3_1_4(tab, player);
    }
}
