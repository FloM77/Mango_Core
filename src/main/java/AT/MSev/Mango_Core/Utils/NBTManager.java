package AT.MSev.Mango_Core.Utils;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTManager {
    public static ItemStack AddItemNBT(ItemStack item, String key, NBTBase nbt)
    {
        NBTTagCompound tags = GetItemNms(item);
        tags.set(key, nbt);
        return SetItemNms(item, tags);
    }

    public static ItemStack RemoveItemNBT(ItemStack item, String key)
    {
        NBTTagCompound tags = GetItemNms(item);
        tags.remove(key);
        return SetItemNms(item, tags);
    }

    public static Boolean CompareTag(ItemStack item, String key, NBTBase compareTo)
    {
        if(HasTags(item) && HasKey(item, key)) {
            return GetTag(item, key).equals(compareTo);
        }
        return false;
    }

    public static NBTBase GetTag(ItemStack item, String key)
    {
        if(HasTags(item) && HasKey(item, key)) {
            return GetItemNms(item).get(key);
        }
        return null;
    }

    public static String FromNBTString(NBTTagString format)
    {
        if(format == null) return null;
        return format.toString().replace("\"" , "");
    }

    static Boolean HasKey(ItemStack item, String key)
    {
        return GetItemNms(item).hasKey(key);
    }

    static Boolean HasTags(ItemStack item)
    {
        return !GetItemNms(item).isEmpty();
    }

    static NBTTagCompound GetItemNms(ItemStack item)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nmsCopyNBT = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        return nmsCopyNBT;
    }

    static ItemStack SetItemNms(ItemStack item, NBTTagCompound nms)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        nmsCopy.setTag(nms);
        return CraftItemStack.asBukkitCopy(nmsCopy);
    }
}
