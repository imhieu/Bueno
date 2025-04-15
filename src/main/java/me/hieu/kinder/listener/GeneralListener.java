package me.hieu.kinder.listener;

import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.TasksUtil;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GeneralListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        String[] messages = {"&a%PLAYER% &bjust joined the server - glhf!",
                "&a%PLAYER% &bjust joined. Everyone, look busy!",
                "&a%PLAYER% &bjust joined. Can I get a heal?",
                "&a%PLAYER% &bjoined your party.",
                "&a%PLAYER% &bjoined. You must construct additional pylons.",
                "&bErmagherd. &a%PLAYER% &bis here.",
                "&bWelcome, &a%PLAYER%&b. Stay awhile and listen.",
                "&bWelcome, &a%PLAYER%&b. We were expecting you ( ͡° ͜ʖ ͡°)",
                "&bWelcome, &a%PLAYER%&b. We hope you brought pizza.",
                "&bWelcome &a%PLAYER%&b. Leave your weapons by the door.",
                "&bA wild &a%PLAYER% &bappeared.",
                "&bSwoooosh. &a%PLAYER% &bjust landed.",
                "&bBrace yourselves. &a%PLAYER% &bjust joined the server.",
                "&a%PLAYER% &bjust joined. Hide your bananas.",
                "&a%PLAYER% &bjust arrived. Seems OP - please nerf.",
                "&a%PLAYER% &bjust slid into the server.",
                "&bA &a%PLAYER% &bhas spawned in the server.",
                "&bBig &a%PLAYER% &bshowed up!",
                "&bWhere’s &a%PLAYER%&b? In the server!",
                "&a%PLAYER% &bhopped into the server. Kangaroo!!",
                "&a%PLAYER% &bjust showed up. Hold my beer."};

        int randomMessage = (int) (Math.random() * messages.length);
        String chosenMessage = messages[randomMessage];
        event.setJoinMessage(CC.translate(chosenMessage.replaceAll("%PLAYER%", event.getPlayer().getName())));

        TasksUtil.runTaskLater(() -> {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
        }, 1L);

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        event.setQuitMessage("");
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        TasksUtil.runTaskLater(() -> {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
        }, 1L);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        TasksUtil.runTaskLater(() -> {
            event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
        }, 1L);
    }

}
