package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.OptArg;
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

public class StatisticsCommand {

    @Command(name = "", desc = "view the statistics of a player", usage = "<player>")
    public void execute(@Sender CommandSender commandSender, @OptArg Profile target){
        Player player = (Player) commandSender;
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (target == null) {
            target = profile;
        }
        commandSender.sendMessage(CC.translate("&a" + target.getName() + "'s Statistics"));
        commandSender.sendMessage(CC.translate(" &7Netherite: " + target.getOresMap().get("netherite")));
        commandSender.sendMessage(CC.translate(" &7Diamonds: " + target.getOresMap().get("diamond")));
        commandSender.sendMessage(CC.translate(" &7Emeralds: " + target.getOresMap().get("emerald")));
        commandSender.sendMessage(CC.translate(" &7Redstones: " + target.getOresMap().get("redstone")));
        commandSender.sendMessage(CC.translate(" &7Lapis: " + target.getOresMap().get("lapis")));
        commandSender.sendMessage(CC.translate(" &7Golds: " + target.getOresMap().get("gold")));
        commandSender.sendMessage(CC.translate(" &7Irons: " + target.getOresMap().get("iron")));
        commandSender.sendMessage(CC.translate(" &7Coals: " + target.getOresMap().get("coal")));
    }

}
