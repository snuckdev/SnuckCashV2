package net.snuck.cash.hooks;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import net.snuck.cash.Main;
import net.snuck.cash.manager.DataManager;
import org.bukkit.entity.Player;

public class MvDWPlaceholderAPI {

    public MvDWPlaceholderAPI() {

        PlaceholderAPI.registerPlaceholder(Main.getPlugin(), "snuckcash_cash", event -> {

            if(event.getPlayer() == null) return "";

            Player p = event.getPlayer();

            if(DataManager.has(p.getUniqueId())) {
                return String.valueOf(DataManager.get(p.getUniqueId()).getSaldo());
            }

            return "";

        });

    }

}
