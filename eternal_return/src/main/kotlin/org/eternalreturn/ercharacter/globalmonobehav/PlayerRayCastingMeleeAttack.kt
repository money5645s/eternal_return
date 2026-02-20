package org.eternalreturn.ercharacter.globalmonobehav

import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.animals.events.ERAnimalAttackedByPlayerEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.ercharacter.event.CharacterRayCastEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Vector3

class PlayerRayCastingMeleeAttack : ERCharacterMonobehaviour<CharacterRayCastEvent>() {

    override fun start(event: CharacterRayCastEvent) {

        var minDist = 16.0
        var closestTarget: EREntity? = null;
        val character = getERCharacter();

        getPlayer().sendMessage(this.javaClass.toString() + " is dispatched, list length is " + event.hitEntities.size)

        for(e in event.hitEntities){

            val ePos : Vector3 = e.getPosition();
            val pPos : Vector3 = this.getERCharacter().getPosition();

            val distVec = ePos - pPos;

            val distanceSqr = magnitudeSqr(distVec);

            if(distanceSqr < minDist){
                minDist = distanceSqr;
                closestTarget = e;
                getERPlayer().sendMessage("Melee-attacked to -> " + e.javaClass)
            }
        }

        if(closestTarget == null){
            return;
        }

        if(closestTarget is ERAnimal){
            getERPlayer().sendMessage("Event send to " + closestTarget.javaClass)
            closestTarget.submitEvent(ERAnimalAttackedByPlayerEvent())
        }

        character.submitEvent(CharacterAttackEvent(character, closestTarget))
    }



    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav();
    }

}