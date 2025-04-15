package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand {

    @Command(name = "", desc = "broadcast your location")
    public void execute(@Sender CommandSender commandSender){
        Player player = (Player) commandSender;
        Bukkit.broadcastMessage(CC.translate("&b(Location) &a" + player.getName() + "&7 is at &e" + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ() + "&7."));
    }

}
