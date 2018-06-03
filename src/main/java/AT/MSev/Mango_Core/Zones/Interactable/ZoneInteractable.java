package AT.MSev.Mango_Core.Zones.Interactable;

import AT.MSev.Mango_Core.Handler;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Zones.ZoneBase;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class ZoneInteractable extends ZoneBase implements Listener {
    public ZoneInteractable(Location bound1, Location bound2, Plugin plugin)
    {
        super(bound1, bound2);
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    Boolean OnBreakIn(BlockBreakEvent e)
    {
        if(Setting.IsIn(e.getBlock().getLocation()))
        {
            return true;
        }
        return false;
    }
}
