package org.eternalreturn.ercharacter.globalmonobehav

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterLeftClickEvent
import org.eternalreturn.ercharacter.event.CharacterRayCastEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.OrientedBox

/**
 * 플레이어가 왼쪽 클릭을 할 때마다 실행될 레이캐스트.
 * */
class PlayerRayCastingByLeftClicking : ERCharacterMonobehaviour<CharacterLeftClickEvent>() {


    override fun start(event: CharacterLeftClickEvent) {

        val pdir = (actor as EREntity).getDirection();
        val pPos = (actor as EREntity).getPosition();
        val out = vec3();


        val hitList = ArrayList<EREntity>();

        for(erEntity in getMonobehavActorList()){
            if(erEntity !is EREntity || erEntity === actor){
                continue;
            }

            //println("" + erEntity.collider.javaClass + " with " + erEntity.javaClass);
            
            //콜라이더 위치 조정
            val collider = erEntity.collider;

            if(collider.rayCasting(out, pPos, pdir)){
                println("Ray hit to -> " + erEntity.javaClass);
                hitList.addLast(erEntity)
            }
        }
        if(hitList.isEmpty())return
        this.actor.submitEvent(CharacterRayCastEvent(hitList))
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav();
    }

}