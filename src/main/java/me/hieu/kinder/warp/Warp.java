package me.hieu.kinder.warp;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.util.LocationUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

/**
 * @author hieu
 * @date 12/09/2023
 */

@Getter
public class Warp {

    private final String name;
    private final Location location;
    private final UUID creator;

    public Warp(String name, Location location, UUID creator){
        this.name = name;
        this.location = location;
        this.creator = creator;
        Bueno.getInstance().getWarpHandler().getWarps().add(this);
    }

    public void save(){
        Document document = new Document();
        document.append("name", name);
        document.append("location", LocationUtil.convertLocationToString(location));
        document.append("creator", creator.toString());
        Bson filter = Filters.eq("name", name);
        Bueno.getInstance().getWarpHandler().getCollection().replaceOne(filter, document, new ReplaceOptions().upsert(true));
    }

    public void delete(){
        Bson filter = Filters.eq("name", name);
        Bueno.getInstance().getWarpHandler().getCollection().deleteOne(filter);
        Bueno.getInstance().getWarpHandler().getWarps().remove(this);
    }

}
