package com.magonxesp.nymph.scheduler;

import com.magonxesp.nymph.Nymph;

public class UsageScheduler implements Runnable {

    private Runtime runtime;

    public UsageScheduler() {
        runtime = Runtime.getRuntime();
    }

    @Override
    public void run() {
        alertOnLowMemory();
    }

    private void alertOnLowMemory() {
        float free = getMemoryFree();

        if (free < 250) {
            String msg = "Queda poca memoria ram en el servidor (" + free + "MB libre)";
            Nymph.broadcastMessage(msg, false);
        }
    }

    private float getMemoryFree() {
        return runtime.freeMemory() / (1024 * 1024);
    }

}
