package com.magonxesp.nymph;


import com.magonxesp.nymph.command.FreeramCommand;
import com.magonxesp.nymph.listener.WorldListener;
import com.magonxesp.nymph.scheduler.UsageScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
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
        broadcastMessage("Se ha apagado el server!", false);
        getLogger().info("Nymph disabled!");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig(); // init config file

        PluginManager pluginManager = getServer().getPluginManager();
        BukkitScheduler scheduler = getServer().getScheduler();
        Plugin worldborder = getServer().getPluginManager().getPlugin("WorldBorder");

        // pluginManager events register
        pluginManager.registerEvents(new ServerListener(), this);

        if (worldborder != null) {
            pluginManager.registerEvents(new WorldListener(), this);
        }

        // scheduler tasks
        scheduler.scheduleSyncRepeatingTask(this, new UsageScheduler(), 0, 5 * 20); // each 5 seconds

        // command register
        PluginCommand command = getCommand("freeram");

        if (command != null) {
            command.setExecutor(new FreeramCommand());
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
                String http_host = getConfig().getString("bot_http_host");
                String http_port = getConfig().getString("bot_http_port");
                boolean ssl_enabled = getConfig().getBoolean("bot_http_is_ssl");
                String http_protocol = "http";

                if (ssl_enabled) {
                    http_protocol = "https";
                }

                String url = http_protocol + "://" + http_host;

                if (http_port != null && !http_port.equals("80") && !http_port.equals("443")) {
                    url += ":" + http_port;
                }

                url += "/post";

                HttpRequest botRequest = new HttpRequest(url, "POST");
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
