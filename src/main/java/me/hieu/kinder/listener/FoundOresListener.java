package me.hieu.kinder.listener;

import com.google.common.collect.ImmutableSet;
import me.hieu.kinder.Bueno;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Set;

public class FoundOresListener implements Listener {

    public static final Set<BlockFace> CHECK_FACES = ImmutableSet.of(
            BlockFace.NORTH,
            BlockFace.SOUTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.NORTH_EAST,
            BlockFace.NORTH_WEST,
            BlockFace.SOUTH_EAST,
            BlockFace.SOUTH_WEST,
            BlockFace.UP,
            BlockFace.DOWN);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        switch (event.getBlock().getType()){
            case NETHERITE_BLOCK:
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case EMERALD_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case IRON_ORE:
            case DEEPSLATE_IRON_ORE:
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
                event.getBlock().setMetadata("OrePlaced", new FixedMetadataValue(Bueno.getInstance(), true));
                break;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Profile profile = Bueno.getInstance().getProfileHandler().getProfileByUUID(event.getPlayer().getUniqueId());
        if (!event.getBlock().hasMetadata("OrePlaced")){
            switch (event.getBlock().getType()){
                case NETHERITE_BLOCK:
                    int oresCountNetherite = countRelativeNetherite(event.getBlock());
                    profile.getOresMap().put("netherite", profile.getOresMap().get("netherite") + 1);
                    Bukkit.broadcastMessage(CC.translate("&f[FN] &d" + event.getPlayer().getName() + " found " + oresCountNetherite + " netherite" + (oresCountNetherite == 1 ? "" : "s") + "."));
                    profile.save();
                    break;
                case DIAMOND_ORE:
                case DEEPSLATE_DIAMOND_ORE:
                    int oresCountDiamonds = countRelativeDiamond(event.getBlock());
                    profile.getOresMap().put("diamond", profile.getOresMap().get("diamond") + 1);
                    Bukkit.broadcastMessage(CC.translate("&f[FD] &b" + event.getPlayer().getName() + " found " + oresCountDiamonds + " diamond" + (oresCountDiamonds == 1 ? "" : "s") + "."));
                    profile.save();
                    break;
                case EMERALD_ORE:
                case DEEPSLATE_EMERALD_ORE:
                    profile.getOresMap().put("emerald", profile.getOresMap().get("emerald") + 1);
                    profile.save();
                    break;
                case REDSTONE_ORE:
                case DEEPSLATE_REDSTONE_ORE:
                    profile.getOresMap().put("redstone", profile.getOresMap().get("redstone") + 1);
                    profile.save();
                    break;
                case LAPIS_ORE:
                case DEEPSLATE_LAPIS_ORE:
                    profile.getOresMap().put("lapis", profile.getOresMap().get("lapis") + 1);
                    profile.save();
                    break;
                case GOLD_ORE:
                case DEEPSLATE_GOLD_ORE:
                    profile.getOresMap().put("gold", profile.getOresMap().get("gold") + 1);
                    profile.save();
                    break;
                case IRON_ORE:
                case DEEPSLATE_IRON_ORE:
                    profile.getOresMap().put("iron", profile.getOresMap().get("iron") + 1);
                    profile.save();
                    break;
                case COAL_ORE:
                case DEEPSLATE_COAL_ORE:
                    profile.getOresMap().put("coal", profile.getOresMap().get("coal") + 1);
                    profile.save();
                    break;
            }
        }
    }

    public int countRelativeNetherite(Block block) {
        int count = 1; // We start out with one because 'block' is going to be a diamond too.
        block.setMetadata("OrePlaced", new FixedMetadataValue(Bueno.getInstance(), true));
        for (BlockFace checkFace : CHECK_FACES) {
            Block relative = block.getRelative(checkFace);
            if (relative.getType() == Material.NETHERITE_BLOCK && !relative.hasMetadata("OrePlaced")) {
                relative.setMetadata("OrePlaced", new FixedMetadataValue(Bueno.getInstance(), true));
                count += countRelativeNetherite(relative);
            }
        }
        return (count);
    }

    public int countRelativeDiamond(Block block) {
        int count = 1; // We start out with one because 'block' is going to be a diamond too.
        block.setMetadata("OrePlaced", new FixedMetadataValue(Bueno.getInstance(), true));
        for (BlockFace checkFace : CHECK_FACES) {
            Block relative = block.getRelative(checkFace);
            if (relative.getType() == Material.DIAMOND_ORE || relative.getType() == Material.DEEPSLATE_DIAMOND_ORE && !relative.hasMetadata("OrePlaced")) {
                relative.setMetadata("OrePlaced", new FixedMetadataValue(Bueno.getInstance(), true));
                count += countRelativeDiamond(relative);
            }
        }
        return (count);
    }

}