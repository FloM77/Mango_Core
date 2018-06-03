package AT.MSev.Mango_Core.Blocks;

import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Core.Mango_Core;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;

public class BlockBase extends ItemBase {
    public ArrayList<BlockInstance> Instances = new ArrayList<BlockInstance>();
    public static ArrayList<BlockBase> BlockTypes = new ArrayList<BlockBase>();
    public BlockBase(String name, Material appearance)
    {
        super(name, appearance);
        SetTag("Custom", new NBTTagString("Block"));
        BlockTypes.add(this);

        LoadState();
    }

    public void OnPlace(BlockPlaceEvent e)
    {
        MangoUtils.Log("Triggered " + Name + " place");

        BlockInstance placed = new BlockInstance(e.getBlockPlaced().getLocation(), e.getPlayer().getUniqueId().toString());
        Instances.add(placed);
        SaveState();
    }

    public void OnBreak(BlockBreakEvent e, BlockInstance instance)
    {
        MangoUtils.Log("Triggered " + Name + " break");

        Block b = e.getBlock();
        World w = b.getWorld();

        Instances.remove(instance);
        SaveState();

        e.setCancelled(true);

        w.getBlockAt(b.getLocation()).setType(Material.AIR);
        w.dropItemNaturally(b.getLocation(), new ItemStack(Physical));
    }

    void SaveState()
    {
        YamlConfiguration blockConfig = new YamlConfiguration();
        blockConfig.set("Blocks." + Name + ".Instances", Instances);
        try {
            blockConfig.save(new File(Mango_Core.Folder, "BlockConfig"));
        }
        catch (Exception ex) {
            MangoUtils.Log(ex.getMessage());
        }
    }

    void LoadState()
    {
        try {
            YamlConfiguration blockConfig = YamlConfiguration.loadConfiguration(new File(Mango_Core.Folder, "BlockConfig"));

            ArrayList<BlockInstance> loadedBlocks = (ArrayList<BlockInstance>)blockConfig.get("Blocks." + Name + ".Instances");
            if(loadedBlocks!=null) Instances = loadedBlocks;

        } catch(Exception ex) { MangoUtils.Log(ex.getMessage()); }
    }
}
