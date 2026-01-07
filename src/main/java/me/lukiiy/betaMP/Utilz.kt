package me.lukiiy.betaMP

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

object Utilz {
    fun Location.serialize(): String = "${world.name};$x;$y;$z"

    fun String.toLocation(): Location? {
        val (worldName, x, y, z) = split(";").takeIf { it.size == 4 } ?: return null

        return Location(Bukkit.getServer().getWorld(worldName) ?: Bukkit.getServer().worlds.first(), x.toDouble(), y.toDouble(), z.toDouble())
    }

    fun Player.getBedLocation(): Location? {
        val handle = (this as CraftPlayer).handle
        val bed = handle.bed ?: return null

        return Location(Bukkit.getServer().getWorld(handle.spawnWorld), bed.x + 0.5, bed.y.toDouble(), bed.z + 0.5)
    }
}