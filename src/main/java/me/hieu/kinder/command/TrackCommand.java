package me.hieu.kinder.command;

import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.track.TrackMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrackCommand {

    @Command(name = "", desc = "view the locations of players")
    public void execute(@Sender CommandSender commandSender){
        new TrackMenu().openMenu((Player) commandSender);
    }

}
