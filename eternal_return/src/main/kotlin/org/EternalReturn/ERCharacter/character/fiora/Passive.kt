package org.EternalReturn.ERCharacter.character.fiora

import org.EternalReturn.ERCharacter.character.fiora.event.ERToucheCountEvent
import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.event.CharacterAttackEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: CharacterAttackEvent) {
        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }

        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        event.victim.submitEvent(ERToucheCountEvent(this.getEREntity().entity as Player))
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav();
    }


}