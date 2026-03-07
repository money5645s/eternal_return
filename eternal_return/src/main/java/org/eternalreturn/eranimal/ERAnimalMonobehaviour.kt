package org.eternalreturn.eranimal

import org.bukkit.entity.Entity
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

abstract class ERAnimalMonobehaviour<T : EREntityEvent> : Monobehaviour<T>() {

    protected fun isNotEnd(startTime: Long, durationTicks: Long): Boolean {
        return System.currentTimeMillis() - startTime < durationTicks * 50
    }

    fun isContainedIn(eventType: Class<out EREntityEvent>, eventList: MutableList<MonobehaviourEvent>): Boolean {
        for (event in eventList) {
            if (eventType.isInstance(event)) {
                return true
            }
        }
        return false
    }


}
