package org.eternalreturn.ercharacter.character.jan

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    override fun start(event: EREntityAttackEvent) {
        val jan = actor as Character_Jan

        jan.sendMessage("평타 감지")
        jan.Stack ++

        if(jan.Stack > 5){
            jan.Stack = 5
        }
        jan.sendMessage("현재 스택 ${jan.Stack}/5")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}