package io.github.silktouchspawners.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.Arrays;

public class ChangeSpawner implements CommandExecutor {

    public ChangeSpawner() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("changespawner")) {
            if (!sender.hasPermission("changespawner.use")) {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use that command");
                return true;
            }
            if (args.length != 1) {
                return false;
            }
            if (!(sender instanceof Player)) {
                return true;
            }

            Player player = (Player) sender;

            if (player.getInventory().getItemInMainHand().getType() != Material.SPAWNER) {
                sender.sendMessage(ChatColor.RED + "That Item isn't a spawner!");
                return true;
            }

            try {
                for (EntityType eType : EntityType.values()) {
                    if (eType == EntityType.UNKNOWN) continue;
                    if (eType.name().equalsIgnoreCase(args[0]) || eType.getKey().toString().equalsIgnoreCase(args[0])) {
                        BlockStateMeta meta = (BlockStateMeta) player.getInventory().getItemInMainHand().getItemMeta();
                        CreatureSpawner newSpawner = (CreatureSpawner) meta.getBlockState();
                        newSpawner.setSpawnedType(eType);
                        meta.setBlockState(newSpawner);
                        meta.setDisplayName(ChatColor.AQUA + eType.name().toUpperCase().substring(0, 1) +
                                eType.name().toLowerCase().substring(1).replace('_', ' ') + " Spawner");

                        player.getInventory().getItemInMainHand().setItemMeta(meta);


                        sender.sendMessage(ChatColor.GREEN + "Spawner changed to type " + eType.name().toUpperCase().substring(0, 1) +
                                eType.name().toLowerCase().substring(1));
                    }
                }
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + "Couldn't find that entity type");
            }
            return true;
        }
        return false;
    }
}
