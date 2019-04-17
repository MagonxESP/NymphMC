package com.magonxesp.nymph.listener;

import com.magonxesp.nymph.Nymph;
import com.magonxesp.nymph.scheduler.WorldBorderFillProgressScheduler;
import com.wimbli.WorldBorder.Events.WorldBorderFillFinishedEvent;
import com.wimbli.WorldBorder.Events.WorldBorderFillStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldListener implements Listener {

    private int progressTaskId = 0;

    @EventHandler
    public void onWorldBorderFillStart(WorldBorderFillStartEvent event) {
        Nymph.broadcastMessage("Se ha iniciado el preprocesado del mundo " + event.getFillTask().refWorld(), true);
        progressTaskId = Nymph.getPlugin()
                .getServer()
                .getScheduler()
                .scheduleSyncRepeatingTask(Nymph.getPlugin(), new WorldBorderFillProgressScheduler(event), 0, 60 * 60 * 20);

    }

    @EventHandler
    public void onWorldBorderFillEnd(WorldBorderFillFinishedEvent event) {
        Nymph.broadcastMessage("Se ha terminado el preprocesado del mundo " + event.getWorld().getName(), true);

        if (progressTaskId != 0) {
            Nymph.getPlugin().getServer().getScheduler().cancelTask(progressTaskId);
        }
    }

}
