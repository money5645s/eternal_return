package org.eternalreturn.ercharacter.character.nathapon

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.erentity.events.EREntityAttackEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {

    private var punchTimeMillis: Long = 0

    override fun start(event: EREntityAttackEvent) {

        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }
        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        victimEntity.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 40, 1, false, true))

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}