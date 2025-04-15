package me.hieu.kinder.shop.menu.sub;

import me.hieu.kinder.shop.Shop;
import me.hieu.kinder.shop.TradeType;
import me.hieu.kinder.shop.menu.ShopMenu;
import me.hieu.kinder.shop.menu.sub.button.ShopItemButton;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.button.BackButton;
import me.hieu.libraries.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class TradeTypeMenu extends PaginatedMenu {

    private final TradeType type;

    public TradeTypeMenu(TradeType type){
        this.type = type;
    }

    @Override
    public String getPrePaginatedTitle(Player var1) {
        return type.getTitle();
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player var1) {
        Map<Integer, Button> buttonMap = new HashMap<>();
        for (Shop shop : Shop.values()){
            if (shop.getType() == type){
                buttonMap.put(buttonMap.size(), new ShopItemButton(shop, type));
            }
        }
        return buttonMap;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 45;
    }
}
