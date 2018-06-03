package AT.MSev.Mango_Core;

import AT.MSev.Mango_Core.Blocks.BlockBase;
import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Items.ItemInteractable;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Handler implements Listener {
    // DEBUG ONLY
    @EventHandler
    void OnLogin(PlayerJoinEvent e)
    {
        new ItemInteractable("ItemName1", Material.STICK).Give(e.getPlayer(), true);
        new ItemBase("ItemName2", Material.BONE).Give(e.getPlayer(), true);
        new BlockBase("Block1", Material.DIAMOND_BLOCK).Give(e.getPlayer(), true);
    }
}
