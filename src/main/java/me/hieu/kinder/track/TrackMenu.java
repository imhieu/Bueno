package me.hieu.kinder.track;

import me.hieu.kinder.track.button.TrackPlayerButton;
import me.hieu.kinder.util.CC;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.pagination.PaginatedMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TrackMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&7Track available players");
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();
        for (Player player1 : Bukkit.getOnlinePlayers()){
            buttonMap.put(buttonMap.size(), new TrackPlayerButton(player1));
        }
        return buttonMap;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 9;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }
}
