package io.github.silktouchspawners.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class ChangeSpawnerTab implements TabCompleter {

    private List<String> possibleEntities = new ArrayList<String>();

    public ChangeSpawnerTab() {

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (possibleEntities.isEmpty()) {
            for (EntityType eType : EntityType.values()) {
                if (eType == EntityType.UNKNOWN) continue;
                possibleEntities.add(eType.getKey().toString());
            }
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String eType : possibleEntities) {
                if (eType.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(eType);
                else if (eType.split(":")[1].startsWith(args[0].toLowerCase()))
                    result.add(eType);
            }

            return result;
        }

        return null;
    }
}
