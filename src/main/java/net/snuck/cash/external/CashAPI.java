package net.snuck.cash.external;

import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;

import java.util.UUID;

public class CashAPI {

    public static User get(UUID uuid) {
        return DataManager.get(uuid);
    }

    public static void remove(UUID uuid) {
        DataManager.remove(uuid);
    }

    public static boolean has(UUID uuid) {
        return DataManager.has(uuid);
    }

    public static void insert(UUID uuid, int saldo) {
        DataManager.insert(uuid, saldo);
    }

}
