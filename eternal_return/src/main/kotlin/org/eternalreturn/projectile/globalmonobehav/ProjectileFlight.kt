package org.eternalreturn.projectile.globalmonobehav

import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.World
import org.eternalreturn.projectile.ERProjectile
import org.eternalreturn.projectile.events.ProjectileInitEvent
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class ProjectileFlight : Monobehaviour<ProjectileInitEvent>(){

    val world: World = PluginInstance.getServerInstance().server.worlds.first();
    val startPoint = Location(world, 0.0, 0.0, 0.0)

    var life : Long = 0;
    override fun start(event: ProjectileInitEvent) {
        val projectile = actor as ERProjectile;
        life = System.currentTimeMillis() + 1000 * 10; //10초 날아간다
        startPoint.x = projectile.x;
        startPoint.y = projectile.y;
        startPoint.z = projectile.z;
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val projectile = actor as ERProjectile;

        if(life < System.currentTimeMillis()){
            projectile.remove();
            stopMonobehav();
            return;
        }

        val x = projectile.x;
        val y = projectile.y;
        val z = projectile.z;

        projectile.x = x + projectile.dx * 0.05; // x(t+dt) = x + v * dt
        projectile.y = y + projectile.dy * 0.05; // x(t+dt) = x + v * dt
        projectile.z = z + projectile.dz * 0.05; // x(t+dt) = x + v * dt

        for(i in 0 until 10){
            world.spawnParticle(Particle.DUST, startPoint, 1, Particle.DustOptions(Color.SILVER, 1.0f));
            startPoint.x += i * (projectile.dx * 0.05);
            startPoint.y += i * (projectile.dy * 0.05);
            startPoint.z += i * (projectile.dz * 0.05);
        }

        startPoint.x = projectile.x;
        startPoint.y = projectile.y;
        startPoint.z = projectile.z;

        projectile.dy = (projectile.dy - ((9.80665) * 0.5) * 0.05);  //(v0 - gt)

    }

}