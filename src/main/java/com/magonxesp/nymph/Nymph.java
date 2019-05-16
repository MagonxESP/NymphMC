package com.magonxesp.nymph;


import com.magonxesp.nymph.command.FreeramCommand;
import com.magonxesp.nymph.listener.WorldListener;
import com.magonxesp.nymph.scheduler.UsageScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.magonxesp.nymph.listener.ServerListener;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.IOException;
import com.magonxesp.nymph.http.HttpRequest;
import java.io.File;


public class Nymph extends JavaPlugin {

    private boolean debug;

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
        scheduler.scheduleSyncRepeatingTask(this, new UsageScheduler(), 0, 5 * 20); // each 5 seconds

        try {
            // command register
            getCommand("freeram").setExecutor(new FreeramCommand());
        } catch (NullPointerException e) {
            getLogger().warning(e.getMessage());
        }

        getLogger().info("Nymph enabled!");
    }

    public static JavaPlugin getPlugin() {
        return Nymph.getPlugin(Nymph.class);
    }

    public static void broadcastMessage(String msg, boolean isInGame) {
        if (isInGame) {
            String prefix = "[" + Nymph.class.getName() + "]";
            Bukkit.broadcastMessage(prefix + " " + msg);
        }
        /*
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
        */
        Nymph.getPlugin().getLogger().info("[Broadcast] " + msg);
    }

    public static File getPluginDir() {
        File pluginDirectory = new File(Nymph.class.getName());

        if (!pluginDirectory.exists()) {
            if (!pluginDirectory.mkdir()) {
                return null;
            }
        }

        if (pluginDirectory.canRead() && pluginDirectory.canWrite()) {
            return pluginDirectory;
        } else {
            return null;
        }
    }

}
