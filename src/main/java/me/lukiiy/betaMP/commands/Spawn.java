package me.lukiiy.betaMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Location spawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();

        commandSender.sendMessage("The server's spawn is located at §a" + spawn.getBlockX() + " " + spawn.getBlockZ());
        return true;
    }
}
