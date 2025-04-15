package me.hieu.kinder.command.balance;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.OptArg;
import me.hieu.libraries.drink.annotation.Require;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class BalanceCommand {

    @Command(name = "", desc = "view the balance of a player", usage = "<player>")
    public void execute(@Sender CommandSender commandSender, @OptArg Profile target){
        Player player = (Player) commandSender;
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (target == null){
            target = profile;
        }
        player.sendMessage(CC.translate("&7" + target.getName() + "'s Balance: &2$&a" + Bueno.DECIMAL_FORMAT.format(target.getBalance()) + "&7."));
    }

    @Command(name = "set", desc = "set the balance of a player", usage = "<player> <amount>")
    @Require("*")
    public void set(@Sender CommandSender commandSender, Profile target, double amount){
        target.setBalance(amount);
        target.save();
        commandSender.sendMessage(CC.translate("&7" + target.getName() + "'s balance is now &2$&a" + Bueno.DECIMAL_FORMAT.format(target.getBalance()) + "&7."));
    }

}
