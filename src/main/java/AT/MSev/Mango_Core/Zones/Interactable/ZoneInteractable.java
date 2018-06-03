package AT.MSev.Mango_Core.Zones.Interactable;

import AT.MSev.Mango_Core.Handler;
import AT.MSev.Mango_Core.Mango_Core;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Zones.ZoneBase;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class ZoneInteractable extends ZoneBase implements Listener {
    public ZoneInteractable(Location bound1, Location bound2)
    {
        super(bound1, bound2);
        getServer().getPluginManager().registerEvents(this, Mango_Core.plugin);
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

    @EventHandler
    Boolean OnPlaceIn(BlockPlaceEvent e)
    {
        if(Setting.IsIn(e.getBlockPlaced().getLocation()))
        {
            return true;
        }
        return false;
    }

    @EventHandler
    Boolean OnCommandIn(PlayerCommandPreprocessEvent e)
    {
        if(Setting.IsIn(e.getPlayer().getLocation()))
        {
            return true;
        }
        return false;
    }

    public static ZoneInteractable deserialize(Map<String, Object> args) {
        Location q1 = ((Location)args.get("q1"));
        Location q2 = ((Location)args.get("q2"));
        ZoneInteractable ret = new ZoneInteractable(q1, q2);
        return ret;
    }
}
