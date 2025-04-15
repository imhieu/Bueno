package me.hieu.kinder.warp;

import com.mongodb.client.MongoCollection;
import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.LocationUtil;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * @author hieu
 * @date 12/09/2023
 */

@Getter
public class WarpHandler {

    private final MongoCollection<Document> collection;
    private ArrayList<Warp> warps;

    public WarpHandler(){
        collection = Bueno.getInstance().getMongoHandler().getDatabase().getCollection("Warps");
        warps = new ArrayList<>();
    }

    public void removeWarp(Warp warp){
        warp.delete();
        warps.remove(warp);
    }

    public void loadAllWarps(){
        for (Document document : collection.find()){
            String name = document.getString("name");
            Location location = LocationUtil.convertStringToLocation(document.getString("location"));
            UUID creator = UUID.fromString(document.getString("creator"));
            new Warp(name, location, creator);
        }
    }

}
