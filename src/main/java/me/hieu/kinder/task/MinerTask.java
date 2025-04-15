package me.hieu.kinder.task;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MinerTask {

    private final int Y_LEVEL = 20;

    public MinerTask(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bueno.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.getLocation().getBlockY() < Y_LEVEL){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 20 * 5, 1));
                    Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
                    if (profile.getOresMap().get("diamond") > 100) player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 5, 1));
                    if (profile.getOresMap().get("diamond") > 1000) player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20 * 5, 0));
                }
            }
            }, 0L, 20L);
    }

}
