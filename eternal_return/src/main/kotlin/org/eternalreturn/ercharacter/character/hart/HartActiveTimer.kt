package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.character.hart.event.HartActiveEvent
import org.eternalreturn.ercharacter.event.CooldownEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class HartActiveTimer : EREntityMonobehaviour<HartActiveEvent>() {

    var tick = 0;

    override fun start(event: HartActiveEvent) {
        tick = 0;
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val hart = actor as Character_Hart

        hart.player.sendMessage("${tick}")
        if(hart.stack == 0){
            stopMonobehav()
            return
        }
        tick++

        if(tick >= 100){
            hart.player.sendMessage("재사용 시간 끝")
            tick = 0
            hart.stack = 0
            this.getEREntity().submitEvent(CooldownEvent("Active", hart.ActiveCooldownSeconds))
            stopMonobehav() // 타이머 종료
        }

    }
}