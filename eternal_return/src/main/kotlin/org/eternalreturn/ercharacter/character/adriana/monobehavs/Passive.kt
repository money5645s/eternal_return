package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.bukkit.entity.Entity
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import java.util.*

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    public override fun start(event: EREntityAttackEvent) {

        event.victim.submitEvent(EREntityBurnEvent(erPlayer, 100))

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}
