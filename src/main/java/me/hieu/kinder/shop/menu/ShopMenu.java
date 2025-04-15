package me.hieu.kinder.shop.menu;

import me.hieu.kinder.shop.TradeType;
import me.hieu.kinder.shop.menu.button.TradeTypeButton;
import me.hieu.kinder.util.CC;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class ShopMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.translate("&7Server shop");
    }

    @Override
    public Map<Integer, Button> getButtons(Player var1) {
        Map<Integer, Button> buttonMap = new HashMap<>();
        buttonMap.put(11, new TradeTypeButton(TradeType.BUYING));
        buttonMap.put(15, new TradeTypeButton(TradeType.SELLING));
        return buttonMap;
    }

    @Override
    public int size(Map<Integer, Button> buttons) {
        return 27;
    }
}
