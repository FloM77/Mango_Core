package AT.MSev.Mango_Core.Items.Interactable;

import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.joda.time.Period;

public class ItemInteractable extends ItemBase {
    protected int Cooldown = 10000;
    public ItemInteractable(String name, Material appearance)
    {
        super(name, appearance);
    }

    protected Boolean OnRightClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " right click");

        return HandleCooldown(e.getPlayer(), "rightclick");
    }

    protected Boolean OnLeftClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " left click");

        return HandleCooldown(e.getPlayer(), "leftclick");
    }

    protected void OnDrop(PlayerDropItemEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " drop");
    }

    protected void OnInventoryClick(InventoryClickEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " inventory click");
    }

    Boolean HandleCooldown(Player player, String tag)
    {
        ItemInteractableCooldown cooldown = new ItemInteractableCooldown(this, player, tag);
        Period remaining;

        if(Cooldown>0) {
            if (cooldown.GetStatus().equals(Time.Status.UNMAPPED) | cooldown.GetStatus().equals(Time.Status.COMPLETED)) {
                cooldown.StartCooldownMillis(Cooldown);

                return true;
            } else if (cooldown.GetStatus().equals(Time.Status.RUNNING)) {
                if ((remaining = cooldown.GetRemaining()) != null) {
                    player.sendMessage(ChatColor.RED + remaining.toString() + " until next " + tag + " use");

                    return false;
                }
            }
        }
        else
        {
            return true;
        }
        return true;
    }
}
