package net.snuck.cash;

import net.snuck.cash.commands.CashCommand;
import net.snuck.cash.database.IData;
import net.snuck.cash.database.MySQL;
import net.snuck.cash.database.SQLite;
import net.snuck.cash.hooks.MvDWPlaceholderAPI;
import net.snuck.cash.hooks.PlaceholderAPI;
import net.snuck.cash.listener.PlayerJoinListener;
import net.snuck.cash.listener.PlayerLeftListener;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.manager.SQLManager;
import net.snuck.cash.objects.User;
import net.snuck.cash.tasks.SaveTask;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static IData iData;
    private static HashMap<UUID, User> users = new HashMap<>();

    @Override
    public void onEnable() {
        init();
    }

    private void init() {
        saveDefaultConfig();
        setupDatabase();
        DataManager.load();
        getCommand("cash").setExecutor(new CashCommand("cash"));
        registerListener(new PlayerJoinListener());
        registerListener(new PlayerLeftListener());
        saveTask();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)  {
            System.out.println("PlaceholderAPI encontrado");
            new PlaceholderAPI().register();
        }
        if(Bukkit.getPluginManager().getPlugin("MVdWPlaceholderAPI") != null) {
            System.out.println("MVdWPlaceholderAPI encontrado");
            new MvDWPlaceholderAPI();
        }
    }

    private void setupDatabase() {
        String type = getConfig().getString("Storage.Type").toLowerCase();

        if (type.equals("mysql")) {
            String host = getConfig().getString("Storage.Host");
            int port = getConfig().getInt("Storage.Port");
            String user = getConfig().getString("Storage.User");
            String password = getConfig().getString("Storage.Password");
            String database = getConfig().getString("Storage.Database");

            iData = new MySQL(host, port, user, password, database);

        } else if(type.equals("sqlite")) {
            iData = new SQLite();
        }
        iData.open();
        SQLManager.createTable("snuck_cash", "uuid varchar(36), saldo int");
    }

    @Override
    public void onDisable() {
        DataManager.save();
        getiData().close();
    }

    public static HashMap<UUID, User> getUsers() {
        return users;
    }

    public static IData getiData() {
        return iData;
    }

    public static JavaPlugin getPlugin() {
        return getPlugin(Main.class);
    }

    private void registerListener(Listener l) {
        Bukkit.getPluginManager().registerEvents(l, this);
    }

    private void saveTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), new SaveTask(), 20 * 60 * 30, 20 * 60 * 30);
    }
}
