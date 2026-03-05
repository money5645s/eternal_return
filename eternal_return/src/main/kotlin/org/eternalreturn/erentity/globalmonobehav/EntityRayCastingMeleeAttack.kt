package org.eternalreturn.erentity.globalmonobehav

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackedEvent
import org.eternalreturn.erentity.events.EREntityRayCastEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Vector3

class EntityRayCastingMeleeAttack : EREntityMonobehaviour<EREntityRayCastEvent>() {

    override fun start(event: EREntityRayCastEvent) {
        stopMonobehav();

        val erEntity = getEREntity();
        var minDist = erEntity.maxRange * erEntity.maxRange; //제곱해서 비교
        var closestTarget: EREntity? = null;

        //(getEREntity() as ConsoleCommandSender).sendMessage(this.javaClass.toString() + " is dispatched, list length is " + event.hitEntities.size)

        for(e in event.hitList){
            if(event.shooter === e){
                continue;
            }

            val ePos : Vector3 = e.getPosition();
            val pPos : Vector3 = erEntity.getPosition();
            val distVec = ePos - pPos;
            val distanceSqr = magnitudeSqr(distVec);

            if(distanceSqr < minDist){
                minDist = distanceSqr;
                closestTarget = e;
                println("Melee-attacked to -> " + e.javaClass)
            }
        }

        if(closestTarget == null){
            return;
        }

        closestTarget.submitEvent(EREntityAttackedEvent(actor as EREntity))
        event.shooter.submitEvent(CharacterAttackEvent(event.shooter, closestTarget))
        val shootPlayer = event.shooter.entity as Player;
        closestTarget.damage(shootPlayer.getAttribute(org.bukkit.attribute.Attribute.ATTACK_DAMAGE)!!.value, event.shooter as ERPlayer);
    }



    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }

}