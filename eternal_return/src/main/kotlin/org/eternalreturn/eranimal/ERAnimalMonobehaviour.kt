package org.eternalreturn.eranimal

import org.dpengine.monobehaviour.Monobehaviour
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.system.EREngine
import org.dpengine.monobehaviour.MonobehaviourEvent

abstract class ERAnimalMonobehaviour<T : EREntityEvent> : Monobehaviour<T>() {

    val erEngine : EREngine
        get() {
            return dpEngine as EREngine
        }

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
