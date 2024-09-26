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
        ClassLoader loader = plugin.getClass().getClassLoader();

        Class.forName("me.neznamy.tab.api.TabAPI", false, loader);

        // Up until this was moved to the NameTagManager class
        {
            boolean hasNameTagManager = false;
            try {
                Class.forName("me.neznamy.tab.api.nametag.NameTagManager", false, loader);
                hasNameTagManager = true;
            } catch (Throwable t) { /* Old version */ }
            if (!hasNameTagManager) {
                return create_3_1_4();
            }
        }

        return create_4_0_3();
    }

    private static TabNameTagHider create_4_0_3() {
        return TabNameTagHiderImpl_4_0_3.create();
    }

    private static TabNameTagHider create_3_1_4() {
        return TabNameTagHiderImpl_3_1_4.create();
    }
}
