package org.eternalreturn.eranimal.animals.behavs

import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class Idle : ERAnimalMonobehaviour<IdleEvent>() {
    override fun start(event: IdleEvent) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {

        val animal = actor as ERAnimal;
        val ajEntity = animal.aJEntity;
        //플레이어에게 공격받았을 경우
        if(eventMap[EREntityDamagedEvent::class.java] != null){
            ajEntity.stopAnim();
            stopMonobehav();
            return;
        }

        //플레이어가 하나라도 16칸 이내에 있는 경우 -> Ready & stopMonobehav
        val engine = dpEngine as EREngine
        for(p in engine.players.curQueue){
            //생성 좌표
            val pos = vec3(ajEntity.spawnLocation.x, ajEntity.spawnLocation.y, ajEntity.spawnLocation.z)
            if(magnitudeSqr(pos - p.getPosition()) <= 16 * 16){
                ajEntity.playAnim("ready");
                return;
            }
        }
    }
}