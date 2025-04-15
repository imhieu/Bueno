package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Require;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.TasksUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand {

    @Command(name = "creeper", desc = "toggles creeper explosion")
    @Require("*")
    public void execute(@Sender CommandSender commandSender){
        Bueno.CREEPER_EXPLOSION = !Bueno.CREEPER_EXPLOSION;
        Bueno.getInstance().getConfig().set("SETTINGS.CREEPER-EXPLOSION", Bueno.CREEPER_EXPLOSION);
        Bueno.getInstance().saveConfig();
        Bueno.getInstance().reloadConfig();
        commandSender.sendMessage(CC.translate("&7Creeper explosions are now " + (Bueno.CREEPER_EXPLOSION ? "enabled" : "disabled") + "."));
    }

}
