package me.lukiiy.betaMP

import org.bukkit.util.config.Configuration
import java.io.File

class ExtraData(name: String) {
    private val plugin = BetaMP.getInstance()
    private val file: File = File(plugin.dataFolder, "$name.yml")
    val config: Configuration

    init {
        if (!plugin.dataFolder.exists()) plugin.dataFolder.mkdirs()
        if (!file.exists()) file.createNewFile()

        config = Configuration(file)
        config.load()
    }

    fun reload() = config.load()

    fun save(): Boolean = config.save()

    fun set(path: String, value: Any?) {
        config.setProperty(path, value)
        save()
    }

    fun get(path: String): Any? = config.getProperty(path)
}
