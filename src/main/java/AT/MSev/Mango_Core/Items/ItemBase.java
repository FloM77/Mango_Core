package AT.MSev.Mango_Core.Items;

import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NBTManager;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ItemBase {
    public String Name;
    public ItemStack Physical;
    public static ArrayList<ItemBase> All = new ArrayList<ItemBase>();
    public ItemBase(String name, Material appearance)
    {
        Name = name;
        Physical = new ItemStack(appearance);
        SetTag("Custom", new NBTTagString("Base"));
        SetTag("Name", new NBTTagString(Name));
        All.add(this);
        Rename(Name);
    }

    public void Rename(String newName)
    {
        MangoUtils.ItemRename(Physical, newName);
    }

    public void SetLore(ArrayList<String> newLore)
    {
        MangoUtils.ItemRelore(Physical, newLore);
    }

    public void SetTag(String key, NBTBase nbt)
    {
        Physical = NBTManager.AddItemNBT(Physical, key, nbt);
    }

    public void RemoveTag(String key)
    {
        Physical = NBTManager.RemoveItemNBT(Physical, key);
    }

    Random random = new Random();
    public void Give(Player p, Boolean stackable, int amount)
    {
        for(int i=0;i<amount;i++) {
            if (!stackable) {
                int randomID = random.nextInt(Integer.MAX_VALUE);
                SetTag("Unstackable", new NBTTagInt(randomID));
            }
            p.getInventory().addItem(Physical);
        }
    }

    public static ItemBase Get(String exactName)
    {
        for(ItemBase eb: ItemBase.All)
        {
            if(eb.Name.equalsIgnoreCase(exactName))
            {
                return eb;
            }
        }
        return null;
    }

    public static ItemBase Get(ItemStack item)
    {
        for(ItemBase cib:All)
        {
            if(CompareWithItem(cib, item))
            {
                return cib;
            }
        }
        return null;
    }

    static boolean CompareWithItem(ItemBase ebase, ItemStack item)
    {
        if(NBTManager.GetTag(item, "Custom") != null) {
            if(NBTManager.FromNBTString((NBTTagString) NBTManager.GetTag(item, "Name")).equalsIgnoreCase(ebase.Name))
            {
                return true;
            }
        }
        return false;
    }
}
