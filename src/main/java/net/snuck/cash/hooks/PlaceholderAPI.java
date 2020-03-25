package net.snuck.cash.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;
import net.snuck.cash.utils.Formatter;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public String onPlaceholderRequest(Player p, String params) {

        if(p == null) return "";

        User user = DataManager.get(p.getUniqueId());

        if(user == null) return "";

        if(params.equals("cash")) {
            return Formatter.format(user.getSaldo());
        }
        return "";
    }

    @Override
    public String getIdentifier() {
        return "snuckcash";
    }

    @Override
    public String getAuthor() {
        return "Snuck";
    }

    @Override
    public String getVersion() {
        return "1.2";
    }
}
