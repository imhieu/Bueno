package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.OptArg;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.TimeUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UptimeCommand {

    @Command(name = "", desc = "view the server's uptime")
    public void execute(@Sender CommandSender commandSender){
        commandSender.sendMessage(CC.translate("&7The server has been up for " + TimeUtil.convertLongToString(System.currentTimeMillis() - Bueno.UP_TIME) + "."));
    }

}
