package AT.MSev.Mango_Core.Zones.Interactable;

import AT.MSev.Mango_Core.Zones.Interactable.ZoneInteractable;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class ZoneTest extends ZoneInteractable {
    public ZoneTest(Location bound1, Location bound2, Plugin plugin)
    {
        super(bound1, bound2, plugin);
    }

    @Override
    @EventHandler
    Boolean OnBreakIn(BlockBreakEvent e) {
        Boolean result = super.OnBreakIn(e);
        if(result) {
            e.setCancelled(true);
        }
        return result;
    }
}
