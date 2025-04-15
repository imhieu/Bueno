package me.hieu.kinder.board;

import me.hieu.kinder.util.assemble.AssembleAdapter;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.PlayerDirection;
import org.apache.commons.lang3.time.DateUtils;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return CC.translate(Bueno.getInstance().getBoardHandler().getCurrentTitle());
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> list = new ArrayList<>();
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(player.getUniqueId());
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        String formattedDate = formatDate.format(DateUtils.addHours(new Date(), 7)).toUpperCase().toString();
        list.add("&a&l                     ");
        if (profile == null){
            list.add("&cYour profile is loading...");
            return list;
        }
        if (profile.isLoaded()) {
            list.add("");
            list.add("&fTime: " + formattedDate);
            list.add("&fBalance: &2$&a" + Bueno.DECIMAL_FORMAT.format(profile.getBalance()));
            list.add("&fCoordinates: &b");
            list.add("&e• &b" + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ() + " &f[" + PlayerDirection.getCardinalDirection(player) + "]");
            list.add("");
            list.add(Bueno.getInstance().getBoardHandler().getCurrentFooter());
        }
        list.add("&a&l                     ");
        return list;
    }
}
