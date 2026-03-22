package org.eternalreturn.ercharacter.character.jan

import org.eternalreturn.ercharacter.character.jan.event.JanActiveTimerEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class JanActiveTimer : EREntityMonobehaviour<JanActiveTimerEvent>()  {

    var tick = 0

    override fun start(event: JanActiveTimerEvent) {
        tick = 0
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val jan = actor as Character_Jan


        if(jan.SkillReuse == 0){
            stopMonobehav()
            return
        }
        tick++

        if(tick >= 100){
            jan.player.sendMessage("재사용 시간 끝")
            tick = 0
            jan.SkillReuse = 0
            jan.StackSkill = false
            stopMonobehav() // 타이머 종료
        }

    }
}