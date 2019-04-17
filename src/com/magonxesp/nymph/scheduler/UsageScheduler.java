package com.magonxesp.nymph.scheduler;

import com.magonxesp.nymph.Nymph;

public class UsageScheduler implements Runnable {

    private Runtime runtime;
    private boolean lowRamUsageAlerted = false;

    public UsageScheduler() {
        runtime = Runtime.getRuntime();
    }

    @Override
    public void run() {
        alertOnLowMemory();
    }

    private void alertOnLowMemory() {
        float free = getMemoryFree();

        if (free < 250 && !lowRamUsageAlerted) {
            String msg = "Queda poca memoria ram en el servidor (" + free + "MB libre)";
            Nymph.broadcastMessage(msg, false);
            lowRamUsageAlerted = true;
        } else if (free > 2048 && lowRamUsageAlerted) {
            String msg = "El servidor vuelve a tener ram UwU";
            Nymph.broadcastMessage(msg, false);
            lowRamUsageAlerted = false;
        }

        Nymph.getPlugin().getLogger().info("Memory free " + free + "MB");
    }

    private float getMemoryFree() {
        return runtime.freeMemory() / (1024 * 1024);
    }

}
