package org.eternalreturn.eranimal.animals.behavs

import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.StatEnhanceEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class EnhanceStatByDay : ERAnimalMonobehaviour<StatEnhanceEvent>() {

    var day : Int = 0;
    override fun start(event: StatEnhanceEvent) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val animal = actor as ERAnimal

        if(animal.aJEntity.name == "animal_alpha"){
            animal.level = 2;
            stopMonobehav();
            return;
        }

        val curDay = erEngine.curDay;
        if(curDay != day){
            day = curDay

            animal.level = curDay;
            if(curDay > animal.maxLevel){
                animal.level = animal.maxLevel;
            }

            animal.hpMax = animal.hp0 + animal.hpInc * (animal.level - 1);
            animal.damage = animal.damage0 + animal.damageInc * (animal.level - 1);
            animal.updateHPBar();

            if(animal.level == animal.maxLevel){
                stopMonobehav();
                return;
            }
        }
    }
}