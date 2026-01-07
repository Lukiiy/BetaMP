package me.lukiiy.betaMP

import me.lukiiy.betaMP.Utilz.serialize
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityListener

class EntityEcho : EntityListener() {
    override fun onEntityDeath(e: EntityDeathEvent) {
        if (e.entity !is Player) return
        val p = e.entity as Player
        val loc = p.location

        val deathMsg = BetaMP.getInstance().config.getString("deathMsg", "").replace('&', '§').replace("%x", "${loc.blockX}").replace("%y", "${loc.blockY}").replace("%z", "${loc.blockZ}")
        if (deathMsg.isBlank()) p.sendMessage(deathMsg)

        BetaMP.getInstance().locations.set("death.${p.uniqueId}", loc.serialize())
    }
}