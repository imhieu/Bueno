package me.hieu.kinder.command.balance;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class PayCommand {

    @Command(name = "", desc = "pay a player", usage = "<player> <amount>")
    public void execute(@Sender CommandSender commandSender, Profile target, Double amount){
        Player player = (Player) commandSender;
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (amount <= 0){
            player.sendMessage(CC.translate("&cInsufficient amount!"));
            return;
        }
        if (target == profile){
            player.sendMessage(CC.translate("&cYou can't send money to yourself."));
            return;
        }
        if (profile.getBalance() < amount) {
            player.sendMessage(CC.translate("&cInsufficient balance!"));
            return;
        }
        profile.setBalance(profile.getBalance() - amount);
        profile.save();
        target.setBalance(target.getBalance() + amount);
        target.save();
        player.sendMessage(CC.translate("&7Sent &a" + target.getName() + " &2$&a" + Bueno.DECIMAL_FORMAT.format(amount) + "&7."));
        if (Bukkit.getPlayer(target.getUniqueId()) != null) Bukkit.getPlayer(target.getUniqueId()).sendMessage(CC.translate("&7Received &2$&a" + Bueno.DECIMAL_FORMAT.format(amount) + " &7from &a" + player.getName() + "&7."));
    }

}
