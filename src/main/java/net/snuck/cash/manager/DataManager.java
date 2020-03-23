package net.snuck.cash.manager;

import net.snuck.cash.Main;
import net.snuck.cash.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DataManager {

    public static boolean has(UUID uuid) {
        return Main.getUsers().containsKey(uuid);
    }

    public static void remove(UUID uuid) {
        Main.getUsers().remove(uuid);
    }

    public static void insert(UUID uuid, int saldo) {
        Main.getUsers().put(uuid, new User(uuid, saldo));
    }

    public static User get(UUID uuid) {
        return Main.getUsers().get(uuid);
    }

    public static void load() {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM snuck_cash");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                insert(UUID.fromString(rs.getString("uuid")), rs.getInt("saldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        Main.getUsers().forEach((key, user) -> user.save());
    }

}
