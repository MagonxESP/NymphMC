package com.magonxesp.nymph.scheduler;

import com.magonxesp.nymph.Nymph;

public class UsageScheduler implements Runnable {

    private Runtime runtime;

    public UsageScheduler() {
        runtime = Runtime.getRuntime();
    }

    @Override
    public void run() {
        Nymph.getPlugin().getLogger().info("Memory free " + getMemoryFree() + "MB");
        alertOnLowMemory();
    }

    private void alertOnLowMemory() {
        float free = getMemoryFree();

        if (free < 250) {
            Nymph.getPlugin().getLogger().info("Queda poca ram en el servidor");
        }
    }

    private float getMemoryFree() {
        return runtime.freeMemory() / (1024 * 1024);
    }

}
