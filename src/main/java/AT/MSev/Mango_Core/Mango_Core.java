package AT.MSev.Mango_Core;

import AT.MSev.Mango_Core.Blocks.BlockBaseHandler;
import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Items.Interactable.ItemInteractableHandler;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.TimedEvent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Mango_Core extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(BlockInstance.class, "BlockInstance");
        ConfigurationSerialization.registerClass(TimedEvent.class, "TimedEvent");
    }

    public static NamespacedKey key;
    public static File Folder;

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());

        MangoUtils.DebugMode = true;
        Folder = this.getDataFolder();

        TimedEvent.LoadState();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, TimedEvent.Tick, 0L, (60L*20L));

        getServer().getPluginManager().registerEvents(new ItemInteractableHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockBaseHandler(), this);
        getServer().getPluginManager().registerEvents(new Handler(this), this);
    }
    @Override
    public void onDisable() {

    }
}
