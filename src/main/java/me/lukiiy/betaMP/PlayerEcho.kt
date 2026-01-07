package me.lukiiy.betaMP

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerListener

class PlayerEcho : PlayerListener() {
    override fun onPlayerJoin(e: PlayerJoinEvent) {
        val welcome = BetaMP.getInstance().configuration.getString("welcome", "")
        if (welcome.isBlank()) return

        val online = Bukkit.getServer().onlinePlayers

        welcome.replace('&', '§').replace("%o", "${online.size}").replace("%ps", online.joinToString(", ") { it.displayName }).split('\n').forEach { e.player.sendMessage(it.ifEmpty { "§f" }) }
    }

    override fun onPlayerChat(e: PlayerChatEvent) {
        val loc = e.player.location

        e.message = e.message.replace("[coords]", "${loc.blockX} ${loc.blockY} ${loc.blockZ}")
    }

    override fun onPlayerBedEnter(e: PlayerBedEnterEvent) {
        Bukkit.getServer().broadcastMessage("${e.player.displayName} is sleeping in a bed. To skip to dawn, all players need to sleep in beds at the same time.")
    }
}