package me.lukiiy.betaMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Boop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            Player p = Bukkit.getServer().getPlayer(strings[0]);
            if (p == null) {
                commandSender.sendMessage("§cPlayer not found.");
                return true;
            }

            String sender = !(commandSender instanceof Player) ? "Server" : ((Player) commandSender).getDisplayName();

            p.sendMessage(sender + "§d booped you!");
            return true;
        }

        commandSender.sendMessage("§cYou do need to specify someone to boop.");
        return true;
    }
}
