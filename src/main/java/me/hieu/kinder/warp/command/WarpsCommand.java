package me.hieu.kinder.warp.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.warp.menu.WarpsMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author hieu
 * @date 12/09/2023
 */

public class WarpsCommand {

    @Command(name = "", desc = "view all warps")
    public void execute(@Sender CommandSender commandSender){
        if (Bueno.getInstance().getWarpHandler().getWarps().isEmpty()){
            commandSender.sendMessage(CC.translate("&cThere are no warps!"));
            return;
        }
        new WarpsMenu().openMenu((Player) commandSender);
    }

}
