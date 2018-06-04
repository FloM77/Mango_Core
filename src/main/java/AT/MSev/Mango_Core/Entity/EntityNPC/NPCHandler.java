package AT.MSev.Mango_Core.Entity.EntityNPC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCHandler implements Listener {
    @EventHandler
    protected void OnInteract(PlayerInteractEntityEvent e)
    {
        for(VillagerNPC npc: VillagerNPC.All)
        {
            npc.OnInteract(e);
        }
    }
}
