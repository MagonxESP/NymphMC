package com.magonxesp.nymph;


import com.magonxesp.nymph.listener.WorldListener;
import com.magonxesp.nymph.scheduler.UsageScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.magonxesp.nymph.listener.ServerListener;
import org.bukkit.scheduler.BukkitScheduler;


public class Nymph extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {
        getLogger().info("Nymph disabled!");
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();
        BukkitScheduler scheduler = getServer().getScheduler();

        // pluginManager events register
        pluginManager.registerEvents(new ServerListener(), this);
        pluginManager.registerEvents(new WorldListener(), this);

        // scheduler tasks
        scheduler.scheduleSyncRepeatingTask(this, new UsageScheduler(), 0, 5000); // each 5 seconds

        getLogger().info("Nymph enabled!");
    }

    public static JavaPlugin getPlugin() {
        return Nymph.getPlugin(Nymph.class);
    }

    public static void broadcastMessage(String msg) {
        String prefix = "[" + Nymph.class + "]";
        Bukkit.broadcastMessage(prefix + " " + msg);
    }

}
