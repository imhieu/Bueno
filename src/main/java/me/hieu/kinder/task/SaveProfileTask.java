package me.hieu.kinder.task;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import org.bukkit.Bukkit;

public class SaveProfileTask {

    public SaveProfileTask(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bueno.getInstance(), () -> {
            Long started = System.currentTimeMillis();
            for (Profile profile : Bueno.getInstance().getProfileHandler().getProfileList()) {
                profile.save();
            }
            Long ended = System.currentTimeMillis();
            String context = Bueno.getInstance().getProfileHandler().getProfileList().size() > 1 ? "s" : "";
            Bukkit.broadcastMessage(CC.translate("&a&lSuccessfully&a saved a total of &a&l" + Bueno.getInstance().getProfileHandler().getProfileList().size() + "&a profile" + context + " within &a&l" + (ended - started) + "&ams."));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            }, 0L, (120 * 20));
    }

}
