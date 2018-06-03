package AT.MSev.Mango_Core.Zones;

import org.bukkit.Location;
import org.bukkit.World;

public class ZoneSetting {
    Location Quad1;
    Location Quad2;

    public ZoneSetting(Location q1, Location q2)
    {
        Quad1 = q1;
        Quad2 = q2;
    }

    public Boolean IsIn(Location location)
    {
        LocationSet X = BothX();
        LocationSet Y = BothY();
        LocationSet Z = BothZ();

        if(
                location.getX() <= X.Greater && location.getX() >= X.Lesser &&
                        location.getY() <= Y.Greater && location.getY() >= Y.Lesser &&
                        location.getZ() <= Z.Greater && location.getZ() >= Z.Lesser
                )
        {
            return true;
        }
        return false;
    }

    public double ZoneXSize()
    {
        return (BothX().Greater - BothX().Lesser);
    }

    public double ZoneYSize()
    {
        return (BothY().Greater - BothY().Lesser);
    }

    public double ZoneZSize()
    {
        return (BothZ().Greater - BothZ().Lesser);
    }

    public World getWorld()
    {
        return Quad1.getWorld();
    }

    public Location RelLocLesser(double x, double y, double z)
    {
        return new Location(getWorld(), BothX().Lesser + x, BothY().Lesser + y, BothZ().Lesser + z);
    }

    public Location RelLocGreater(double x, double y, double z)
    {
        return new Location(getWorld(), BothX().Greater + x, BothY().Greater + y, BothZ().Greater + z);
    }

    LocationSet BothX()
    {
        return new LocationSet(Quad1.getX(), Quad2.getX());
    }

    LocationSet BothY()
    {
        return new LocationSet(Quad1.getY(), Quad2.getY());
    }

    LocationSet BothZ()
    {
        return new LocationSet(Quad1.getZ(), Quad2.getZ());
    }
}

class LocationSet
{
    double Greater;
    double Lesser;

    public LocationSet(double v1, double v2)
    {
        if(v1 > v2) {
            Greater = v1;
            Lesser = v2;
        }
        else
        {
            Lesser = v1;
            Greater = v2;
        }
    }
}