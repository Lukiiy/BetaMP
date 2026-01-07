package me.lukiiy.betaMP

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerListener

class PlayerEcho : PlayerListener() {
    override fun onPlayerJoin(e: PlayerJoinEvent) {
        val welcome = BetaMP.getInstance().config.getString("welcome", "")
        if (welcome.isBlank()) return

        val online = Bukkit.getServer().onlinePlayers

        welcome.replace('&', '§').replace("%o", "${online.size}").replace("%ps", online.joinToString(", ") { it.displayName }).split('\n').forEach { e.player.sendMessage(it.ifEmpty { "§f" }) }
    }
}