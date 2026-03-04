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
        val erEntity = actor as EREntity;
        erEntity.shootRay();
        stopMonobehav();
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }

}