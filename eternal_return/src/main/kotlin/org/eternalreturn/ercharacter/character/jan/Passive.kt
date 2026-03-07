package org.eternalreturn.ercharacter.character.jan

import org.bukkit.entity.LivingEntity
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.jan.event.JanPassiveTimerEvent
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.compareTo
import kotlin.inc

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {

    private var punchTimeMillis: Long = 0

    override fun start(event: CharacterAttackEvent) {
        val player = getPlayer()
        val jan = actor as Character_Jan

        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }

        punchTimeMillis = System.currentTimeMillis() + 10 * 50
        jan.Stack ++
        this.getEREntity().submitEvent(JanPassiveTimerEvent())

        if(jan.Stack > 5){
            jan.Stack = 5
        }
        player.sendMessage("현재 스택 ${jan.Stack}/5")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}