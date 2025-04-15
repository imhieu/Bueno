package me.hieu.kinder.warp.menu;

import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.CC;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.pagination.PaginatedMenu;
import me.hieu.kinder.warp.Warp;
import me.hieu.kinder.warp.menu.button.WarpButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hieu
 * @date 12/09/2023
 */

public class WarpsMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player var1) {
        return CC.translate("&7Server warps");
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player var1) {
        Map<Integer, Button> buttonMap = new HashMap<>();
        for (Warp warp : Bueno.getInstance().getWarpHandler().getWarps()){
            buttonMap.put(buttonMap.size(), new WarpButton(warp));
        }
        return buttonMap;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 18;
    }
}
