package me.lukiiy.betaMP;

import me.lukiiy.betaMP.commands.Bed;
import me.lukiiy.betaMP.commands.Spawn;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BetaMP extends JavaPlugin {
    private static BetaMP instance;

    public Configuration config;
    public ExtraData locations;

    @Override
    public void onEnable() {
        instance = this;
        locations = new ExtraData("locations");
        setupConfig();

        PlayerEcho pEcho = new PlayerEcho();
        EntityEcho eEcho = new EntityEcho();

        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, pEcho, Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DEATH, eEcho, Event.Priority.Normal, this);

        getCommand("spawn").setExecutor(new Spawn());
        getCommand("bed").setExecutor(new Bed());
    }

    @Override
    public void onDisable() { }

    public static BetaMP getInstance() {
        return instance;
    }

    // Config
    public void setupConfig() {
        config = getConfiguration();
        config.load();

        config.getString("welcome", "§f\n§fWelcome to §aBetaMP§f!\n§eOnline players (§f%o§e): §f%ps\n");
        config.getString("deathMsg", "§cYou died at §6%x %y %z");

        config.save();
    }
}
