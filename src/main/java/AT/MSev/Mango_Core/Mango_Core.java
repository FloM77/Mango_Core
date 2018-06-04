package AT.MSev.Mango_Core;

import AT.MSev.Mango_Core.Blocks.BlockBaseHandler;
import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Entity.EntityNPC.NPCHandler;
import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Items.Interactable.ItemInteractableHandler;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.Mango_Core.Utils.TimedEvent;
import AT.MSev.Mango_Core.Zones.Interactable.ZoneInteractable;
import AT.MSev.Mango_Core.Zones.ZoneBase;
import net.minecraft.server.v1_12_R1.BlockPressurePlateBinary;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Mango_Core extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(BlockInstance.class, "BlockInstance");
        ConfigurationSerialization.registerClass(TimedEvent.class, "TimedEvent");
        ConfigurationSerialization.registerClass(ZoneBase.class, "ZoneBase");
        ConfigurationSerialization.registerClass(ZoneInteractable.class, "ZoneInteractable");
    }

    public static NamespacedKey key;
    public static File Folder;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        key = new NamespacedKey(this, this.getDescription().getName());

        MangoUtils.DebugMode = true;
        Folder = this.getDataFolder();

        TimedEvent.LoadState();
        ZoneBase.LoadStates();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, TimedEvent.Tick, 0L, (60L*20L));

        getServer().getPluginManager().registerEvents(new ItemInteractableHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockBaseHandler(), this);
        getServer().getPluginManager().registerEvents(new Handler(), this);
        getServer().getPluginManager().registerEvents(new NPCHandler(), this);

        NMSUtils.registerEntity("npc_villager", NMSUtils.Type.VILLAGER, VillagerNPC.class, false);
    }
    @Override
    public void onDisable() {

    }
}
