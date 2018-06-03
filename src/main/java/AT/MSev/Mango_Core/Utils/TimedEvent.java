package AT.MSev.Mango_Core.Utils;

import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Mango_Core;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimedEvent implements ConfigurationSerializable {
    DateTime Trigger;
    Period Repeat;
    TimedCall TriggeredCall;

    public static ArrayList<TimedEvent> All = new ArrayList<TimedEvent>();

    public TimedEvent(DateTime trigger, Period period, Class<? extends TimedCall> call)
    {
        Trigger = trigger;
        try {
            TriggeredCall = call.newInstance();
        } catch (Exception ex) { MangoUtils.Log(ex.getMessage()); }
        Repeat = period;
        All.add(this);
        SaveState();
    }

    public static Runnable Tick = new Runnable() {
        public void run() {
            for(TimedEvent te: TimedEvent.All)
            {
                if(DateTime.now().isAfter(te.Trigger))
                {
                    te.TriggeredCall.Run();

                    te.Trigger = DateTime.now().plus(te.Repeat);
                    SaveState();
                }

            }
        }
    };
    //    TO SERIALIZE
    //    DateTime Trigger;
    //    String Call;
    //    Period Repeat;
    //    Class<?> CallList;
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("trigger", Trigger.toString());
        result.put("period", Repeat.toString());
        result.put("call", TriggeredCall.getClass().getName());
        return result;
    }

    public static TimedEvent deserialize(Map<String, Object> args) {
        DateTime trigger = DateTime.parse((String)args.get("trigger"));
        try {
            Class<? extends TimedCall> call = (Class<? extends TimedCall>)Class.forName(((String) args.get("call")));
            Period period = Period.parse((String)args.get("period"));
            TimedEvent ret = new TimedEvent(trigger, period, call);
            return ret;
        } catch (Exception ex) { MangoUtils.Log(ex.getMessage()); }
        return null;
    }

    public static void SaveState()
    {
        YamlConfiguration timedConfig = new YamlConfiguration();
        timedConfig.set("Timed.Events", All);
        try {
            timedConfig.save(new File(Mango_Core.Folder, "TimedConfig"));
        }
        catch (Exception ex) {
            MangoUtils.Log(ex.getMessage());
        }
    }

    public static void LoadState()
    {
        try {
            YamlConfiguration timedConfig = YamlConfiguration.loadConfiguration(new File(Mango_Core.Folder, "TimedConfig"));

            ArrayList<TimedEvent> loadedEvents = (ArrayList<TimedEvent>)timedConfig.get("Timed.Events");
            if(loadedEvents!=null) All = loadedEvents;

        } catch(Exception ex) { MangoUtils.Log(ex.getMessage()); }
    }

    public static Boolean IsLoaded(Class<? extends TimedCall> call)
    {
        for(TimedEvent te : TimedEvent.All)
        {
            if(te.TriggeredCall.getClass().equals(call))
            {
                return true;
            }
        }
        return false;
    }
}
