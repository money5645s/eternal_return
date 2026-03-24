package org.eternalreturn.eranimal.manager.behavs

import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.eranimal.manager.events.AnimalManageEvent
import org.eternalreturn.eranimal.manager.events.DetectingPlayerEvent
import org.eternalreturn.util.dpengine.monobehaviour.Monobehaviour
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class WaitForSummoningEvent() : MonobehaviourEvent

/**
 * - 해당 manager가 관리하는 Animal들이 모두 소환되었는지 지속적으로 확인.
 * - 모두 성공적으로 소환된 경우, 이벤트 전달 및 stopMonobehav()
 * */
class WaitForSummoning : Monobehaviour<WaitForSummoningEvent>() {
    override fun start(event: WaitForSummoningEvent) {
        val manager = actor as ERAnimalManager;
        for(animal in manager.entities){
            animal.summon(0.0, 2.0, 0.0);
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val manager = actor as ERAnimalManager;

        //하나라도 소환이 덜 된 경우 해당 함수 바로 종료
        for(animal in manager.entities){
            if(!animal.isValid){
                return;
            }
        }
        
        //(AJEntity, ERAniaml) 쌍 모두 안 보이게 설정
        for(animal in manager.entities){
            animal.setNotBeShown()
        }
        
        //해당 Monobehaviour을 종료하면서, 다른 Monobehav들을 활성화
        manager.submitEvent(DetectingPlayerEvent());
        manager.submitEvent(AnimalManageEvent());
        stopMonobehav();

    }


}