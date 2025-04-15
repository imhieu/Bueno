package me.hieu.kinder.profile;

import com.mongodb.client.MongoCollection;
import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.Bueno;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ProfileHandler {

    private final MongoCollection<Document> collection;
    private List<Profile> profileList;

    public ProfileHandler(){
        collection = Bueno.getInstance().getMongoHandler().getDatabase().getCollection("Profiles");
        profileList = new ArrayList<>();
    }

    public Profile getProfileByName(String name) throws IOException {
        Player player = Bukkit.getPlayer(name);
        if (player != null) {
            for (Profile profile : profileList){
                if (profile.getName().equalsIgnoreCase(name)){
                    return profile;
                }
            }
        }
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);
        if (offlinePlayer.hasPlayedBefore()) {
            return new Profile(offlinePlayer.getUniqueId());
        }
        return null;
    }

    public Profile getProfileByUUID(UUID uuid){
        for (Profile profile : profileList){
            if (profile.getUniqueId().equals(uuid)){
                return profile;
            }
        }
        return null;
    }

    public void addProfile(Profile profile){
        this.profileList.add(profile);
    }

    public void removeProfile(Profile profile){
        this.profileList.remove(profile);
    }

}
