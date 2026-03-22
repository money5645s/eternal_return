package org.eternalreturn.ercharacter.character.lidailin

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.lidailin.event.LiDailinPassiveTimerEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: EREntityAttackEvent) {

        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            stopMonobehav();
            return;
        }
        erCharacter.passiveCooldown = erCharacter.passiveCoolForEachLevel[erCharacter.passiveLevel];

        if((actor as Character_LiDailin).isDrunk){
            event.victim.submitEvent(LiDailinPassiveTimerEvent(erPlayer))
        }


    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}