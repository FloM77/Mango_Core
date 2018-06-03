package AT.MSev.Mango_Core.Zones;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Mango_Core;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.annotation.Nullable;

public class ZoneBase implements ConfigurationSerializable {

    public static ArrayList<ZoneBase> All = new ArrayList<ZoneBase>();

    public ZoneSetting Setting;

    public ZoneBase(Location bound1, Location bound2)
    {
        Setting = new ZoneSetting(bound1, bound2);
        All.add(this);
        MangoUtils.Log("New Zone in " + Setting.Quad1.toString() + " to" + Setting.Quad2.toString());
    }

    Boolean toDelete = false;
    public void Remove()
    {
        Setting.Quad1 = new Location(Setting.Quad1.getWorld() , 0, 0, 0);
        Setting.Quad2 = new Location(Setting.Quad2.getWorld() , 0, 0, 0);
        toDelete = true;

        SaveStates();
    }

    public static void SaveStates()
    {
        YamlConfiguration ZoneConfig = new YamlConfiguration();
        for (ZoneBase zb : All) {
            if(zb.toDelete)
                All.remove(zb);
        }

        ZoneConfig.set("Zones.Instances", All);

        try {
            ZoneConfig.save(new File(Mango_Core.Folder, "ZoneConfig"));
        } catch(Exception ex) {
            MangoUtils.Log(ex.getMessage());
        }
    }
    public static void LoadStates()
    {
        YamlConfiguration ZoneConfig = YamlConfiguration.loadConfiguration(new File(Mango_Core.Folder, "ZoneConfig"));
        if(ZoneConfig.contains("Zones.Instances"))
        {
            All = (ArrayList<ZoneBase>)ZoneConfig.get("Zones.Instances");
        }
    }

    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("q1", Setting.Quad1);
        result.put("q2", Setting.Quad2);
        return result;
    }

    public static ZoneBase deserialize(Map<String, Object> args) {
        Location q1 = ((Location)args.get("q1"));
        Location q2 = ((Location)args.get("q2"));
        ZoneBase ret = new ZoneBase(q1, q2);
        return ret;
    }
}
