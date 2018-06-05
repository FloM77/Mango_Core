package AT.MSev.Mango_Core.Items.Interactable;

import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.joda.time.Period;

public class ItemInteractable extends ItemBase {
    public ItemInteractable(String name, Material appearance)
    {
        super(name, appearance);
    }

    protected Boolean OnRightClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " OnRightClick");
        return true;
    }

    protected Boolean OnLeftClick(PlayerInteractEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " OnLeftClick");
        return true;
    }

    protected Boolean OnDamageReceivedWhileInInventory(EntityDamageEvent e, ItemStack actual)
    {
        MangoUtils.Log("Triggered " + Name + " OnDamageReceivedWhileInInventory");
        return true;
    }

    protected Boolean OnDrop(PlayerDropItemEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " OnDrop");
        return true;
    }

    protected Boolean OnInventoryClick(InventoryClickEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " OnInventoryClick");
        return true;
    }

    protected Boolean OnCommand(PlayerCommandPreprocessEvent e)
    {
        if(e.getMessage().split(" ")[0].equalsIgnoreCase("/hand")) {
            MangoUtils.Log("Triggered " + Name + " command : " + e.getMessage());
            return true;
        }
        return false;
    }

    protected Boolean HandleCooldown(Player player, String tag, int milli)
    {
        ItemInteractableCooldown cooldown = new ItemInteractableCooldown(this, player, tag);
        Period remaining;

        if (cooldown.GetStatus().equals(Time.Status.UNMAPPED) | cooldown.GetStatus().equals(Time.Status.COMPLETED)) {
            cooldown.StartCooldownMillis(milli);

            return true;
        } else if (cooldown.GetStatus().equals(Time.Status.RUNNING)) {
            if ((remaining = cooldown.GetRemaining()) != null) {
                player.sendMessage(ChatColor.RED + remaining.toString() + " until next " + tag + " use");

                return false;
            }
        }

        return true;
    }
}
