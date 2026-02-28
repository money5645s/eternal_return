package org.EternalReturn.ERCharacter.Character.adriana.monobehavs

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterAttackEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.Entity
import java.util.*

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    public override fun start(event: CharacterAttackEvent) {
        Objects.requireNonNull<Entity?>(event.victim.entity).setFireTicks(100)
    }

    public override fun update(event: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav()
    }
}
