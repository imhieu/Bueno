package me.hieu.kinder.shop.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.shop.TradeType;
import me.hieu.kinder.shop.menu.sub.TradeTypeMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellCommand {

    @Command(name = "", desc = "view a shop to sell")
    public void execute(@Sender CommandSender commandSender){
        new TradeTypeMenu(TradeType.SELLING).openMenu((Player) commandSender);
    }

}
