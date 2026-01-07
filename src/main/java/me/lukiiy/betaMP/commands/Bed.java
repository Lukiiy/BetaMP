package me.lukiiy.betaMP.commands;

import me.lukiiy.betaMP.Utilz;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be used by players!");
            return true;
        }

        Player p = (Player) commandSender;
        Location bedSpawn = Utilz.INSTANCE.getBedLocation(p);

        if (bedSpawn != null) p.sendMessage("Your bed is located at §d" + bedSpawn.getBlockX() + " " + bedSpawn.getBlockZ() + " (" + Math.round(p.getLocation().distance(bedSpawn)) + ")");
        else p.sendMessage("§cYou haven't set your spawn using a bed yet!");

        return true;
    }
}
