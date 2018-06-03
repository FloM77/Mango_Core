package AT.MSev.Mango_Core.Items.Interactable;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Utils.Security;
import AT.MSev.Mango_Core.Utils.Time;
import org.bukkit.entity.Player;
import org.joda.time.Period;

public class ItemInteractableCooldown {
    String CooldownKey;

    public ItemInteractableCooldown(ItemInteractable item, Player player, String tag)
    {
        CooldownKey = Security.SHAString(player.getUniqueId().toString() + item.Name + tag);
    }

    void StartCooldownMillis(int milliDelay)
    {
        Time.Entry(CooldownKey, milliDelay);
    }

    void StartCooldownMinutes(int minuteDelay)
    {
        Time.EntryMinutes(CooldownKey, minuteDelay);
    }

    public Period GetRemaining()
    {
        return Time.UntilCompletion(CooldownKey);
    }

    public Time.Status GetStatus()
    {
        return Time.GetStatus(CooldownKey);
    }
}
