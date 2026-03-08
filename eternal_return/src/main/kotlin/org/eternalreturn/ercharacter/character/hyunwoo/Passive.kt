package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.eternalreturn.ercharacter.character.hyunwoo.event.HyunwooPassiveTimerEvent
import java.util.*
import kotlin.math.min

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {

    private var punchTimeMillis: Long = 0

    override fun start(event: CharacterAttackEvent) {
        val player = getPlayer()
        val hyuwnoo = actor as Character_Hyunwoo

        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }

        punchTimeMillis = System.currentTimeMillis() + 10 * 50
        hyuwnoo.Stack ++
        this.getEREntity().submitEvent(HyunwooPassiveTimerEvent())

        player.sendMessage("현재 스택 ${hyuwnoo.Stack}/5")

        if(hyuwnoo.Stack >= 5){
            val healAmount = 3.0
            val maxHealth = player.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
            val finalHealth = Math.min(player.health + healAmount, maxHealth)
            player.health = finalHealth
        }
        player.sendMessage("현재 스택 ${hyuwnoo.Stack}/5")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}