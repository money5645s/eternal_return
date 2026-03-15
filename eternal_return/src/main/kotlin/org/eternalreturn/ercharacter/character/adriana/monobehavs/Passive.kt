package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.bukkit.entity.Entity
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import java.util.*

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    public override fun start(event: EREntityAttackEvent) {
        Objects.requireNonNull<Entity?>(event.victim.entity).setFireTicks(100)
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}
