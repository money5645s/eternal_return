package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.character.hyunwoo.event.HyunwooPassiveTimerEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class HyunwooPassiveTimer : EREntityMonobehaviour<HyunwooPassiveTimerEvent>() {
    var tick = 0
    override fun start(event: HyunwooPassiveTimerEvent) {
        tick = 0
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val hyunwoo = actor as Character_Hyunwoo

        for (event in eventMap) {
            this.tick = 0
        }

        tick ++
        if(tick >= 100){
            hyunwoo.Stack = 0
            hyunwoo.player.sendMessage("스택 초기화")
            stopMonobehav()
        }

    }
}