package io.github.silktouchspawners;

import io.github.silktouchspawners.commands.ChangeSpawner;
import io.github.silktouchspawners.listerners.BlockBreakListener;
import io.github.silktouchspawners.listerners.BlockPlaceListener;
import io.github.silktouchspawners.listerners.SilkListeners;
import io.github.silktouchspawners.tabcompleters.ChangeSpawnerTab;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        registerTabCompleters();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("changespawner").setExecutor(new ChangeSpawner());
    }

    private void registerListeners() {
        new BlockPlaceListener(this);
        new BlockBreakListener(this);
        new SilkListeners(this);
    }

    private void registerTabCompleters() {
        this.getCommand("changespawner").setTabCompleter(new ChangeSpawnerTab());
    }
}
