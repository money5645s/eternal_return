package org.eternalreturn.erentity.globalmonobehav

import org.bukkit.damage.DamageType
import org.bukkit.entity.Player
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.erentity.events.EREntityRayCastEvent
import org.eternalreturn.erplayer.DPlayer
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.dpengine.geometry.Vector3
import kotlin.math.min

class EntityRayCastingMeleeAttack : EREntityMonobehaviour<EREntityRayCastEvent>() {

    override fun start(event: EREntityRayCastEvent) {
        stopMonobehav();

        val DPEntity = actor as DPEntity
        var minDist = DPEntity.maxRange * DPEntity.maxRange; //제곱해서 비교
        var closestTarget: DPEntity? = null;

        //(getEREntity() as ConsoleCommandSender).sendMessage(this.javaClass.toString() + " is dispatched, list length is " + event.hitEntities.size)

        for(hit in event.hitList){

            if(event.shooter === hit.DPEntity){
                continue;
            }

            val ePosMin : Vector3 = vec3(hit.xMin, hit.yMin, hit.zMin)
            val ePosMax : Vector3 = vec3(hit.xMax, hit.yMax, hit.zMax)
            val pPos : Vector3 = DPEntity.getPosition();
            val distanceSqr = min(magnitudeSqr(pPos - ePosMin), magnitudeSqr(pPos - ePosMax));

            if(distanceSqr < minDist){
                minDist = distanceSqr;
                closestTarget = hit.DPEntity;
                //println("Melee-attacked to -> " + e.javaClass)
            }
        }

        if(closestTarget == null){
            return;
        }
        val shooter = event.shooter
        val shootPlayer = shooter.entity as Player;

        shooter.submitEvent(EREntityAttackEvent(shooter, closestTarget))
        closestTarget.submitEvent(EREntityDamagedEvent(actor as DPEntity))

        val attackDamage = shootPlayer.getAttribute(org.bukkit.attribute.Attribute.ATTACK_DAMAGE)!!.value;
        val strength: Float = (shooter as DPlayer).attackCooldown


        //println("damage : ${attackDamage * strength * strength} with strength : $strength")


        closestTarget.damage(kotlin.math.max(attackDamage * strength * strength, 1.0), event.shooter, DamageType.ARROW);
    }



    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }

}