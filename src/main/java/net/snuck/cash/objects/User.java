package net.snuck.cash.objects;

import net.snuck.cash.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class User {

    private UUID uuid;
    private int saldo;

    public User(UUID uuid, int saldo) {
        this.uuid = uuid;
        this.saldo = saldo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void save() {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE snuck_cash SET saldo = ? WHERE uuid = ?");
            st.setInt(1, getSaldo());
            st.setString(2, getUuid().toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
