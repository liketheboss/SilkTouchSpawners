package io.github.silktouchspawners.listeners;

import io.github.silktouchspawners.Core;
import io.github.silktouchspawners.events.SpawnerPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    static Core plugin;

    public BlockPlaceListener(Core instance) {
        plugin = instance;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.SPAWNER) {
            Bukkit.getServer().getPluginManager().callEvent(new SpawnerPlaceEvent(e.getPlayer(), e.getBlock(), e.getItemInHand()));
        }
    }
}
