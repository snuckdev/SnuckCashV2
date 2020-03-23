package net.snuck.cash.listener;

import net.snuck.cash.manager.DataManager;
import net.snuck.cash.manager.SQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        if(!(DataManager.has(uuid))) {
            DataManager.insert(uuid, 0);
        }

        if(!(SQLManager.has(uuid))) {
            SQLManager.insert(uuid, 0);
        }
    }

}
