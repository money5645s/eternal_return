package org.eternalreturn.eranimal.managers.behavs

import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.managers.events.AnimalManageEvent
import org.eternalreturn.eranimal.managers.events.WaitForSummoning
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.properties.Delegates

class WaitForSummoningERAJEntities : Monobehaviour<WaitForSummoning>() {

    var waitingAnimalList: MutableList<ERAJEntity> by Delegates.notNull()

    override fun start(event: WaitForSummoning) {
        waitingAnimalList = event.listReference;
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        for(erAJEntity in waitingAnimalList){
            if(erAJEntity.actor == null){ //HUSK가 하나라도 null이라면
                return; //묻지도 따지지도 말고 그냥 return + 다음 틱까지 대기
            }
        }
        
        //모두 소환되었다면
        actor.submitEvent(AnimalManageEvent(waitingAnimalList));
        stopMonobehav();
    }
}