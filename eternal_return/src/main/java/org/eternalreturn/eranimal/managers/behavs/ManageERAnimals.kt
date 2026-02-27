package org.eternalreturn.eranimal.managers.behavs

import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.animals.actors.Alpha
import org.eternalreturn.eranimal.animals.actors.Bear
import org.eternalreturn.eranimal.animals.actors.Boar
import org.eternalreturn.eranimal.animals.actors.Wolf
import org.eternalreturn.eranimal.managers.events.AnimalManageEvent
import org.eternalreturn.eranimal.managers.events.RemoveAllERAnimals
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.datastructure.UpdateView

class ManageERAnimals : Monobehaviour<AnimalManageEvent>() {

    val animals = ArrayList<ERAnimal>();


    override fun start(event: AnimalManageEvent) {
        
        //실체화된(AJEntity.summon()을 호출한) 엔티티들에 대한 Actor들을 생성
        //val startNano = System.nanoTime();

        for(erAJAnimal in event.aleadySummonedAnimals){

            val erAJAnimalLoc = erAJAnimal.location;

            val newAnimal = when (erAJAnimal.name) {
                "animal_alpha" -> Alpha(dpEngine as EREngine, erAJAnimal, erAJAnimalLoc)
                "animal_bear"  -> Bear (dpEngine as EREngine, erAJAnimal, erAJAnimalLoc)
                "animal_boar"  -> Boar (dpEngine as EREngine, erAJAnimal, erAJAnimalLoc)
                "animal_wolf"  -> Wolf (dpEngine as EREngine, erAJAnimal, erAJAnimalLoc)
                else -> null
            }
            val engine = dpEngine as EREngine;
            engine.monobehaviourModule.register(newAnimal!!);
            engine.registerBukkitActor(newAnimal.entity, newAnimal);
            animals.add(newAnimal);

            erAJAnimal.rootEntity.setRotation(erAJAnimalLoc.yaw, erAJAnimalLoc.pitch);
            erAJAnimal.actor.setRotation(erAJAnimalLoc.yaw, erAJAnimalLoc.pitch);

        }

        //println("Nano spent : ${System.nanoTime() - startNano}");

    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        //RemoveAllERAnimals 이벤트가 삽입된 경우, 바로 제거 절차 진입.
        for(event in eventList){
            if(event is RemoveAllERAnimals){
                removeAll();
                stopMonobehav();
                return;
            }
        }

    }

    fun removeAll(){
        val engine = dpEngine as EREngine;
        for(animal in animals){
            animal.aJEntity.remove();
            animal.remove();
            engine.removeBukkitActor(animal.entity);
        }
        animals.clear();
    }

}