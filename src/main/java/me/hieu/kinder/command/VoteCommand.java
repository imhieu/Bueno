package me.hieu.kinder.command;

import me.hieu.kinder.util.TasksUtil;
import me.hieu.libraries.drink.annotation.Command;
import me.hieu.libraries.drink.annotation.Sender;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.countdown.CountdownBuilder;
import me.hieu.libraries.menu.Button;
import me.hieu.kinder.vote.Vote;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hieu
 * @date 17/09/2023
 */

public class VoteCommand {

    @Command(name = "", desc = "vote for sunrise")
    public void execute(@Sender CommandSender commandSender){
        Player player = (Player) commandSender;
        if (Bueno.getInstance().getVote() == null){
            commandSender.sendMessage(CC.translate("&cThere is currently no vote running."));
            return;
        }
        Vote vote = Bueno.getInstance().getVote();
        if (vote.getVote().size() == vote.getRequired()){
            player.sendMessage(CC.translate("&cThe vote is already full."));
            return;
        }
        if (vote.getVote().contains(player.getUniqueId())){
            player.sendMessage(CC.translate("&cYou've already voted."));
            return;
        }
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (profile.getBalance() - 0.99 < 0){
            player.sendMessage(CC.translate("&cInsufficient balance!"));
            return;
        }
        profile.setBalance(profile.getBalance() - 0.99);
        profile.save();
        Vote.addVote(player.getUniqueId());
        Bukkit.broadcastMessage(CC.translate("&a" + player.getName() + " voted. &7(" + vote.getVote().size() + "/" + vote.getRequired() + ")"));
        if (vote.getVote().size() == vote.getRequired()){
            TasksUtil.runTaskTimer(new BukkitRunnable() {
                int count = 5;
                @Override
                public void run() {
                    if (count == 0){
                        Bueno.getInstance().setVote(null);
                        Bukkit.broadcastMessage(CC.translate("&aMorning!"));
                        Bukkit.getServer().getWorld("world").setTime(1000L);
                        Button.playSuccess(player);
                        cancel();
                        return;
                    }
                    for (Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(CC.translate("&7Sunrise in %...").replaceAll("%", String.valueOf(count)));
                        Button.playNeutral(player);
                    }
                    count = count - 1;
                }
            }, 0L, 20L);
        }
    }

}
