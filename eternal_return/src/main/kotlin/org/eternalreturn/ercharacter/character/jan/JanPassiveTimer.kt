package org.eternalreturn.ercharacter.character.jan

import org.eternalreturn.ercharacter.character.jan.event.JanPassiveTimerEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent

class JanPassiveTimer : EREntityMonobehaviour<JanPassiveTimerEvent>() {
    var tick = 0
    override fun start(event: JanPassiveTimerEvent) {
        tick = 0
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val jan = actor as Character_Jan

        if(eventMap[EREntityAttackEvent::class.java] != null) {
            this.tick = 0
        }

        tick ++
        if(tick >= 100){
            jan.Stack = 0
            jan.StackSkill = false
            jan.player.sendMessage("스택 초기화")
            stopMonobehav()
        }

    }
}