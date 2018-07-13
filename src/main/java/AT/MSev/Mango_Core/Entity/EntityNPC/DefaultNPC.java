package AT.MSev.Mango_Core.Entity.EntityNPC;

import AT.MSev.Mango_Core.Mango_Core;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Default extends EntityZombie implements Listener {
    public Default(World world) {
        super(world);
        Bukkit.getServer().getPluginManager().registerEvents(this, Mango_Core.plugin);
    }

    Entity GetBukkit()
    {
        return this.getBukkitEntity();
    }

    @EventHandler
    protected Boolean OnKilled(EntityDeathEvent e)
    {
        if(e.getEntity().equals(GetBukkit()))
        {
            return true;
        }
        return true;
    }

    @EventHandler
    protected Boolean OnHit(EntityDamageByEntityEvent e)
    {
        if(e.getDamager().equals(GetBukkit()))
        {
            return true;
        }
        return false;
    }

    public void Spawn(Location location)
    {
        this.setPosition(location.getX(), location.getY(), location.getZ());
        ((CraftWorld)(location.getWorld())).addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
