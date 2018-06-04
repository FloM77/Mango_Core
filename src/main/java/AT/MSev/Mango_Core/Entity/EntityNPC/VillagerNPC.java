package AT.MSev.Mango_Core.Entity.EntityNPC;


import AT.MSev.Mango_Core.Entity.IStayAtLocation;
import AT.MSev.Mango_Core.Entity.PathfinderStayAtLocation;
import AT.MSev.Mango_Core.Mango_Core;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerNPC extends EntityVillager implements Listener, IStayAtLocation {

    Location stayAt;

    public VillagerNPC(World world) {
        super(((CraftWorld)world).getHandle());
        GetBukkit().getServer().getPluginManager().registerEvents(this, Mango_Core.plugin);
    }

    @Override
    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 20.0F));
        this.goalSelector.a(2, new PathfinderStayAtLocation(this, 4));
    }

    Entity GetBukkit()
    {
        return this.getBukkitEntity();
    }

    @EventHandler
    protected Boolean OnInteract(PlayerInteractEntityEvent e)
    {
        if(e.getRightClicked().equals(GetBukkit()))
        {
            e.setCancelled(true);
           return true;
        }
        return false;
    }

    public Location StayAt() {
        return stayAt;
    }

    public void Spawn(Location location)
    {
        this.setPosition(location.getX(), location.getY(), location.getZ());
        ((CraftWorld)(location.getWorld())).addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        stayAt = location;
    }
}
