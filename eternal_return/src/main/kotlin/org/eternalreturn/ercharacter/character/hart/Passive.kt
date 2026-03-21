package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.character.hart.event.PassiveTimerEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityBurnEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {

    override fun start(event: EREntityAttackEvent) {
        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            stopMonobehav();
            return;
        }
        erCharacter.passiveCooldown = erCharacter.passiveCoolForEachLevel[erCharacter.passiveLevel] * 20;

        val hart = erCharacter as Character_Hart;
        event.victim.submitEvent(PassiveTimerEvent(hart.passiveExtraDamageForEachLevel[hart.passiveLevel], this.erPlayer))

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
            stopMonobehav()
    }
}