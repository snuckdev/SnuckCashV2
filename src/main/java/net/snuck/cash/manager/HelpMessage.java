package net.snuck.cash.manager;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpMessage {

    public static void send(CommandSender sender, boolean isStaff) {

        List<String> messages = new ArrayList<>();

        messages.add("§e/cash §f- mostra seu saldo.");
        messages.add("§e/cash ver <jogador> §f- mostra o saldo de um jogador.");

        if(isStaff) {
            messages.add("§e/cash setar <jogador> <quantia> §f- define o saldo de um jogador.");
            messages.add("§e/cash remover <jogador> <quantia> §f- remove uma quantia do saldo de um jogador.");
            messages.add("§e/cash adicionar <jogador> <quantia> §f- adiciona uma quantia ao saldo de um jogador.");
        }

        messages.forEach(sender::sendMessage);

    }

}
