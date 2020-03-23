package net.snuck.cash.commands.subcommands;

import net.snuck.cash.abstracts.SubCommand;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSubCommand extends SubCommand {

    public SetSubCommand(String command) {
        super("setar", "§cUse: /" + command + " setar <jogador> <quantia>.", "snuckcash.admin", "set");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length >= 3) {

            Player target = Bukkit.getPlayerExact(args[1]);
            String amountString = args[2];

            if (target == null) {
                sender.sendMessage("§cJogador offline ou inexistente.");
                return;
            }

            if (!(isInt(amountString))) {
                sender.sendMessage("§cUtilize somente números válidos.");
                return;
            }

            int amount = Integer.parseInt(amountString);

            User user = DataManager.get(target.getUniqueId());
            user.setSaldo(amount);
            sender.sendMessage("§aVocê definiu o balanço de §f" + target.getName() + " §apara §f" + amountString + "§a.");
        } else {
            sender.sendMessage(getUsage());
        }

    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
