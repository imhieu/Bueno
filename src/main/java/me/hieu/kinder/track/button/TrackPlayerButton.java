package me.hieu.kinder.track.button;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.ItemBuilder;
import me.hieu.kinder.util.PlayerDirection;
import me.hieu.libraries.menu.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class TrackPlayerButton extends Button {

    private Player player1;

    public TrackPlayerButton(Player player1){
        this.player1 = player1;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player1.getUniqueId());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(CC.translate("&bBalance: &2$&a" + Bueno.DECIMAL_FORMAT.format(profile.getBalance())));
        lore.add(CC.translate("&bLocation: &f" + player1.getLocation().getBlockX() + ", " + player1.getLocation().getBlockY() + ", " + player1.getLocation().getBlockZ() + " [" + PlayerDirection.getCardinalDirection(player1) + "]"));
        lore.add(CC.translate("&bHealth: &f" + Bueno.DECIMAL_FORMAT.format(player1.getHealth()/2) + "&4❤"));
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player1.getName()));
        skullMeta.setLore(lore);
        skullMeta.setDisplayName(CC.translate("&a" + player1.getName()));
        skull.setItemMeta(skullMeta);
        return skull;
    }

    @Override
    public boolean shouldUpdate(Player player, ClickType clickType) {
        return true;
    }

}
