package AT.MSev.Mango_Core;

import AT.MSev.Mango_Core.Blocks.BlockBase;
import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Zones.Interactable.ZoneTest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Handler implements Listener {
    public Handler(Plugin plugin)
    {
        this.plugin = plugin;
    }
    Plugin plugin;
    // DEBUG ONLY
    @EventHandler
    void OnLogin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        new ItemInteractable("ItemName1", Material.STICK).Give(p, true);
        new ItemBase("ItemName2", Material.BONE).Give(p, true);
        new BlockBase("Block1", Material.DIAMOND_BLOCK).Give(p, true);

        new ZoneTest(p.getLocation(), (p.getLocation().clone().add(100, 100, 100)), plugin);
    }
}
