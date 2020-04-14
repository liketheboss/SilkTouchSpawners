package io.github.silktouchspawners.listerners;

import io.github.silktouchspawners.Core;
import io.github.silktouchspawners.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    static Core plugin;

    public BlockBreakListener(Core instance) {
        plugin = instance;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.SPAWNER &&
                e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            Bukkit.getServer().getPluginManager().callEvent(new SpawnerBreakEvent(e.getPlayer(), e.getBlock()));
            e.setExpToDrop(0);
        }
    }
}
