package AT.MSev.Mango_Core.Entity.EntityNPC;


import AT.MSev.Mango_Core.Entity.IStayAtLocation;
import AT.MSev.Mango_Core.Entity.PathfinderStayAtLocation;
import AT.MSev.Mango_Core.Mango_Core;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.UUID;

public class VillagerNPC extends EntityVillager implements Listener, IStayAtLocation {

    Location stayAt;
    public static ArrayList<VillagerNPC> All = new ArrayList<VillagerNPC>();

    public VillagerNPC(World world) {
        super(world);
        this.persistent = true;
        All.add(this);
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

    public Boolean OnInteract(PlayerInteractEntityEvent e)
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

    /*
     * Save NBT data
     */
    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);

        nbttagcompound.setString("stayAtWorld", stayAt.getWorld().getUID().toString());
        nbttagcompound.setDouble("stayAtX", stayAt.getX());
        nbttagcompound.setDouble("stayAtY", stayAt.getY());
        nbttagcompound.setDouble("stayAtZ", stayAt.getZ());
    }

    /*
     * Load NBT data
     */
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        stayAt = new Location(
        Bukkit.getWorld(UUID.fromString( nbttagcompound.getString("stayAtWorld"))),
        nbttagcompound.getDouble("stayAtX"),
        nbttagcompound.getDouble("stayAtY"),
        nbttagcompound.getDouble("stayAtZ")
        );
    }
}
