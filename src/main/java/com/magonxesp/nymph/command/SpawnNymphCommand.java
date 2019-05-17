package com.magonxesp.nymph.command;

import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.magonxesp.nymph.npc.NymphEntity;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.Player;

public class SpawnNymphCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("Nymph.npc.spawn")) {
            return false;
        }

        Server server = commandSender.getServer();
        World world = commandSender.getServer().getWorld("Apocalisis maincra");
        NymphEntity nymphEntity = new NymphEntity(((CraftServer) server).getServer(), ((CraftWorld) world).getHandle());
        Location location;

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            location = player.getLocation();

            nymphEntity.spawn(player, location);
            commandSender.sendMessage("Nymph ha aparecido en: " + location.toString());
        } else {
            commandSender.sendMessage("Este comando solo puede ser usado por un jugador!");
        }

        return true;
    }

}
