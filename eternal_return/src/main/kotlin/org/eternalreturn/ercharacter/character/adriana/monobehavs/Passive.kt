package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.Entity
import java.util.*

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    public override fun start(event: CharacterAttackEvent) {
        Objects.requireNonNull<Entity?>(event.victim.entity).setFireTicks(100)
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}
