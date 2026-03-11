package org.eternalreturn.projectile.globalmonobehav

import io.papermc.paper.math.Position
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityAttackedEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.projectile.ERProjectile
import org.eternalreturn.projectile.events.ProjectileHitEvent
import org.eternalreturn.projectile.events.ProjectileRayCastEvent
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Vector3


class ProjectileRayCastingAttack : Monobehaviour<ProjectileRayCastEvent>() {

    val world: World = PluginInstance.getServerInstance().server.worlds.first();
    val startPoint = Location(world, 0.0, 0.0, 0.0)

    override fun start(event: ProjectileRayCastEvent) {
        val projectile = actor as ERProjectile;
        //각 축에 대한 속도에 0.05를 곱해서 m/ticks -> m/s 단위로 변경함.
        //이후 현재 위치에서 다음 틱(t + 0.05)에 도달할 위치의 길이를 정확히 계산
        //val prevPos = vec3()
        val nextPos = vec3(projectile.dx * 0.05, projectile.dy * 0.05, projectile.dz * 0.05);
        var minDist = magnitudeSqr(nextPos)
        var closestTarget: EREntity? = null;

        //val result = getRayTraceResult(projectile);
        //result?.hitPosition

        for(e in event.hitList){
            if(e === projectile.owner)continue;
            val ePos : Vector3 = e.getPosition();
            val pPos : Vector3 = vec3(projectile.x, projectile.y, projectile.z);
            val distVec = ePos - pPos;
            val distanceSqr = magnitudeSqr(distVec);

            if(distanceSqr < minDist){
                minDist = distanceSqr;
                closestTarget = e;
                //println("Projectile-attacked to -> " + e.javaClass)
            }
        }

        if(closestTarget == null){
            return;
        }

        closestTarget.submitEvent(EREntityAttackedEvent(projectile.owner as EREntity))
        projectile.submitEvent(ProjectileHitEvent(closestTarget))
        closestTarget.damage(projectile.damage , projectile.owner);
        projectile.remove();
        stopMonobehav();
    }

    private fun getRayTraceResult(projectile : ERProjectile) : RayTraceResult?{
        startPoint.x = projectile.x;
        startPoint.y = projectile.y;
        startPoint.z = projectile.z;
        val vx = projectile.dx * 0.05;
        val vy = projectile.dy * 0.05;
        val vz = projectile.dz * 0.05;
        val direction = Vector(vx, vy, vz)
        return world.rayTraceBlocks(startPoint, direction, direction.length());
    }


    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }

}