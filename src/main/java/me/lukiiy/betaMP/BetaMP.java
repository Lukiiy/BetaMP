package me.lukiiy.betaMP;

import me.lukiiy.betaMP.commands.Back;
import me.lukiiy.betaMP.commands.Bed;
import me.lukiiy.betaMP.commands.Boop;
import me.lukiiy.betaMP.commands.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BetaMP extends JavaPlugin {
    private static BetaMP instance;

    public ExtraData locations;

    @Override
    public void onEnable() {
        instance = this;
        locations = new ExtraData("locations");
        setupConfig();

        PluginManager pm = getServer().getPluginManager();
        PlayerEcho pEcho = new PlayerEcho();
        EntityEcho eEcho = new EntityEcho();

        pm.registerEvent(Event.Type.PLAYER_JOIN, pEcho, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, eEcho, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, pEcho, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_ENTER, pEcho, Event.Priority.Normal, this);

        getCommand("spawn").setExecutor(new Spawn());
        getCommand("bed").setExecutor(new Bed());
        getCommand("boop").setExecutor(new Boop());
        getCommand("back").setExecutor(new Back());

        int difficulty = getConfiguration().getInt("difficulty", -1);
        if (difficulty > 0 && difficulty < 4) Bukkit.getServer().getWorlds().forEach(w -> ((CraftWorld) w).getHandle().spawnMonsters = difficulty);
    }

    @Override
    public void onDisable() { }

    public static BetaMP getInstance() {
        return instance;
    }

    // Config
    public void setupConfig() {
        getConfiguration().load();

        getConfiguration().getString("welcome", "§f\n§fWelcome to §aBetaMP§f!\n§eOnline players (§f%o§e): §f%ps\n");
        getConfiguration().getString("deathMsg", "§cYou died at §6%x %y %z");
        getConfiguration().getBoolean("cheatyBack", false);
        getConfiguration().getInt("difficulty", -1);

        getConfiguration().save();
    }
}
