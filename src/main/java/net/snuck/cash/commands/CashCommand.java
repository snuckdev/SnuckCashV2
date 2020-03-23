package net.snuck.cash.commands;

import net.snuck.cash.abstracts.SubCommand;
import net.snuck.cash.commands.subcommands.*;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.manager.HelpMessage;
import net.snuck.cash.objects.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CashCommand implements CommandExecutor {

    private List<SubCommand> subCommands;

    public CashCommand(String command) {
        this.subCommands = new ArrayList<>();
        this.subCommands.add(new SetSubCommand(command));
        this.subCommands.add(new AddSubCommand(command));
        this.subCommands.add(new RemoveSubCommand(command));
        this.subCommands.add(new SeeSubCommand(command));
        this.subCommands.add(new HelpSubCommand(command));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                String arg = args[0].toLowerCase();
                if (!this.subCommands.isEmpty()) {
                    for (SubCommand subCommand : this.subCommands) {
                        if ((arg.equalsIgnoreCase(subCommand.getName().toLowerCase())) || (subCommand.getAlias().contains(arg))) {
                            if ((sender.hasPermission(subCommand.getPermission())) || (subCommand.getPermission().isEmpty())) {
                                subCommand.execute(sender, args);
                            } else {
                                sender.sendMessage("§cVocê não possui permissão para executar este comando.");
                            }
                            return false;
                        }
                    }
                }
            } else {
                HelpMessage.send(sender, true);
            }

            return false;
        }

        Player p = (Player) sender;

        if (args.length >= 1) {
            String arg = args[0].toLowerCase();
            if (!this.subCommands.isEmpty()) {
                for (SubCommand subCommand : this.subCommands) {
                    if ((arg.equalsIgnoreCase(subCommand.getName().toLowerCase())) || (subCommand.getAlias().contains(arg))) {
                        if ((sender.hasPermission(subCommand.getPermission())) || (subCommand.getPermission().isEmpty())) {
                            subCommand.execute(sender, args);
                        } else {
                            sender.sendMessage("§cVocê não possui permissão para executar este comando.");
                        }
                        return false;
                    }
                }
            }
        } else {
            User user = DataManager.get(p.getUniqueId());
            p.sendMessage("§eCash: §f" + user.getSaldo());
        }
        return false;
    }
}
