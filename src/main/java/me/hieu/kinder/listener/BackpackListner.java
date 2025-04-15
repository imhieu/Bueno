package me.hieu.kinder.listener;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author hieu
 * @date 14/09/2023
 */

public class BackpackListner implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity();
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (profile.getBackpack().isEmpty()) return;
        for (ItemStack itemStack : profile.getBackpack().getContents()){
            if (itemStack == null) continue;
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        }
        profile.getBackpack().clear();
        profile.save();
    }

}
