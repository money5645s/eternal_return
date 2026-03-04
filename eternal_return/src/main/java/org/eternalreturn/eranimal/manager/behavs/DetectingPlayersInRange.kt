package org.eternalreturn.eranimal.manager.behavs

import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.eranimal.manager.events.AnimalManageEvent
import org.eternalreturn.eranimal.manager.events.DetectingPlayerEvent
import org.eternalreturn.eranimal.manager.events.RemoveAllERAnimals
import org.eternalreturn.eranimal.manager.events.WaitForSummoning
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

/**
 * 해당 영역 (보다 좀 더 큰 범위 내에) 플레이어가 존재하는 경우 해당 위치의 야생동물을 플레이어한테 보여줄 지 결정한다.
 * */
class DetectingPlayersInRange : Monobehaviour<DetectingPlayerEvent>() {

    var px: Double = 0.0
    var py: Double = 0.0
    var pz: Double = 0.0

    public override fun start(event: DetectingPlayerEvent) {
        val actor = actor as ERAnimalManager

        val size = actor.entities.size.toDouble()
        //야생동물들 위치의 평균을 구함
        for (e in actor.entities) {
            val loc = e.getLocation()
            px += loc.x
            py += loc.y
            pz += loc.z
        }
        px /= size
        py /= size
        pz /= size

        println("Middle point is : $px $py $pz")

    }

    var isShowed = false;
    public override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {

        var isInDistance = false;

        val playerList = (dpEngine as EREngine).players.curQueue;

        for (player in playerList) {
            val avg = vec3(px, py, pz);
            val pos = player.getPosition()
            val dis = magnitudeSqr(avg - pos);
            if(dis <= 128 * 128){
                isInDistance = true;
                break;
            }
        }

        if(isInDistance && !isShowed){
            actor.submitEvent(AnimalManageEvent())
            isShowed = true;
        }else if(!isInDistance && isShowed){
            actor.submitEvent(RemoveAllERAnimals())
            isShowed = false;
        }

    }


}
