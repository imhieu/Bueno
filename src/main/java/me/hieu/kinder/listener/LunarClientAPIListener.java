package me.hieu.kinder.listener;

import me.hieu.kinder.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LunarClientAPIListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()){
            player.sendTitle(CC.translate("&a&lHELLO " + player.getName()), CC.translate("&fWelcome back to the server!"));
        } else {
            player.sendTitle(CC.translate("&a&lHELLO " + player.getName()), CC.translate("&fWelcome to the server!"));
        }
    }

}
