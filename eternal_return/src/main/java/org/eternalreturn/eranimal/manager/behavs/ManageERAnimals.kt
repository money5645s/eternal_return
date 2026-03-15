package org.eternalreturn.eranimal.manager.behavs

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.entity.Display
import org.bukkit.entity.EntityType
import org.bukkit.entity.TextDisplay
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.animals.actors.Alpha
import org.eternalreturn.eranimal.animals.actors.Bear
import org.eternalreturn.eranimal.animals.actors.Boar
import org.eternalreturn.eranimal.animals.actors.Wolf
import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.eranimal.manager.TextDisplayer
import org.eternalreturn.eranimal.manager.events.AnimalManageEvent
import org.eternalreturn.eranimal.manager.events.RemoveAllERAnimals
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import java.util.LinkedList
import kotlin.collections.remove
import kotlin.collections.set


class ManageERAnimals(val animalSize : Int) : Monobehaviour<AnimalManageEvent>() {

    val erAnimalMap = HashMap<ERAJEntity, ERAnimal>();
    val animalIsSummoned = Array<Boolean>(animalSize){false};
    val animalSummoningTicks = Array<Int>(animalSize){-1};
    val textDisplayList = ArrayList<TextDisplayer>();


    override fun start(event: AnimalManageEvent) {

        val manager = actor as ERAnimalManager;
        
        //초기화
        for(erAJAnimal in manager.entities){
            val loc = Location(
                manager.world,
                erAJAnimal.location.x,
                erAJAnimal.location.y + 3.0,
                erAJAnimal.location.z
            )
            val textDisplay = manager.world.spawnEntity(loc, EntityType.TEXT_DISPLAY) as TextDisplay
            textDisplay.billboard = Display.Billboard.CENTER; //어느 방향에서 봐도 똑같이 보인다.

            val textDisplayEREntity = TextDisplayer(textDisplay, dpEngine as EREngine); //어차피 생성되면서 MonobehaviourModule내에 들어가게 됨.
            textDisplayList.add(textDisplayEREntity)
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        //RemoveAllERAnimals 이벤트가 삽입된 경우, 바로 제거 절차 진입.
        if(eventMap[RemoveAllERAnimals::class.java] != null){
            for(animal in erAnimalMap.values){
                if(animal.isAlive()){
                    animal.remove();
                }
            }

            java.util.Arrays.fill(animalIsSummoned,false);
            java.util.Arrays.fill(animalSummoningTicks,-1);

            //println("eventMap[RemoveAllERAnimals::class.java] != null : removed all")
            stopMonobehav();
            return;
        }

        val manager = actor as ERAnimalManager;
        val animalList = manager.entities;

        for(i in 0 until animalSize){
            val animal = animalList[i];
            if(animal.name == "animal_alpha"){
                continue;
            }

            if(!animalIsSummoned[i]){

                val ticksLeft = animalSummoningTicks[i]
                val textDisplay = textDisplayList[i].textDisplay

                if(ticksLeft == -1){
                    val testTicksForSummoning = when(animal.name){
                        "animal_boar" -> 5;
                        "animal_wolf" -> 5;
                        "animal_bear" -> 5;
                        else -> Integer.MAX_VALUE
                    }

                    animalSummoningTicks[i] = testTicksForSummoning;
                    textDisplay.text(Component.text("$ticksLeft"))
                    continue;
                }else if(ticksLeft > 0){
                    animalSummoningTicks[i]--;
                    textDisplay.text(Component.text("$ticksLeft"))
                    continue;
                }

                //if ticksLeft == 0 then

                val erAJAnimal = manager.entities[i]

                if(!manager.entities[i].isShown){
                    //println("[${this.javaClass.simpleName}] AJEntity가 아직 소환되지 않았으므로, 소환합니다.");
                    erAJAnimal.summon(0.0, 2.0, 0.0);
                }else if(erAJAnimal.isValid){
                    erAnimalMap[erAJAnimal] = createAnimal(erAJAnimal)
                    animalIsSummoned[i] = true;
                }
            }
        }

        for(i in 0 until animalSize){
            if(!animalIsSummoned[i]){
                continue;
            }

            val erAJAnimal = manager.entities[i];
            val erAnimal = erAnimalMap[erAJAnimal]!!;

            if(erAnimal.isDead()){
                animalSummoningTicks[i] = -1;
                erAnimalMap.remove(erAJAnimal, erAnimal);
                animalIsSummoned[i] = false;
                erAnimal.remove();

            } else if(erAnimal.haveToReturnToPoint()){
                animalSummoningTicks[i] = 0;
                erAnimalMap.remove(erAJAnimal, erAnimal);
                animalIsSummoned[i] = false;
                erAnimal.remove();

            }

        }

    }

    /**
     * 소환된 ERAJEntity에 맞는 ERAnimal객체를 생성하는 함수
     * */
    private fun createAnimal(erAJAnimal : ERAJEntity) : ERAnimal{
        val erAJAnimalLoc = erAJAnimal.location;
        val newAnimal = when (erAJAnimal.name) {
            "animal_alpha" -> Alpha(dpEngine as EREngine, erAJAnimal);
            "animal_bear"  -> Bear (dpEngine as EREngine, erAJAnimal);
            "animal_boar"  -> Boar (dpEngine as EREngine, erAJAnimal);
            "animal_wolf"  -> Wolf (dpEngine as EREngine, erAJAnimal);
            else -> Boar (dpEngine as EREngine, erAJAnimal)
        }
        val engine = dpEngine as EREngine;
        engine.registerBukkitActor(newAnimal.entity, newAnimal);
        erAJAnimal.rootEntity.setRotation(erAJAnimalLoc.yaw, erAJAnimalLoc.pitch);
        return newAnimal;
    }




}