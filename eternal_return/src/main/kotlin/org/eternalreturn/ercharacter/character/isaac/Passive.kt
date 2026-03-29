package org.eternalreturn.ercharacter.character.isaac

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.isaac.event.PassiveCountEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.dpengine.monobehaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {

    override fun start(event: EREntityAttackEvent) {
        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            stopMonobehav();
            return;
        }
        //erCharacter.passiveCooldown = erCharacter.passiveCoolForEachLevel[erCharacter.passiveLevel] * 20;
        event.victim.submitEvent(PassiveCountEvent(this.erPlayer))
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}