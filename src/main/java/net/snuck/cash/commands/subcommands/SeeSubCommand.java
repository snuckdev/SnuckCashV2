package net.snuck.cash.commands.subcommands;

import net.snuck.cash.abstracts.SubCommand;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeeSubCommand extends SubCommand {

    public SeeSubCommand(String command) {
        super("ver", "§cUse: /" + command + " ver <jogador>.", "snuckcash.see.other", "see");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length >= 2) {

            Player target = Bukkit.getPlayerExact(args[1]);

            if(target == null) {
                sender.sendMessage("§cJogador offline ou inexistente.");
                return;
            }

            User user = DataManager.get(target.getUniqueId());

            sender.sendMessage("§eCash de " + target.getName() + ": §f" + user.getSaldo());

        } else {
            sender.sendMessage(getUsage());
        }

    }
}
