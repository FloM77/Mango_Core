package AT.MSev.Mango_Core.Items.Interactable;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Items.ItemBase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemInteractableHandler implements Listener {
    @EventHandler
    void OnClick(PlayerInteractEvent e)
    {
        ItemBase ib;
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) | e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if((ib = ItemBase.Get(e.getItem())) instanceof ItemInteractable)
            {
                ((ItemInteractable)ib).OnRightClick(e);
            }
        }
        else if(e.getAction().equals(Action.LEFT_CLICK_AIR) | e.getAction().equals(Action.LEFT_CLICK_BLOCK))
        {
            if((ib = ItemBase.Get(e.getItem())) instanceof ItemInteractable)
            {
                ((ItemInteractable)ib).OnLeftClick(e);
            }
        }
    }

    @EventHandler
    void OnDrop(PlayerDropItemEvent e)
    {
        ItemBase ib;
        if((ib = ItemBase.Get(e.getItemDrop().getItemStack())) instanceof ItemInteractable)
        {
            ((ItemInteractable)ib).OnDrop(e);
        }
    }

    @EventHandler
    void OnInventoryClick(InventoryClickEvent e)
    {
        if(e.getCurrentItem() != null) {
            ItemBase ib;
            if ((ib = ItemBase.Get(e.getCurrentItem())) instanceof ItemInteractable) {
                ((ItemInteractable) ib).OnInventoryClick(e);
            }
        }
    }
}
