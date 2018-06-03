package AT.MSev.Mango_Core.Items;

import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.joda.time.Period;

import static org.bukkit.Bukkit.getLogger;

public class ItemInteractable extends ItemBase {
    protected int Cooldown = 10000;
    protected Boolean Cancel = false;
    public ItemInteractable(String name, Material appearance)
    {
        super(name, appearance);
    }

    protected void OnRightClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " right click");

        HandleCooldown(e.getPlayer(), "rightclick");
    }

    protected void OnLeftClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " left click");

        HandleCooldown(e.getPlayer(), "leftclick");
    }

    protected void OnDrop(PlayerDropItemEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " drop");
    }

    protected void OnInventoryClick(InventoryClickEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " inventory click");
    }

    protected void HandleCooldown(Player player, String tag)
    {
        ItemInteractableCooldown cooldown = new ItemInteractableCooldown(this, player, tag);
        Period remaining;

        if(Cooldown>0) {
            if (cooldown.GetStatus().equals(Time.Status.UNMAPPED) | cooldown.GetStatus().equals(Time.Status.COMPLETED)) {
                cooldown.StartCooldownMillis(Cooldown);

                Cancel = false;
            } else if (cooldown.GetStatus().equals(Time.Status.RUNNING)) {
                if ((remaining = cooldown.GetRemaining()) != null) {
                    player.sendMessage(ChatColor.RED + remaining.toString() + " until next " + tag + " use");

                    Cancel = true;
                }
            }
        }
        else
        {
            Cancel = false;
        }
    }
}
