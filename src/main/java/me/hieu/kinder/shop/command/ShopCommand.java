package me.hieu.kinder.shop.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.shop.menu.ShopMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand {

    @Command(name = "", desc = "view a shop to buy/sell")
    public void execute(@Sender CommandSender commandSender){
        new ShopMenu().openMenu((Player) commandSender);
    }

}
