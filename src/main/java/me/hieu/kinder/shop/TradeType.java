package me.hieu.kinder.shop;

import lombok.Getter;
import me.hieu.kinder.util.CC;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author hieu
 * @date 13/09/2023
 */

@Getter
public enum TradeType {

    SELLING (CC.translate("&aSelling Shop"), CC.translate("&7Selling shop"), new ItemStack(Material.BLAZE_POWDER)),
    BUYING (CC.translate("&aBuying Shop"), CC.translate("&7Buying shop"), new ItemStack(Material.COMPASS));

    private final String name;
    private final String title;
    private final ItemStack display;

    TradeType(String name, String title, ItemStack display){
        this.name = name;
        this.title = title;
        this.display = display;
    }

}
