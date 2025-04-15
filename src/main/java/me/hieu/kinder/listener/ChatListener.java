package me.hieu.kinder.listener;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChatEmojiEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Location playerLocation = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()+2, player.getLocation().getZ());
        if (message.contains("<3")){
            event.setMessage(message.replaceAll("<3", CC.translate("&4❤&f")));
            Bukkit.getWorld(player.getWorld().getName()).spawnParticle(Particle.HEART, playerLocation, 10);
        } else if (message.contains(":cool:")){
            event.setMessage(message.replaceAll(":cool:", CC.translate("&b(-■_■)&f")));
        } else if (message.contains(":confused:")){
            event.setMessage(message.replaceAll(":confused:", CC.translate("&2(O.o)&f")));
        } else if (message.contains(":flippingtable:")){
            event.setMessage(message.replaceAll(":flippingtable:", CC.translate("&9(╯°□°)╯︵ ┻━┻&f")));
        } else if (message.contains(":happy:")){
            event.setMessage(message.replaceAll(":happy:", CC.translate("&a•‿•&f")));
        } else if (message.contains(":sad:")){
            event.setMessage(message.replaceAll(":sad:", CC.translate("&cʘ︵ʘ&f")));
        } else if (message.contains(":disbelief:")){
            event.setMessage(message.replaceAll(":disbelief:", CC.translate("&3☉_☉&f")));
        } else if (message.contains(":fire:")){
            event.setMessage(message.replaceAll(":fire:", CC.translate("&6\uD83D\uDD25&f")));
            Bukkit.getWorld(player.getWorld().getName()).spawnParticle(Particle.DRIPPING_LAVA, playerLocation, 10);
        }
    }

}
