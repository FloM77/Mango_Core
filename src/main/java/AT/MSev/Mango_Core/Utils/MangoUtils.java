package AT.MSev.Mango_Core.Utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;

public class MangoUtils {
    public static Boolean DebugMode = false;

    public static void ItemRename(ItemStack item, String newName)
    {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(newName);
        item.setItemMeta(meta);
    }

    public static void ItemRelore(ItemStack item, ArrayList<String> newLore)
    {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(newLore);
        item.setItemMeta(meta);
    }

    public static void Log(String message)
    {
        if(DebugMode) {
            getLogger().info(message);
        }
    }
}
