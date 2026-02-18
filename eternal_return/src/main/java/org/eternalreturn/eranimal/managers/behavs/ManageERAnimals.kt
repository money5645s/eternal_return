package org.eternalreturn.eranimal.managers.behavs

import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.animals.actors.Alpha
import org.eternalreturn.eranimal.animals.actors.Bear
import org.eternalreturn.eranimal.animals.actors.Boar
import org.eternalreturn.eranimal.animals.actors.Wolf
import org.eternalreturn.eranimal.managers.events.AnimalManageEvent
import org.eternalreturn.eranimal.managers.events.RemoveAllERAnimals
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.datastructure.UpdateView

class ManageERAnimals : Monobehaviour<AnimalManageEvent>() {

    val animals = UpdateView<ERAnimal>();


    override fun start(event: AnimalManageEvent) {
        for(erAJAnimal in event.aleadySummonedAnimals){
            val newAnimal = when (erAJAnimal.name) {
                "animal_alpha" -> Alpha(dpEngine, erAJAnimal, erAJAnimal.location)
                "animal_bear"  -> Bear(dpEngine, erAJAnimal, erAJAnimal.location)
                "animal_boar"  -> Boar(dpEngine, erAJAnimal, erAJAnimal.location)
                "animal_wolf"  -> Wolf(dpEngine, erAJAnimal, erAJAnimal.location)
                else -> null
            }
            dpEngine.monobehaviourModule.register(newAnimal!!);
            animals.add(newAnimal);
        }
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        for(event in eventList){
            if(event is RemoveAllERAnimals){
                removeAll();
                stopMonobehav();
                return;
            }
        }


    }

    fun removeAll(){
        for(animal in animals.curQueue){
            animal.remove();
        }
    }

}