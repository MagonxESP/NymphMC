package com.magonxesp.nymph.scheduler;

import com.magonxesp.nymph.Nymph;
import com.wimbli.WorldBorder.Events.WorldBorderFillStartEvent;

public class WorldBorderFillProgressScheduler implements Runnable {

    private WorldBorderFillStartEvent event;

    public WorldBorderFillProgressScheduler(WorldBorderFillStartEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
        double completed = event.getFillTask().getPercentageCompleted();
        String world = event.getFillTask().refWorld();

        Nymph.broadcastMessage("El preprocesado de " + world + " esta al " + completed + "% completado", true);
    }
}
