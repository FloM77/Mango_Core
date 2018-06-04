package AT.MSev.Mango_Core.Entity;

import AT.MSev.Mango_Core.Utils.MangoUtils;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.PathfinderGoal;

public class PathfinderStayAtLocation<T extends EntityInsentient & IStayAtLocation> extends PathfinderGoal {

    T Affected;
    double RelocSpeed;

    public PathfinderStayAtLocation(T affected, double relocSpeed)
    {
        Affected = affected;
        RelocSpeed = relocSpeed;
    }

    @Override
    public boolean a() {
        if(Affected.StayAt()!=null) {
            if (Affected.getBukkitEntity().getLocation().distance((Affected).StayAt()) > 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void c() {
        Affected.setPosition(Affected.StayAt().getX(),Affected.StayAt().getY(),Affected.StayAt().getZ());
    }
}
