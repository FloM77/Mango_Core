package AT.MSev.Mango_Core;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Handler implements Listener {

    // DEBUG ONLY
    @EventHandler
    void OnLogin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        //new ZoneTest(p.getLocation(), (p.getLocation().clone().add(100, 100, 100)));
    }
}
