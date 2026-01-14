package me.lukiiy.betaMP.commands;

import me.lukiiy.betaMP.BetaMP;
import me.lukiiy.betaMP.Utilz;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            if (strings.length > 0) {
                Player p = Bukkit.getServer().getPlayer(strings[0]);
                if (p == null) {
                    commandSender.sendMessage("No player was found");
                    return true;
                }

                Object deathLocValue = BetaMP.getInstance().locations.get("death." + p.getUniqueId());
                if (deathLocValue == null) {
                    commandSender.sendMessage("This player has no recorded death point!");
                    return true;
                }

                p.teleport(Utilz.INSTANCE.toLocation((String) deathLocValue));
                commandSender.sendMessage("Teleported " + p.getDisplayName() + " to their death point.");
                return true;
            }

            commandSender.sendMessage("You need to specify a player to teleport them to their death point.");
            return true;
        }

        Player p = (Player) commandSender;
        Object deathLocValue = BetaMP.getInstance().locations.get("death." + p.getUniqueId());
        if (deathLocValue == null) {
            commandSender.sendMessage("§cYou do not have a recorded death point.");
            return true;
        }

        Location loc = Utilz.INSTANCE.toLocation((String) deathLocValue);
        if (loc == null) return true;

        if (BetaMP.getInstance().getConfiguration().getBoolean("cheatyBack", false)) { // cheat
            if (commandSender.hasPermission("betamp.backtp") && strings.length > 0) {
                Player target = Bukkit.getServer().getPlayer(strings[0]);

                if (target == null) {
                    commandSender.sendMessage("§cNo player was found");
                    return true;
                }

                p.sendMessage("§aTeleported §f" + target.getDisplayName() + " §ato their death point.");
                return true;
            }

            p.sendMessage("§aTeleported to your last recorded death point!");
            p.teleport(loc);
        } else p.sendMessage("§fYour death point is located at §c" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());

        return true;
    }
}
