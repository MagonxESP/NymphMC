package com.magonxesp.nymph.npc;


import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.MinecraftServer;
import net.minecraft.server.v1_13_R2.PlayerInteractManager;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Location;

import java.util.UUID;

public class NymphEntity {

    private EntityPlayer entityPlayer;

    public NymphEntity(MinecraftServer minecraftserver, WorldServer worldserver) {
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Nymph");
        PlayerInteractManager playerInteractManager = new PlayerInteractManager(worldserver);

        entityPlayer = new EntityPlayer(minecraftserver, worldserver, gameProfile, playerInteractManager);
        entityPlayer.setInvulnerable(true);
    }

    public void spawn(Location location) {
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entityPlayer.spawnIn(entityPlayer.world);
    }

    public EntityPlayer getEntity() {
        return entityPlayer;
    }
}
