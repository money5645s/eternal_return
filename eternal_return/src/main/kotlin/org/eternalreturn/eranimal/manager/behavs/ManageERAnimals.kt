package org.eternalreturn.eranimal.manager.behavs

import net.kyori.adventure.text.Component
import org.dpengine.monobehaviour.Monobehaviour
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.DPAnimal
import org.eternalreturn.eranimal.animals.actors.*
import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.eranimal.manager.events.AnimalManageEvent
import org.eternalreturn.eranimal.manager.events.RemoveAllERAnimals
import org.eternalreturn.system.EREngine
import org.dpengine.monobehaviour.MonobehaviourEvent


class ManageERAnimals(val animalSize : Int) : Monobehaviour<AnimalManageEvent>() {


    val animalSummoningTicks = LongArray(animalSize){-1};

    val animalShownState = Array<ShowState>(animalSize){ShowState.NOT_SHOWN};
    val summoningCondition = Array<Condition>(animalSize){Condition.NOTHING}; //야생동물 원위치로 돌아감


    enum class ShowState{
        SHOW_PROCESSING,
        SHOWN,
        NOT_SHOWN
    }

    enum class Condition{
        GET_KILLED,
        HAVE_TO_RETURN,
        NOTHING,
        OUT_OF_RANGE
    }

    override fun start(event: AnimalManageEvent) {

    }
    
    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        val manager = actor as ERAnimalManager;
        
        //RemoveAllERAnimals 이벤트가 삽입된 경우, 바로 제거 절차 진입.
        if(eventMap[RemoveAllERAnimals::class.java] != null){
            for(animal in manager.erAnimalMap.values){
                animal.setNotBeShown();
            }

            for(i in 0 until animalSize){
                animalShownState[i] = ShowState.NOT_SHOWN;
                summoningCondition[i] = Condition.OUT_OF_RANGE;
            }

            stopMonobehav();
            return;
        }


        for(i in 0 until animalSize){

            /**
             * ERAJEntity
             * */
            val animal = manager.entities[i];
            if(animal.name == "animal_alpha" && !manager.isAllowedToSummonAlpha){
                continue;
            }
            if(animal.name == "animal_omega" && !manager.isAllowedToSummonOmega){
                continue;
            }

            if(animalShownState[i] == ShowState.NOT_SHOWN){
                animalShownState[i] = setCooldown(i);
            }

            if(animalShownState[i] == ShowState.SHOW_PROCESSING){
                animalShownState[i] = cooldownNShowProcessing(i);
            }
        }

        for(i in 0 until animalSize){
            if(animalShownState[i] != ShowState.SHOWN){
                continue;
            }

            val animal = manager.entities[i];
            val erAnimal = manager.erAnimalMap[animal]!!;

            if(erAnimal.isDead()){
                if(animal.name == "animal_alpha") {
                    manager.allowToSummonAlpha(false);
                } else if(animal.name == "animal_omega"){
                    manager.allowToSummonOmega(false);
                }

                summoningCondition[i] = Condition.GET_KILLED;
                animalSummoningTicks[i] = 0;
                animalShownState[i] = ShowState.NOT_SHOWN;
                erAnimal.setNotBeShown();
                erAnimal.remove();

            } else if(erAnimal.haveToReturnToPoint()){
                summoningCondition[i] = Condition.HAVE_TO_RETURN;
                animalSummoningTicks[i] = 0;
                animalShownState[i] = ShowState.NOT_SHOWN;
                erAnimal.setNotBeShown();
                erAnimal.remove();

            }

        }

    }

    /**
     * 야생동물의 사망 여부 (Condition)의 여부를 고려하여 적절한 쿨다운 설정
     *
     * 상태 전이
     *
     *           --[시간 설정 완료]--> ShowState.SHOW_PROCESSING
     *
     * */
    private fun setCooldown(animalID : Int) : ShowState{
        val manager = actor as ERAnimalManager

        var ticksForSummoning : Int = 0
        val i = animalID
        val animal = manager.entities[i];
        val textDisplay = manager.textDisplayList[i]

//        if(summoningCondition[i] == Condition.NOTHING){
//            ticksForSummoning = when(animal.name){
//                "animal_alpha" -> 20 * 3;
//                "animal_bear" -> 20 * 90;
//                "animal_boar" -> 20 * 30;
//                "animal_omega" -> 20 * 3;
//                "animal_wolf" -> 20 * 60;
//                else -> Integer.MAX_VALUE
//            }
//        }
//
//        if(summoningCondition[i] == Condition.GET_KILLED){
//            ticksForSummoning = when(animal.name){
//                "animal_alpha" -> 20 * 3;
//                "animal_bear" -> 20 * 100;
//                "animal_boar" -> 20 * 65;
//                "animal_omega" -> 20 * 3;
//                "animal_wolf" -> 20 * 55;
//                else -> Integer.MAX_VALUE
//            }
//        }



        if(summoningCondition[i] == Condition.NOTHING){
            ticksForSummoning = ERAnimalManager.Companion.animalMap[animal.name]!!.summonCooldownSeconds[0] * 20
        }

        if(summoningCondition[i] == Condition.GET_KILLED){
            ticksForSummoning = ERAnimalManager.Companion.animalMap[animal.name]!!.summonCooldownSeconds[1] * 20
        }

        animalShownState[i] = ShowState.SHOW_PROCESSING
        animalSummoningTicks[i] = ticksForSummoning * 50 + (System.currentTimeMillis());
        textDisplay.text(Component.text("${(ticksForSummoning / 20)} s"))
        return ShowState.SHOW_PROCESSING
    }

    /**
     *
     * 쿨타임을 지속적으로 재 계산한다. 쿨타임이 끝나면 Shown으로 변경한다.
     *
     * 상태 전이
     *
     *           --[시간 남음]--> ShowState.SHOW_PROCESSING
     *
     *           --[시간 종료]--> ShowState.SHOWN
     * */
    private fun cooldownNShowProcessing(animalID : Int) : ShowState{
        val manager = actor as ERAnimalManager
        val i = animalID;
        val animal = manager.entities[i];
        val textDisplay = manager.textDisplayList[i]
        val ticksLeft : Long = animalSummoningTicks[i] - System.currentTimeMillis();
        if(ticksLeft >= 0){
            textDisplay.text(Component.text("${(ticksLeft / 1000)} s"))
            return ShowState.SHOW_PROCESSING;
        }

        //if ticksLeft == 0 then

        textDisplay.text(Component.empty()); //더이상 초 표기를 하지 않음.

        manager.erAnimalMap[animal] = createAnimal(animal);
        animal.setBeShown();
        animalShownState[i] = ShowState.SHOWN;
        return ShowState.SHOWN;
    }


    /**
     * 소환된 ERAJEntity에 맞는 ERAnimal객체를 생성하는 함수
     * */
    private fun createAnimal(erAJAnimal : ERAJEntity) : DPAnimal{
        val erAJAnimalLoc = erAJAnimal.spawnLocation;
        val newAnimal = when (erAJAnimal.name) {
            "animal_alpha" -> Alpha(dpEngine as EREngine, erAJAnimal);
            "animal_bear"  -> Bear (dpEngine as EREngine, erAJAnimal);
            "animal_boar"  -> Boar (dpEngine as EREngine, erAJAnimal);
            "animal_wolf"  -> Wolf (dpEngine as EREngine, erAJAnimal);
            "animal_omega" -> Omega(dpEngine as EREngine, erAJAnimal);
            else -> Boar (dpEngine as EREngine, erAJAnimal)
        }
        val engine = dpEngine as EREngine;
        engine.registerBukkitActor(newAnimal.entity, newAnimal);
        erAJAnimal.rootEntity.setRotation(erAJAnimalLoc.yaw, erAJAnimalLoc.pitch);
        return newAnimal;
    }




}