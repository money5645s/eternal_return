package org.eternalreturn.eranimal.manager.behavs

import org.dpengine.monobehaviour.Monobehaviour
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.eranimal.manager.events.RemoveAllERAnimals
import org.eternalreturn.system.EREngine

class DetectingPlayerEvent : MonobehaviourEvent


/**
 * 해당 영역 (보다 좀 더 큰 범위 내에) 플레이어가 존재하는 경우 해당 위치의 야생동물을 플레이어한테 보여줄 지 결정한다.
 * */
class DetectingPlayersInRange : Monobehaviour<DetectingPlayerEvent>() {

    public override fun start(event: DetectingPlayerEvent) {    }

    var isShowed = false;
    public override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {

        val manager = actor as ERAnimalManager;

        var isInDistance = false;

        val playerList = (dpEngine as EREngine).players.curQueue;

        for (player in playerList) {
            val avg = vec3(manager.px, manager.py, manager.pz);
            val pos = player.getPosition();
            val dis = magnitudeSqr(avg - pos);
            if(dis <= 128 * 128){
                isInDistance = true;
                break;
            }
        }

        if(isInDistance && !isShowed){
            actor.submitEvent(AnimalManageEvent());
            isShowed = true;
        }else if(!isInDistance && isShowed){
            actor.submitEvent(RemoveAllERAnimals());
            isShowed = false;
        }

    }


}
