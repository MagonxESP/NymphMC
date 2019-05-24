package com.magonxesp.nymph.command;

import com.magonxesp.nymph.Nymph;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.magonxesp.nymph.npc.NymphEntity;
import org.bukkit.entity.Player;

public class SpawnNymphCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("Nymph.npc.spawn")) {
            return false;
        }

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Location location = player.getLocation();
            spawnNPC(location);
            commandSender.sendMessage("Nymph ha aparecido en: " + location.toString());
        } else {
            commandSender.sendMessage("Este comando solo puede ser usado por un jugador!");
        }

        return true;
    }


    private void spawnNPC(Location location) {
        NymphEntity nymphEntity = new NymphEntity();
        String skinName = Nymph.getPlugin().getConfig().getString("npc.Nymph.skin.name");
        String uuid = Nymph.getPlugin().getConfig().getString("npc.Nymph.skin.uuid");
        String texture = Nymph.getPlugin().getConfig().getString("npc.Nymph.skin.texture");
        String signature = Nymph.getPlugin().getConfig().getString("npc.Nymph.skin.signature");

        nymphEntity.setSkin(skinName, uuid, texture, signature);
        nymphEntity.getNPC().spawn(location);
    }
}
