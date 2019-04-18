package com.magonxesp.nymph.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FreeramCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("Nymph.system.freeram")) {
            return false;
        }

        Runtime runtime = Runtime.getRuntime();

        float free = runtime.freeMemory() / (1024 * 1024);
        float max = runtime.maxMemory() / (1024 * 1024);
        float total = runtime.totalMemory() / (1024 * 1024);

        String status = "Free: " + free + "MB\nMax: " + max + "MB\nTotal: " + total + "MB";
        commandSender.sendMessage(status);

        return true;
    }

}
