package org.eternalreturn.eranimal.animals.behavs

import org.bukkit.entity.Entity
import org.bukkit.entity.Husk
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.ERAnimalAttackedByPlayerEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Battle : ERAnimalMonobehaviour<ERAnimalAttackedByPlayerEvent>() {
    var ajEntity: ERAJEntity? = null

    var animalState: AnimalState? = null

    enum class AnimalState {
        ATTACK,
        MOVE
    }

    public override fun start(event: ERAnimalAttackedByPlayerEvent) {
        ajEntity = eRAJEntity
        if (!ajEntity!!.isShown()) return
        animalState = AnimalState.MOVE
        (ajEntity!!.getActor() as Husk).setAI(true)
        println("attacked by player")
    }

    public override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        //for(MonobehaviourEvent event : eventList){
        //    if(event instanceof ERAnimalPlayerToFarAwayEvent){
        //        ajEntity.stopAnim();
        //        stopMonobehav();
        //    }
        //}

        //System.out.println("updating");

        //rotating == look at a target

        val actor = ajEntity!!.getActor() as Husk
        val target: Entity? = actor.target
        if (target == null) return

        val root = ajEntity!!.getRootEntity()
        val actorLoc = actor.location
        root.setRotation(actorLoc.yaw, root.location.pitch)

        //범위 내에 있는가?
        val isInDistance = isInDistance(3.0, actor, target)

        //상태 결정
        if (isInDistance && animalState != AnimalState.ATTACK) {
            animalState = AnimalState.ATTACK
        } else if (!ajEntity!!.isPlaying("attack")) {
            animalState = AnimalState.MOVE
        }

        //상태에 따라 행동 : MOVE
        if (animalState == AnimalState.MOVE) {
            actor.setAI(true)
            if (actor.velocity.isZero) {
                ajEntity!!.stopAnim()
                return
            }
            ajEntity!!.playAnim("move")
        }

        //상태에 따라 행동 : ATTACK
        if (animalState == AnimalState.ATTACK) {
            actor.setAI(false)
            ajEntity!!.playAnimForce("attack")
        }
    }
}
