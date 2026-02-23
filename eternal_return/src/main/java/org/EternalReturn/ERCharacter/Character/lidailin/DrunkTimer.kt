package org.EternalReturn.ERCharacter.Character.lidailin

import org.EternalReturn.ERCharacter.Character.lidailin.event.DrunkTimerEvent
import org.EternalReturn.EREntity.EREntityMonobehaviour
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent

class DrunkTimer : EREntityMonobehaviour<DrunkTimerEvent>() {

    var tick = 0;

    override fun start(event: DrunkTimerEvent) {
        tick = 0;
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val liDailin = actor as Character_LiDailin
        val currentTime = System.currentTimeMillis()
        for (event in eventList) {
            if (event is DrunkTimerEvent) {
//                liDailin.player.sendMessage("§a[시스템] 취기 타이머가 초기화되었습니다.")
                this.tick = 0
            }
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

        // 5초가 경과하면
//        if (currentTime - startTime >= duration) {
//            if (liDailin.isDrunk) {
//                liDailin.player.sendMessage("§7[리 다이린] 맨정신이 되었습니다.")
//                liDailin.isDrunk = false
//            } else if (liDailin.drinkCount > 0) {
//                liDailin.player.sendMessage("§7[리 다이린] 취기가 사라졌습니다.")
//                liDailin.drinkCount = 0
//            }
//
//            stopMonobehav() // 타이머 종료
//        }
    }
}