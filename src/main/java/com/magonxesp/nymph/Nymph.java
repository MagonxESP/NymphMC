package com.magonxesp.nymph;


import com.magonxesp.nymph.command.FreeramCommand;
import com.magonxesp.nymph.listener.WorldListener;
import com.magonxesp.nymph.scheduler.UsageScheduler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.magonxesp.nymph.listener.ServerListener;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.IOException;
import com.magonxesp.nymph.http.HttpRequest;


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
        saveDefaultConfig(); // init config file

        PluginManager pluginManager = getServer().getPluginManager();
        BukkitScheduler scheduler = getServer().getScheduler();

        // pluginManager events register
        pluginManager.registerEvents(new ServerListener(), this);
        pluginManager.registerEvents(new WorldListener(), this);

        // scheduler tasks
        scheduler.scheduleSyncRepeatingTask(this, new UsageScheduler(), 0, 5 * 20); // each 5 seconds

        try {
            // command register
            getCommand("freeram").setExecutor(new FreeramCommand());
        } catch (NullPointerException e) {
            getLogger().warning(e.getMessage());
        }

        getLogger().info("Nymph enabled!");

        if (getConfig().getBoolean("debug")) {
            getLogger().info("Debug mode enabled!");
        }
    }

    public static Nymph getPlugin() {
        return getPlugin(Nymph.class);
    }

    public void broadcastMessage(String msg, boolean isInGame) {
        if (isInGame) {
            String prefix = "[" + Nymph.class.getName() + "]";
            Bukkit.broadcastMessage(prefix + " " + msg);
        }

        if (!getConfig().getBoolean("debug")) {
            try {
                HttpRequest botRequest = new HttpRequest("http://192.168.1.46:5000/post", "POST");
                botRequest.addHttpHeader("Content-Type", "application/x-www-form-urlencoded");
                String params =  "status=" + msg;

                botRequest.send(params);
                int responseCode = botRequest.getResponseCode();
                String response = botRequest.getResponse();
                Nymph.getPlugin().getLogger().info("[Bot Request] Status: " + responseCode + "; Response: " + response);
            } catch (IOException e) {
                Nymph.getPlugin().getLogger().warning("[Bot Request] " + e.getMessage());
            }
        }

        Nymph.getPlugin().getLogger().info("[Broadcast] " + msg);
    }

}
