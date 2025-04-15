package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.TasksUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author hieu
 * @date 14/09/2023
 */

public class BackpackCommand {

    @Command(name = "", desc = "view your own backpack")
    public void execute(@Sender CommandSender commandSender){
        Player player = (Player) commandSender;
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        TasksUtil.runTaskLater(() -> {
            player.openInventory(profile.getBackpack());
        }, 1L);
    }

}
