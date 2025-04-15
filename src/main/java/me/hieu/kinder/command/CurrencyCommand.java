package me.hieu.kinder.command;

import me.hieu.kinder.util.CC;
import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrencyCommand {

    @Command(name = "", desc = "how the currency works")
    public void execute(@Sender CommandSender commandSender){
        Player player = (Player) commandSender;
        player.sendMessage(CC.translate("&3Currency &7is used throughout the server and in order to get some balance into your account," +
                " you would need to get a hold of some ores which are sellable in the shop via /shop or /sell. This" +
                " currency can be helpful in multiple ways such as allowing you to warp, vote, and buy" +
                " items from the shop."));
    }

}
