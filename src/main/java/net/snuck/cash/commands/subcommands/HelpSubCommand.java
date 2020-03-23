package net.snuck.cash.commands.subcommands;

import net.snuck.cash.abstracts.SubCommand;
import net.snuck.cash.manager.HelpMessage;
import org.bukkit.command.CommandSender;

public class HelpSubCommand extends SubCommand {

    public HelpSubCommand(String command) {
        super("ajuda", "§cUse: /" + command + " ajuda.", "", "help");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length >= 1) {
            HelpMessage.send(sender, sender.hasPermission("snuckcash.admin"));
        }

    }
}
