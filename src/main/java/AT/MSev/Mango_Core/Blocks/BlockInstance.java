package AT.MSev.Mango_Core.Blocks;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockInstance implements ConfigurationSerializable {
    Location Logical;
    String OwnerId;

    public BlockInstance(Location logical, String ownerid)
    {
        Logical = logical;
        OwnerId = ownerid;
    }

    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("logical", Logical);
        result.put("ownerid", OwnerId);
        return result;
    }

    public static BlockInstance deserialize(Map<String, Object> args) {
        Location logical = ((Location)args.get("logical"));
        String ownerid = ((String)args.get("ownerid"));
        BlockInstance ret = new BlockInstance(logical, ownerid);
        return ret;
    }
}
