package net.snuck.cash.listener;

import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerLeftListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        User user = DataManager.get(uuid);

        user.save();
        DataManager.remove(uuid);

    }

}
