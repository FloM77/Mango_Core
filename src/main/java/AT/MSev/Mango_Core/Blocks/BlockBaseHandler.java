package AT.MSev.Mango_Core.Blocks;

import AT.MSev.Mango_Core.Items.ItemBase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockBaseHandler implements Listener {
    @EventHandler
    void OnPlace(BlockPlaceEvent e)
    {
        ItemBase ib;
        if((ib = ItemBase.Get(e.getItemInHand())) instanceof BlockBase)
        {
            ((BlockBase)ib).OnPlace(e);
        }
    }

    @EventHandler
    void OnBreak(BlockBreakEvent e)
    {
        for(BlockBase bb : BlockBase.BlockTypes)
        {
            if(bb.Physical.getType().equals(e.getBlock().getType()))
            {
                for(BlockInstance bi : new ArrayList<BlockInstance>(bb.Instances))
                {
                    if(e.getBlock().getLocation().equals(bi.Logical))
                    {
                        bb.OnBreak(e, bi);
                    }
                }
            }
        }
    }
}
