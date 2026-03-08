package org.eternalreturn.ercharacter.character.fiora

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    override fun start(event: CharacterAttackEvent) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}