package net.snuck.cash.commands.subcommands;

import net.snuck.cash.abstracts.SubCommand;
import net.snuck.cash.manager.DataManager;
import net.snuck.cash.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddSubCommand extends SubCommand {

    public AddSubCommand(String command) {
        super("add", "§cUse: /" + command + " add <jogador> <quantia>.", "snuckcash.admin", "adicionar");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length >= 3) {

            Player target = Bukkit.getPlayerExact(args[1]);
            String amountString = args[2];

            if(target == null) {
                sender.sendMessage("§cJogador offline ou inexistente.");
                return;
            }

            if (!(isInt(amountString))) {
                sender.sendMessage("§cUtilize somente números válidos.");
                return;
            }

            int amount = Integer.parseInt(amountString);


            User user = DataManager.get(target.getUniqueId());
            user.setSaldo(user.getSaldo() + amount);
            sender.sendMessage("§aVocê adicionou §f" + amountString + " §ade cash ao saldo de §f" + target.getName() + "§a.");
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
