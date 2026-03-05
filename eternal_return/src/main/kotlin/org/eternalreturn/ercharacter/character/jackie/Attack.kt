package org.eternalreturn.ercharacter.character.jackie

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.eternalreturn.ercharacter.character.jackie.Character_Jackie
import javax.management.Attribute

class Attack : ERCharacterMonobehaviour<CharacterAttackEvent>() {

    public override fun start(event: CharacterAttackEvent) {
        val dir = this.getERCharacter().getDirection();
        val player = getPlayer()

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