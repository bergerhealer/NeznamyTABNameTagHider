package com.bergerkiller.bukkit.neznamytabnametaghider;

import com.bergerkiller.bukkit.common.softdependency.SoftDependency;
import org.bukkit.plugin.Plugin;

/**
 * Implements a {@link SoftDependency} to detect the TAB plugin and provide means
 * to hide or re-show player name tags.
 */
public class TabNameTagHiderDependency extends SoftDependency<TabNameTagHider> {

    public TabNameTagHiderDependency(Plugin owningPlugin) {
        super(owningPlugin, "TAB", TabNameTagHider.NONE);
    }

    @Override
    protected TabNameTagHider initialize(Plugin plugin) throws Error, Exception {
        Class.forName("me.neznamy.tab.api.TabAPI");

        try {
            Class.forName("me.neznamy.tab.api.nametag.NameTagManager");
            return create_4_0_3();
        } catch (Throwable t) { /* Old version? */ }

        return create_3_1_4();
    }

    private static TabNameTagHider create_4_0_3() {
        return TabNameTagHiderImpl_4_0_3.create();
    }

    private static TabNameTagHider create_3_1_4() {
        return TabNameTagHiderImpl_3_1_4.create();
    }
}
