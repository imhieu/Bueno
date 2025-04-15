package me.hieu.kinder.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.hieu.kinder.Bueno;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Author: Le Thanh Hieu
 * Date: 01/10/2024
 */

@Getter
public class MongoHandler {

    private final MongoClient client;
    private final MongoDatabase database;

    public MongoHandler(){
        FileConfiguration config = Bueno.getInstance().getConfig();
        client = new MongoClient(new MongoClientURI(config.getString("database.mongo.uri")));
        database = client.getDatabase(config.getString("database.mongo.database"));
    }

}
