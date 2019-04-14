package com.magonxesp.nymph;


import com.magonxesp.nymph.listener.WorldListener;
import com.magonxesp.nymph.scheduler.UsageScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.magonxesp.nymph.listener.ServerListener;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;


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

    private static void sendStatus(String status) {
        try {
            URL url = new URL("http://192.168.1.46:5000/post");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "status=" + status;
            request.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(request.getOutputStream());
            outputStream.writeBytes(params);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            getPlugin().getLogger().log(Level.WARNING, e.getMessage());
        }
    }

    public static void broadcastMessage(String msg, boolean isInGame) {
        if (isInGame) {
            String prefix = "[" + Nymph.class + "]";
            Bukkit.broadcastMessage(prefix + " " + msg);
        }

        sendStatus(msg);
    }

}
