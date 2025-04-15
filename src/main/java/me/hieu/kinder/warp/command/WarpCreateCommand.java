package me.hieu.kinder.warp.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.libraries.drink.annotation.Text;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author hieu
 * @date 12/09/2023
 */

public class WarpCreateCommand {

    @Command(name = "create", desc = "create a warp", usage = "<name>")
    public void execute(@Sender CommandSender commandSender, String name){
        Player player = (Player) commandSender;
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (profile.getBalance() < 4.99) {
            player.sendMessage(CC.translate("&cYou need at least $4.99 to create a warp."));
            return;
        }
        for (Warp warp : Bueno.getInstance().getWarpHandler().getWarps()){
            if (warp.getName().equals(name)){
                commandSender.sendMessage(CC.translate("&cThere's already a warp with that name."));
                return;
            }
        }
        Warp warp = new Warp(name, player.getLocation(), player.getUniqueId());
        warp.save();
        profile.setBalance(profile.getBalance() - 4.99);
        profile.save();
        Bukkit.broadcastMessage(CC.translate("&3(WARP) &a" + commandSender.getName() + " &7has created a new warp!\n&o/warps to view all warps."));
    }

}
