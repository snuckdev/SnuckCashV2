package net.snuck.cash.manager;

import net.snuck.cash.Main;
import net.snuck.cash.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLManager {

    public static User get(UUID uuid) {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM snuck_cash WHERE uuid = ?");
            st.setString(1, uuid.toString());
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return new User(UUID.fromString(rs.getString("uuid")), rs.getInt("saldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void remove(UUID uuid) {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM snuck_cash WHERE uuid = ?");
            st.setString(1, uuid.toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean has(UUID uuid) {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("SELECT uuid FROM snuck_cash WHERE uuid = ?");
            st.setString(1, uuid.toString());
            ResultSet rs = st.executeQuery();
            st.close();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createTable(String table, String columns) {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("CREATE TABLE IF NOT EXISTS `" + table + "` (" + columns + ")");
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(UUID uuid, int saldo) {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO snuck_cash (uuid, saldo) VALUES (?, ?)");
            st.setString(1, uuid.toString());
            st.setInt(2, saldo);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeAll() {
        try {
            Connection con = Main.getiData().getConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM snuck_cash");
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
