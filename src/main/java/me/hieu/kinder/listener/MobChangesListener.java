package me.hieu.kinder.listener;

import me.hieu.kinder.Bueno;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

/**
 * @author hieu
 * @date 14/09/2023
 */

public class MobChangesListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (Bueno.CREEPER_EXPLOSION) return;
        if (event.getEntity() instanceof Creeper) {
            event.setCancelled(true);
        }
    }

}
