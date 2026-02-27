package org.eternalreturn.eranimal.animals.behavs

import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.ERAnimalAttackedByPlayerEvent
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Idle : ERAnimalMonobehaviour<IdleEvent>() {
    override fun start(event: IdleEvent) {

    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        
        //플레이어에게 공격받았을 경우
        for(event in eventList){
            if(event is ERAnimalAttackedByPlayerEvent) {
                stopMonobehav();
                break;
            }
        }

        val animal = eRAnimal!!.aJEntity;
        if(animal.actor.passengers.isEmpty()){
            animal.actor.addPassenger(animal.rootEntity)
        }

        //루트 엔티티가 지정된 방향을 가리키도록 하여 히트박스의 각도를 정확히 맞추도록 함.
        //
        val animalLoc = animal.location;
        animal.rootEntity.setRotation(animalLoc.yaw, animalLoc.pitch);

        //플레이어가 하나라도 16칸 이내에 있는 경우 -> Ready & stopMonobehav
        val engine = dpEngine as EREngine
        for(p in engine.players.curQueue){

            val pos = vec3(animal.location.x, animal.location.y, animal.location.z)
            if(magnitudeSqr(pos - p.getPosition()) <= 16 * 16){
                eRAJEntity!!.playAnim("ready")
                break;
            }
        }

    }
}