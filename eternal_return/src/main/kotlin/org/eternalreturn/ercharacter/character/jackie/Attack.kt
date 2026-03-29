package org.eternalreturn.ercharacter.character.jackie

import org.bukkit.Sound
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.dpengine.monobehaviour.MonobehaviourEvent

class Attack : ERCharacterMonobehaviour<EREntityAttackEvent>() {

    public override fun start(event: EREntityAttackEvent) {
        val dir = erPlayer.getDirection();
        val player = erPlayer.player

        if((actor as Character_Jackie).isBloodSweep){
            val maxHealth = player.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH)?.value ?: 20.0
            player.health = (player.health + 3.0).coerceAtMost(maxHealth)
            player.playSound(player.location, Sound.ENTITY_GENERIC_DRINK, 0.5f, 1.5f)
        }

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }
}