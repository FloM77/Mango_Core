package AT.MSev.Mango_Core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Seconds;

import java.util.HashMap;

public class Time {
    static HashMap<String, DateTime> TimeMap = new HashMap<String, DateTime>();
    public enum Status {
        COMPLETED, RUNNING, UNMAPPED
    }

    public static void Entry(String key, int milliDelay)
    {
        MutableDateTime time = DateTime.now().toMutableDateTime();
        time.addMillis(milliDelay);

        TimeMap.put(key, time.toDateTime());
    }

    public static void EntryMinutes(String key, int minuteDelay)
    {
        MutableDateTime time = DateTime.now().toMutableDateTime();
        time.addMinutes(minuteDelay);

        TimeMap.put(key, time.toDateTime());
    }

    public static Period UntilCompletion(String key)
    {
        if(!TimeMap.containsKey(key)) return null;

        DateTime target = TimeMap.get(key);
        Period period = new Period(DateTime.now(), target);
        return period;
    }

    public static Status GetStatus(String key)
    {
        if(!TimeMap.containsKey(key)) return Status.UNMAPPED;

        DateTime target = TimeMap.get(key);
        if(DateTime.now().isBefore(target)) {
            return Status.RUNNING;
        }
        else
        {
            return Status.COMPLETED;
        }
    }
}
