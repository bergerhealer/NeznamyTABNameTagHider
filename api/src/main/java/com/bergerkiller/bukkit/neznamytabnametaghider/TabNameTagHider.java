package com.bergerkiller.bukkit.neznamytabnametaghider;

import org.bukkit.entity.Player;

/**
 * Temporarily hides and then restores the player's nametag displayed by the TAB plugin
 */
public interface TabNameTagHider {
    /**
     * Does nothing. Can be used as a no-op value.
     */
    TabNameTagHider NONE = player -> TabPlayerNameTagHider.NONE;

    /**
     * Gets an interface to control hiding and re-showing of a Player's name tag
     *
     * @param player Player
     * @return PlayerTabNameTagHider
     */
    TabPlayerNameTagHider get(Player player);

    /**
     * Shows or hides the Player name tag displayed by the TAB plugin.
     */
    interface TabPlayerNameTagHider {
        /**
         * Does nothing. Can be used as a no-op value.
         */
        TabPlayerNameTagHider NONE = new TabPlayerNameTagHider() {
            @Override
            public void hide() {}

            @Override
            public void show() {}
        };

        void hide();

        void show();
    }
}
