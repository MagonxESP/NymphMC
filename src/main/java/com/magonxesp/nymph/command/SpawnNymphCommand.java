package com.magonxesp.nymph.command;

import net.minecraft.server.v1_13_R2.MinecraftServer;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.magonxesp.nymph.npc.NymphEntity;

public class SpawnNymphCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("Nymph.npc.spawn")) {
            return false;
        }

        Server server = commandSender.getServer();
        World world = commandSender.getServer().getWorld("Apocalisis maincra");
        NymphEntity nymphEntity = new NymphEntity((MinecraftServer) server, (WorldServer) world);

        Location location = new Location(world, 0.0, 0.0, 0.0, 0.0f, 0.0f);

        nymphEntity.spawn(location);

        return true;
    }

}
