package com.magonxesp.nymph.npc;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.event.NavigationCompleteEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;


public class NymphEntity implements Listener {

    private NPC npc;

    public NymphEntity() {
        this.npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Nymph");
    }

    public void setSkin(String skinName, String uuid, String texture, String signature) {
        npc.data().remove(NPC.PLAYER_SKIN_UUID_METADATA);
        npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA);
        npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA);
        npc.data().remove("cached-skin-uuid-name");
        npc.data().remove("cached-skin-uuid");
        npc.data().remove(NPC.PLAYER_SKIN_UUID_METADATA);

        npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
        npc.data().set("cached-skin-uuid-name", skinName);
        npc.data().set("cached-skin-uuid", UUID.fromString(uuid));
        npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, skinName);
        npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, texture);
        npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, signature);
    }

    public NPC getNPC() {
        return npc;
    }
}
