package org.EternalReturn.ERCharacter.character.jackie

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.event.CharacterAttackEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound

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

    public override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav();
    }
}