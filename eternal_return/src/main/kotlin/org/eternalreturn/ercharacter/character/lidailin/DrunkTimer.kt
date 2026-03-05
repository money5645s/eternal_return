package org.eternalreturn.ercharacter.character.lidailin

import org.eternalreturn.ercharacter.character.lidailin.event.DrunkTimerEvent
import org.eternalreturn.ercharacter.character.lidailin.Character_LiDailin
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

// 취기 클래스
class DrunkTimer : EREntityMonobehaviour<DrunkTimerEvent>() {

    var tick = 0;

    override fun start(event: DrunkTimerEvent) {
        tick = 0;
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val liDailin = actor as Character_LiDailin
        val event = eventMap[DrunkTimerEvent::class.java] as DrunkTimerEvent?
        if(event != null){
            this.tick = 0
        }
//        liDailin.player.sendMessage("${tick}")
        tick++

        if(tick >= 140){
            if (liDailin.isDrunk) {
                liDailin.player.sendMessage("§7[리 다이린] 맨정신이 되었습니다.")
                liDailin.isDrunk = false
            } else if (liDailin.drinkCount > 0) {
                liDailin.player.sendMessage("§7[리 다이린] 취기가 사라졌습니다.")
                liDailin.drinkCount = 0
            }
            tick = 0
            stopMonobehav() // 타이머 종료
        }

    }
}