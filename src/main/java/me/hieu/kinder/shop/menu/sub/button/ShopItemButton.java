package me.hieu.kinder.shop.menu.sub.button;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.shop.Shop;
import me.hieu.kinder.shop.TradeType;
import me.hieu.kinder.shop.menu.sub.TradeTypeMenu;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.ItemBuilder;
import me.hieu.kinder.util.TasksUtil;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.menus.ConfirmMenu;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class ShopItemButton extends Button {

    private final Shop item;
    private final TradeType type;

    public ShopItemButton(Shop item, TradeType type){
        this.item = item;
        this.type = type;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(CC.translate("&bAmount: &f" + item.getAmount()));
        lore.add(CC.translate("&bPrice: &2$&a" + item.getPrice()));
        lore.add("");
        lore.add(CC.translate("&bYour Balance: &2$&a" + Bueno.DECIMAL_FORMAT.format(profile.getBalance())));
        lore.add("");
        if (type == TradeType.BUYING) {
            if (profile.getBalance() >= item.getPrice()){
                lore.add(CC.translate("&eClick to purchase!"));
            } else {
                lore.add(CC.translate("&cInsufficient balance!"));
            }
        } else {
            if (checkItem(player, item.getItem(), item.getAmount())){
                lore.add(CC.translate("&eClick to sell!"));
            } else {
                lore.add(CC.translate("&cNot enough!"));
            }
        }
        return new ItemBuilder(item.getItem()).lore(lore).amount(item.getAmount()).build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        if (type == TradeType.BUYING){
            if (profile.getBalance() >= item.getPrice()){
                if (player.getInventory().firstEmpty() == -1){
                    player.sendMessage(CC.translate("&cYour inventory is full."));
                    playFail(player);
                } else {
                    profile.setBalance(profile.getBalance() - item.getPrice());
                    profile.save();
                    player.getInventory().addItem(new ItemBuilder(item.getItem().getType()).amount(item.getAmount()).build());
                    player.updateInventory();
                    playNeutral(player);
                    for (Player player1 : Bukkit.getOnlinePlayers()){
                        player1.sendMessage(CC.translate("&2(SHOP) &a" + player.getName() + "&7 bought " + item.getAmount() + "x &7" + WordUtils.capitalize(item.name().replaceAll("_", " ").toLowerCase()) + " for &2$&a" + item.getPrice() + "&7."));
                    }
                }
            } else {
                playFail(player);
            }
        } else {
            if (player.getInventory().contains(item.getItem().getType(), item.getAmount())){
                profile.setBalance(profile.getBalance() + item.getPrice());
                profile.save();
                removeItem(player, item.getItem(), item.getAmount());
                playNeutral(player);
                Bukkit.broadcastMessage(CC.translate("&2(SHOP) &a" + player.getName() + "&7 sold " + item.getAmount() + "x &7" + WordUtils.capitalize(item.name().replaceAll("_", " ").toLowerCase()) + " for &2$&a" + item.getPrice() + "&7."));
            } else {
                playFail(player);
            }
        }
    }

    public boolean checkItem(Player player, ItemStack item, int amount){
        for (ItemStack itemStack : player.getInventory()){
            if (itemStack != null){
                if (itemStack.getType().equals(item.getType()) && itemStack.getAmount() >= amount){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeItem(Player player, ItemStack it, int amount) {
        for (int a = 0; a < amount; a++) {
            for (ItemStack i : player.getInventory()) {
                if (i != null) {
                    if (i.getType() == it.getType() && (it.getDurability() == i.getDurability())) {
                        if (i.getAmount() == 1) {
                            player.getInventory().clear(player.getInventory().first(i));
                            break;
                        } else {
                            i.setAmount(i.getAmount() - 1);
                            break;
                        }
                    }
                }
            }
        }

    }

}
