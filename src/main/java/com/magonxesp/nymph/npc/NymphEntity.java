package com.magonxesp.nymph.npc;


import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.AbstractNPC;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.citizensnpcs.api.npc.BlockBreaker;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class NymphEntity extends AbstractNPC {

    private EntityPlayer entityPlayer;
    private GameProfile gameProfile;
    private PlayerInteractManager playerInteractManager;
    private MinecraftServer minecraftServer;
    private WorldServer worldServer;

    public NymphEntity(MinecraftServer minecraftserver, WorldServer worldserver) {
        super();
        this.gameProfile = new GameProfile(UUID.randomUUID(), "Nymph");
        this.playerInteractManager = new PlayerInteractManager(worldserver);
        this.minecraftServer = minecraftserver;
        this.worldServer = worldserver;
        this.setSkin();
    }

    @Override
    public boolean despawn(DespawnReason despawnReason) {
        return false;
    }

    @Override
    public void faceLocation(Location location) {

    }

    @Override
    public BlockBreaker getBlockBreaker(Block block, BlockBreaker.BlockBreakerConfiguration blockBreakerConfiguration) {
        return null;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public Navigator getNavigator() {
        return null;
    }

    @Override
    public Location getStoredLocation() {
        return null;
    }

    @Override
    public void setBukkitEntityType(EntityType entityType) {

    }

    @Override
    public boolean spawn(Location location) {
        return false;
    }

    @Override
    public boolean spawn(Location location, SpawnReason spawnReason) {
        return false;
    }

    private void setSkin() {
        gameProfile.getProperties()
                .put("textures", new Property("textures",
                        "eyJ0aW1lc3RhbXAiOjE1NTgxMjYxMzI3MTEsInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E0ZmRiMjE2ZTJkYTIyZGVlN2Q1ZTU2ZGJjNTg0Mzc2ODFlY2U1MmFlMjc5NTg1MTAyN2ExMGNjODY0ZDVjYWYifX19",
                        "yg0uHgH0fBecX7I3ht7asCMJWwKpZhJGCwmVu0VODKNmR5BF7tBfkgPc/P1D/HFeDW4FxpZdUYOypP6yl3E/KPenH1aT/jAkG4L7DRvRyRjZZDzW310K5WK2YSihTEFO5VEyrXhQ+vMdh7iCYZo8uHtppUfJOCeb4ogze/m9ne9JtDMRrgKBhCrF0rnVGmqq2V5BG1erP2lLJ8qI5HMNS4ItNrmN8HJcxVHssceeVW5IoulwVdlXLtT0wudktur4DVLbS+c/g4Y5+c3zUhLU4Uk8wnD94HJ2wu+S1bmZPYbHZND8zgomg/0zCjTzRehQgs4rRqC/AVMpf7DTBtiA44LGeMpeecbg+FCS+uCWhNg8NwasdB3uwRpdcoAYkIWVWccxazrr0P91CYqm48b8AUJKB5qNJZJpXpjbV7j06FwzqSWVXXeWLmv50Icso9HjEkiRtmKKy2vytD/2cc3EsAdMmdtOFVrKePc4GXBe8v/Re0LQDo1PqDjaZ1QGp6sHduW43zX9dgwbDSRM+ZBfg1axo53O8axNiXJCFzyu5fQImqiTpqVDi9AY6D9RKrp2QUSiX6BSjHoV+q6N5qpqcZYP7/fFU0qII8HrIPb4RTvnh9h9aLIIVHNuC59uZk0mnofPWSs7QU8i6LyrAZMa55QPOMms9xe/Mmp2Qy41BBs="));

    }

    public void spawn(Player player, Location location) {
        entityPlayer = new EntityPlayer(minecraftServer, worldServer, gameProfile, playerInteractManager);
        entityPlayer.setInvulnerable(true);
        entityPlayer.teleportTo(location, false);

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
    }
}
