package org.eternalreturn.erentity.globalmonobehav

import org.bukkit.entity.Player
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.erentity.events.EREntityRayCastEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Vector3
import kotlin.math.min

class EntityRayCastingMeleeAttack : EREntityMonobehaviour<EREntityRayCastEvent>() {

    override fun start(event: EREntityRayCastEvent) {
        stopMonobehav();

        val erEntity = actor as EREntity
        var minDist = erEntity.maxRange * erEntity.maxRange; //제곱해서 비교
        var closestTarget: EREntity? = null;

        //(getEREntity() as ConsoleCommandSender).sendMessage(this.javaClass.toString() + " is dispatched, list length is " + event.hitEntities.size)

        for(hit in event.hitList){

            if(event.shooter === hit.erEntity){
                continue;
            }

            val ePosMin : Vector3 = vec3(hit.xMin, hit.yMin, hit.zMin)
            val ePosMax : Vector3 = vec3(hit.xMax, hit.yMax, hit.zMax)
            val pPos : Vector3 = erEntity.getPosition();
            val distanceSqr = min(magnitudeSqr(pPos - ePosMin), magnitudeSqr(pPos - ePosMax));

            if(distanceSqr < minDist){
                minDist = distanceSqr;
                closestTarget = hit.erEntity;
                //println("Melee-attacked to -> " + e.javaClass)
            }
        }

        if(closestTarget == null){
            return;
        }
        val shooter = event.shooter
        val shootPlayer = shooter.entity as Player;

        shooter.submitEvent(EREntityAttackEvent(shooter, closestTarget))
        closestTarget.submitEvent(EREntityDamagedEvent(actor as EREntity))

        val attackDamage = shootPlayer.getAttribute(org.bukkit.attribute.Attribute.ATTACK_DAMAGE)!!.value;
        val strength: Float = (shooter as ERPlayer).attackCooldown
        //println("strength : $strength")

        closestTarget.damage(attackDamage * strength, event.shooter);
    }



    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }

}