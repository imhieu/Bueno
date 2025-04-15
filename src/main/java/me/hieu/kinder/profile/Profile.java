package me.hieu.kinder.profile;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.ItemSerializationUtil;
import me.hieu.kinder.util.LocationUtil;
import me.hieu.libraries.util.CC;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    private UUID uniqueId;
    private String name;
    private Double balance;
    private HashMap<String, Integer> oresMap;
    private Inventory backpack;
    private boolean loaded;

    public Profile(UUID uniqueId) throws IOException {
        this.uniqueId = uniqueId;
        name = Bukkit.getOfflinePlayer(uniqueId).getName();
        balance = 0.00;
        oresMap = new HashMap<>();
        oresMap.put("netherite", 0);
        oresMap.put("diamond", 0);
        oresMap.put("emerald", 0);
        oresMap.put("redstone", 0);
        oresMap.put("lapis", 0);
        oresMap.put("gold", 0);
        oresMap.put("iron", 0);
        oresMap.put("coal", 0);
        backpack = Bukkit.createInventory(null, 54);
        loaded = false;
        try {
            load();
        } catch (Exception e){
            Player player = Bukkit.getPlayer(uniqueId);
            if (player == null) return;
            player.kickPlayer(CC.translate("&cThere was an error loading your profile. Contact an administrator."));
        }
    }

    public Profile(String name) throws IOException {
        this.uniqueId = Bukkit.getOfflinePlayer(name).getUniqueId();
        this.name = name;
        this.balance = 0.00;
        oresMap = new HashMap<>();
        oresMap.put("netherite", 0);
        oresMap.put("diamond", 0);
        oresMap.put("emerald", 0);
        oresMap.put("redstone", 0);
        oresMap.put("lapis", 0);
        oresMap.put("gold", 0);
        oresMap.put("iron", 0);
        oresMap.put("coal", 0);
        this.backpack = Bukkit.createInventory(null, 54);
        this.loaded = false;
        try {
            load();
        } catch (Exception e){
            Player player = Bukkit.getPlayer(name);
            if (player == null) return;
            player.kickPlayer(CC.translate("&cThere was an error loading your profile. Contact an administrator."));
        }
    }

    public void load() throws IOException {
        Bson filter = Filters.eq("uniqueId", uniqueId.toString());
        Document document = Bueno.getInstance().getProfileHandler().getCollection().find(filter).first();
        if (document != null){
            name = document.getString("name");
            balance = document.getDouble("balance");
            String[] oresSplitUp = document.getString("ores").split(":");
            oresMap.put("netherite", Integer.parseInt(oresSplitUp[0]));
            oresMap.put("diamond", Integer.parseInt(oresSplitUp[1]));
            oresMap.put("emerald", Integer.parseInt(oresSplitUp[2]));
            oresMap.put("redstone", Integer.parseInt(oresSplitUp[3]));
            oresMap.put("lapis", Integer.parseInt(oresSplitUp[4]));
            oresMap.put("gold", Integer.parseInt(oresSplitUp[5]));
            oresMap.put("iron", Integer.parseInt(oresSplitUp[6]));
            oresMap.put("coal", Integer.parseInt(oresSplitUp[7]));
            backpack = ItemSerializationUtil.fromBase64(document.getString("backpack"));
        }
        loaded = true;
    }

    public void save(){
        Document document = new Document();
        document.append("uniqueId", uniqueId.toString());
        document.append("name", name);
        document.append("balance", balance);
        String oresString = oresMap.get("netherite") + ":" + oresMap.get("diamond") + ":" + oresMap.get("emerald") + ":" + oresMap.get("redstone") + ":" + oresMap.get("lapis") + ":" + oresMap.get("gold") + ":" + oresMap.get("iron") + ":" + oresMap.get("coal") + ":";
        document.append("ores", oresString);
        document.append("backpack", ItemSerializationUtil.toBase64(backpack));
        Bson filter = Filters.eq("uniqueId", uniqueId.toString());
        Bueno.getInstance().getProfileHandler().getCollection().replaceOne(filter, document, new ReplaceOptions().upsert(true));
    }

}
