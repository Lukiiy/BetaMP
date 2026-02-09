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

        welcome.replace('&', '§')
            .replace("%o", "${online.size}")
            .replace("%ps", online.joinToString(", ") { it.displayName }).split('\n').forEach { e.player.sendMessage(it.ifEmpty { "§f" }) }
    }

    override fun onPlayerChat(e: PlayerChatEvent) {
        val loc = e.player.location

        e.message = e.message.replace("[coords]", "${loc.blockX} ${loc.blockY} ${loc.blockZ}")
    }

    override fun onPlayerBedEnter(e: PlayerBedEnterEvent) {
        val players = e.player.world.players
        if (players.size < 2) return

        var msg = BetaMP.getInstance().configuration.getString("bedMsg", "").replace("%p", e.player.displayName)
        var possibleSleeplings = 0
        var sleeping = 0

        players.forEach {
            if (!it.isSleepingIgnored) {
                possibleSleeplings++

                if (it.isSleeping) sleeping++
            }
        }

        msg = msg.replace("%r", "${possibleSleeplings - sleeping}")
        if (possibleSleeplings == sleeping) msg = BetaMP.getInstance().configuration.getString("bedMsgFull", "").replace("%p", e.player.displayName)
        if (msg.isBlank()) return

        players.forEach { it.sendMessage(msg) }
        BetaMP.getInstance().server.logger.info("[${e.player.world.name}] $msg")
    }
}