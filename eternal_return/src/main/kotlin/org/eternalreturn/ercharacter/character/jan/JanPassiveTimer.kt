package org.eternalreturn.ercharacter.character.jan

import org.eternalreturn.ercharacter.character.jan.event.JanPassiveTimerEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class JanPassiveTimer : EREntityMonobehaviour<JanPassiveTimerEvent>() {
    var tick = 0
    override fun start(event: JanPassiveTimerEvent) {
        tick = 0
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val jan = actor as Character_Jan

        tick ++
        if(tick >= 100){
            jan.Stack = 0
            jan.sendMessage("스택 초기화")
        }

    }
}