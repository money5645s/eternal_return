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

    fun isInDistance(r: Double, e0: Entity, e1: Entity): Boolean {
        val tx = e0.location.x
        val ty = e0.location.y
        val tz = e0.location.z

        val ax = e1.location.x
        val ay = e1.location.y
        val az = e1.location.z

        val dx = ax - tx
        val dy = ay - ty
        val dz = az - tz

        return (dx * dx + dy * dy + dz * dz <= r * r)
    }
}
