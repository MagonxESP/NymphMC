package com.magonxesp.nymph.command;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import net.citizensnpcs.api.CitizensAPI;

import java.util.function.Consumer;

public class DespawnNymph implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("Nymph.npc.despawn")) {
            CitizensAPI.getNPCRegistry().forEach(new Consumer<NPC>() {
                @Override
                public void accept(NPC npc) {
                    if (npc.getName().equals("Nymph")) {
                        npc.despawn();
                        npc.destroy();
                    }
                }
            });

            return true;
        }

        return false;
    }

}
