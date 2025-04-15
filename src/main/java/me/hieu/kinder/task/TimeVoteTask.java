package me.hieu.kinder.task;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.vote.Vote;
import org.bukkit.Bukkit;

/**
 * @author hieu
 * @date 17/09/2023
 */

public class TimeVoteTask {

    public TimeVoteTask(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bueno.getInstance(), () -> {
            if (Bueno.getInstance().getVote() == null){
                //Night = 13000L
                if (Bukkit.getServer().getWorld("world").getTime() >= 12000L){
                    int required;
                    int online = Bukkit.getOnlinePlayers().size();
                    if (online == 0){
                        return;
                    } else if (online == 1){
                        required = 1;
                    } else {
                        if (online % 2 == 0){
                            required = online / 2;
                        } else {
                            required = (online - 1) / 2;
                        }
                    }
                    Vote vote = new Vote(required);
                }
            }
        }, 0L, (30 * 20));
    }

}
