package me.hieu.kinder.vote;

import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.TasksUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author hieu
 * @date 17/09/2023
 */

@Getter @Setter
public class Vote {

    private int required;
    private List<UUID> vote;

    public Vote(int required){
        this.required = required;
        this.vote = new ArrayList<>();
        Bueno.getInstance().setVote(this);
        Bukkit.broadcastMessage(CC.translate("&aEnter /vote for day time. &7(" + this.vote.size() + "/" + required + ") (&2$&a0.99&7)"));
        TasksUtil.runTaskLater(() -> {
            if (Bueno.getInstance().getVote() != null){
                Bukkit.broadcastMessage(CC.translate("&cVote failed!"));
                Bueno.getInstance().setVote(null);
            }
        }, 30 * 20);
    }

    public static void addVote(UUID uuid){
        Bueno.getInstance().getVote().getVote().add(uuid);
    }

}
