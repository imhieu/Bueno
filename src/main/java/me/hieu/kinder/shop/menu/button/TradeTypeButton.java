package me.hieu.kinder.shop.menu.button;

import me.hieu.kinder.shop.TradeType;
import me.hieu.kinder.shop.menu.sub.TradeTypeMenu;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.ItemBuilder;
import me.hieu.kinder.util.TasksUtil;
import me.hieu.libraries.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class TradeTypeButton extends Button {

    private final TradeType type;

    public TradeTypeButton(TradeType type){
        this.type = type;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(CC.translate("&fView a wide range of " + (type == TradeType.BUYING ? "items" : "ores")));
        if (type == TradeType.BUYING){
            lore.add(CC.translate("&fto buy such as food, building"));
            lore.add(CC.translate("&fblocks, and decorative pieces."));
        } else {
            lore.add(CC.translate("&fto sell such as diamonds and"));
            lore.add(CC.translate("&femeralds."));
        }
        lore.add("");
        lore.add(CC.translate("&eClick to open!"));
        return new ItemBuilder(type.getDisplay()).name(type.getName()).lore(lore).build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (type == TradeType.BUYING){
            new TradeTypeMenu(TradeType.BUYING).openMenu(player);
        } else {
            new TradeTypeMenu(TradeType.SELLING).openMenu(player);
        }
        playNeutral(player);
    }
}
