package org.eternalreturn.eranimal.manager.behavs

import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent


class InitializeEvent : MonobehaviourEvent;

/**
 * - 범위 내(range) 플레이어가 존재하는지 지속적으로 확인
 * - 존재 시에는 바로 AJEntity.summon(...) 호출 후 이벤트 전달 및 stopMonobehav()
 * */
class InitializeManager(val range : Double) : Monobehaviour<InitializeEvent>() {
    override fun start(event: InitializeEvent) {
        val manager = actor as ERAnimalManager

        val size = manager.entities.size.toDouble()
        //야생동물들 위치의 평균을 구함
        for (e in manager.entities) {
            val loc = e.spawnLocation
            manager.px += loc.x
            manager.py += loc.y
            manager.pz += loc.z
        }
        manager.px /= size
        manager.py /= size
        manager.pz /= size

        println("Middle point is : ${manager.px} ${manager.py} ${manager.pz}")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val manager = actor as ERAnimalManager;
        val engine = dpEngine as EREngine;
        val middleVec = vec3(manager.px, manager.py, manager.pz)

        for(player in engine.playerList){

            val distSqr = magnitudeSqr(player.getPosition() - middleVec);
            if(distSqr <= range * range){
                //상태 전이 -> WiatForSummonibn
                manager.submitEvent(WaitForSummoningEvent())
                stopMonobehav();
                return;
            }

        }

    }

}