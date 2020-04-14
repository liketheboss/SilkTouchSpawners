package io.github.silktouchspawners.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class SpawnerPlaceEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player placer;
    private Block spawner;

    private ItemStack item;


    public SpawnerPlaceEvent(Player placer, Block spawner, ItemStack item) {
        this.placer = placer;
        this.spawner = spawner;
        this.item = item;
    }

    public Player getPlacer() {
        return placer;
    }

    public Block getSpawner() {
        return spawner;
    }

    public ItemStack getItem() {
        return item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}