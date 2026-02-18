package org.eternalreturn.eranimal.managers.behavs

import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.managers.actors.ERAnimalManager
import org.eternalreturn.eranimal.managers.events.AnimalManageEvent
import org.eternalreturn.eranimal.managers.events.DetectingPlayerEvent
import org.eternalreturn.eranimal.managers.events.RemoveAllERAnimals
import org.eternalreturn.eranimal.managers.events.WaitForSummoning
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

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

    var summoned = false;
    public override fun update(eventList: MutableCollection<MonobehaviourEvent>) {

        var isInDistance = false;

        val playerList = (dpEngine as EREngine).players.curQueue;

        //println("PlayerListSize : ${playerList.size}");

        for (player in playerList) {
            val avg = vec3(px, py, pz);
            val pos = player.getPosition()
            val dis = magnitudeSqr(avg - pos);
            if(dis <= 32 * 32){
                isInDistance = true;
                break;
            }
        }

        //플레이어가 하나라도 없는 경우
        if(!isInDistance && summoned){
            removeERAJEntities();
            actor.submitEvent(RemoveAllERAnimals())
            summoned = false;
        }
        
        //플레이어가 하나라도 있지만, 소환되지는 않은 경우
        if(isInDistance && !summoned){
            val listReference = summonERAJEntities();
            actor.submitEvent(WaitForSummoning(listReference))
            summoned = true;
        }

    }

    private fun summonERAJEntities() : MutableList<ERAJEntity>{
        val list = (actor as ERAnimalManager).entities;
        for(erAJAnimal in list){
            erAJAnimal.summon();
            erAJAnimal.isShown = true;
        }
        return list;
    }

    private fun removeERAJEntities(){
        val list = (actor as ERAnimalManager).entities;
        for(erAJAnimal in list){
            erAJAnimal.remove();
            erAJAnimal.isShown = false;
        }
    }


}
