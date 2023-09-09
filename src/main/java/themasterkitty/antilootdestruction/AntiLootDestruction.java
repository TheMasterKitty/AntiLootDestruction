package themasterkitty.antilootdestruction;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AntiLootDestruction extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void itemSpawn(ItemSpawnEvent event) {
        event.getEntity().setHealth(1000); // Make items industructible
    }

    @EventHandler
    public void blowUp(BlockExplodeEvent e) {
        try {
            if (Objects.requireNonNull(e.getExplodedBlockState()).getType() == Material.RESPAWN_ANCHOR) {
                e.getBlock().getLocation().getNearbyEntities(5, 4, 5).forEach(entity -> {
                    if (entity.getType() == EntityType.DROPPED_ITEM) entity.remove(); // Destroy items via Anchors alone.
                });
            }
        }
        catch (Exception ignored) { }
    }
}
