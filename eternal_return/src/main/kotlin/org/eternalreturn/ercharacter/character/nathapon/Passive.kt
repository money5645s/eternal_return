package org.eternalreturn.ercharacter.character.nathapon

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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

        victimEntity.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 40, 1, false, true))


    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav()
    }
}