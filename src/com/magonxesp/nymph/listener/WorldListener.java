package com.magonxesp.nymph.listener;

import com.magonxesp.nymph.Nymph;
import com.wimbli.WorldBorder.Events.WorldBorderFillFinishedEvent;
import com.wimbli.WorldBorder.Events.WorldBorderFillStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldListener implements Listener {

    @EventHandler
    public void onWorldBorderFillStart(WorldBorderFillStartEvent event) {
        Nymph.broadcastMessage("Se ha iniciado el preprocesado del mundo " + event.getFillTask().refWorld(), true);
    }

    @EventHandler
    public void onWorldBorderFillEnd(WorldBorderFillFinishedEvent event) {
        Nymph.broadcastMessage("Se ha terminado el preprocesado del mundo " + event.getWorld().getName(), true);
    }

}
