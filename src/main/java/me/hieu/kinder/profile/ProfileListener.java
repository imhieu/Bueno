package me.hieu.kinder.profile;

import me.hieu.kinder.Bueno;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.UUID;

public class ProfileListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoinEvent(PlayerJoinEvent event) throws IOException {
        Profile profile = new Profile(event.getPlayer().getUniqueId());
        profile.setName(event.getPlayer().getName());
        Bueno.getInstance().getProfileHandler().addProfile(profile);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(uuid);
        profile.save();
        Bueno.getInstance().getProfileHandler().removeProfile(profile);
    }

}
