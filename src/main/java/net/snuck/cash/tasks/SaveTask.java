package net.snuck.cash.tasks;

import net.snuck.cash.Main;

public class SaveTask implements Runnable {

    @Override
    public void run() {
        Main.getUsers().forEach((key, user) -> {
            user.save();
        });
        System.out.println("[SnuckCash] [DEBUG] AutoSave executado.");
    }
}
