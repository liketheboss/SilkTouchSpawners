package io.github.silktouchspawners.listerners;

import io.github.silktouchspawners.Core;
import io.github.silktouchspawners.events.SpawnerBreakEvent;
import io.github.silktouchspawners.events.SpawnerPlaceEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class SilkListeners implements Listener {

    static Core plugin;

    public SilkListeners(Core instance) {
        plugin = instance;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSpawnerBreak(SpawnerBreakEvent e) {
        CreatureSpawner spawner = (CreatureSpawner) e.getSpawner().getState();
        ItemStack item = new ItemStack(spawner.getType());
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();

        CreatureSpawner newSpawner = (CreatureSpawner) meta.getBlockState();
        newSpawner.setSpawnedType(spawner.getSpawnedType());

        meta.setBlockState(newSpawner);
        meta.setDisplayName(ChatColor.AQUA + newSpawner.getSpawnedType().name().toUpperCase().substring(0, 1) +
                newSpawner.getSpawnedType().name().toLowerCase().substring(1) + " Spawner");

        item.setItemMeta(meta);

        Location loc = e.getSpawner().getLocation();
        loc.getWorld().dropItemNaturally(loc, item);
    }

    @EventHandler
    public void onSpawnerPlaced(SpawnerPlaceEvent e) {
        BlockStateMeta meta = (BlockStateMeta) e.getItem().getItemMeta();
        CreatureSpawner itemSpawnerVersion = (CreatureSpawner) meta.getBlockState();

        CreatureSpawner spawner = (CreatureSpawner) e.getSpawner().getState();
        spawner.setSpawnedType(itemSpawnerVersion.getSpawnedType());
        spawner.update();
    }
}
