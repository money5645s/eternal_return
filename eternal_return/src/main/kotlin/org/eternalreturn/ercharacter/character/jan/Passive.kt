package org.eternalreturn.ercharacter.character.jan

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    override fun start(event: CharacterAttackEvent) {
        val player = getPlayer()
        val jan = actor as Character_Jan

        jan.sendMessage("평타 감지")
        jan.Stack ++

        if(jan.Stack > 5){
            jan.Stack = 5
        }
        jan.sendMessage("현재 스택 ${jan.Stack}/5")
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav()
    }
}