package me.hieu.kinder.warp.menu.button;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.ItemBuilder;
import me.hieu.kinder.util.TasksUtil;
import me.hieu.kinder.util.countdown.CountdownBuilder;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.menus.ConfirmMenu;
import me.hieu.kinder.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hieu
 * @date 12/09/2023
 */

public class WarpButton extends Button {

    private Warp warp;

    public WarpButton(Warp warp){
        this.warp = warp;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(CC.translate("&bCreator: &f" + Bukkit.getOfflinePlayer(warp.getCreator()).getName()));
        String location = "(" + warp.getLocation().getWorld().getName() + ") " + warp.getLocation().getBlockX() + ", " + warp.getLocation().getBlockY() + ", " + warp.getLocation().getBlockZ();
        lore.add(CC.translate("&bLocation: &f" + location));
        lore.add("");
        lore.add(CC.translate("&eClick to warp!"));
        if (player.getUniqueId().equals(warp.getCreator())){
            lore.add(CC.translate("&cRight Click to delete!"));
        }
        return new ItemBuilder(Material.PAPER).name(CC.translate("&a" + warp.getName())).lore(lore).build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasMetadata("warping")){
            TasksUtil.runTask(player::closeInventory);
            playFail(player);
            return;
        }
        if (clickType.isLeftClick()) {
            TasksUtil.runTask(player::closeInventory);
            player.setMetadata("warping", new FixedMetadataValue(Bueno.getInstance(), "warping"));
            Location originalLocation = player.getLocation();
            TasksUtil.runTaskTimer(new BukkitRunnable() {
                int count = 5;
                @Override
                public void run() {
                    if (count == 0){
                        player.teleport(warp.getLocation());
                        player.sendMessage(CC.translate("&aWarped!"));
                        playSuccess(player);
                        player.removeMetadata("warping", Bueno.getInstance());
                        cancel();
                        return;
                    }
                    player.sendMessage(CC.translate("&7Warping in %...").replaceAll("%", String.valueOf(count)));
                    if (warp == null){
                        player.sendMessage(CC.translate("&cThis warp was deleted."));
                        player.removeMetadata("warping", Bueno.getInstance());
                        playFail(player);
                        cancel();
                        return;
                    }
                    Location currentLocation = player.getLocation();
                    if (currentLocation.getX() != originalLocation.getX() || currentLocation.getY() != originalLocation.getY() || currentLocation.getZ() != originalLocation.getZ()){
                        player.sendMessage(CC.translate("&cWarp cancelled because you moved."));
                        player.removeMetadata("warping", Bueno.getInstance());
                        playFail(player);
                        cancel();
                        return;
                    }
                    count = count - 1;
                    Button.playNeutral(player);
                }
            }, 0L, 20L);
            return;
        }
        if (player.getUniqueId().equals(warp.getCreator())){
            if (clickType.isRightClick()){
                playNeutral(player);
                new ConfirmMenu("Delete Confirmation", data -> {
                    if (data) {
                        Bueno.getInstance().getWarpHandler().removeWarp(warp);
                        player.sendMessage(CC.translate("&cWarp deleted!"));
                    }
                }).openMenu(player);
            }
        }
    }
}
