package AT.MSev.Mango_Core.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    public static Object GetPrivateField(String fieldName, Class clazz, Object object)
    {
        Field field;
        Object o = null;

        try
        {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch(NoSuchFieldException e) { e.printStackTrace(); } catch(IllegalAccessException e) { e.printStackTrace(); }

        return o;
    }

    public static Method GetPrivateMethod(String methodName, Class clazz, Class<?>... params)
    {
        try{
            Method m = clazz.getDeclaredMethod(methodName, params);
            m.setAccessible(true);
            return m;
        } catch(NoSuchMethodException e) { e.printStackTrace(); }
        return null;
    }

    public static Method GetPrivateMethod(String methodName, Class clazz)
    {
        try{
            Method m = clazz.getDeclaredMethod(methodName);
            m.setAccessible(true);
            return m;
        } catch(NoSuchMethodException e) { e.printStackTrace(); }
        return null;
    }

    public static ArrayList<ItemStack> GetCustomItemsInInventory(Player p)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        for(ItemStack is : p.getInventory().getContents())
        {
            if(NBTManager.GetTag(is, "Custom") != null) {
                ret.add(is);
            }
        }
        return ret;
    }
}
