package me.hieu.kinder.util;

import me.hieu.kinder.Bueno;
import org.bukkit.scheduler.BukkitRunnable;

public class TasksUtil {

    public static void runTaskAsync(Runnable runnable) {
        Bueno.getInstance().getServer().getScheduler().runTaskAsynchronously(Bueno.getInstance(), runnable);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        Bueno.getInstance().getServer().getScheduler().runTaskLater(Bueno.getInstance(), runnable, delay);
    }

    public static void runTaskTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(Bueno.getInstance(), delay, timer);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long timer) {
        Bueno.getInstance().getServer().getScheduler().runTaskTimer(Bueno.getInstance(), runnable, delay, timer);
    }

    public static void runTask(Runnable runnable) {
        Bueno.getInstance().getServer().getScheduler().runTask(Bueno.getInstance(), runnable);
    }

}
