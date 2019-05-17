package com.magonxesp.nymph.listener;

import com.magonxesp.nymph.Nymph;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

public class ServerListener implements Listener {

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        Nymph.getPlugin().broadcastMessage("El servidor se ha iniciado!", false);
    }

}
