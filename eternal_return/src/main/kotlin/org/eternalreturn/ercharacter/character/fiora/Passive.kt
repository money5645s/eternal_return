package org.eternalreturn.ercharacter.character.fiora

import org.eternalreturn.ercharacter.character.fiora.event.ERToucheCountEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.erentity.events.EREntityAttackEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: EREntityAttackEvent) {

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }

        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        event.victim.submitEvent(ERToucheCountEvent(erPlayer))
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}