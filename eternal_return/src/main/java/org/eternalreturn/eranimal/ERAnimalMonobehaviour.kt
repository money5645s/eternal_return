package org.eternalreturn.eranimal

import org.bukkit.entity.Entity
import org.eternalreturn.eranimal.animals.events.ERAnimalEvent
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

abstract class ERAnimalMonobehaviour<T : ERAnimalEvent> : Monobehaviour<T>() {
    private var erAJEntity: ERAJEntity? = null
    private var erAnimal: ERAnimal? = null

    private fun __init() {
        if (erAJEntity != null) {
            return
        }
        if (this.actor !is ERAnimal) {
            throw ClassCastException("Actor가 ERCharacter가 아닙니다.")
        }
        erAnimal = this.actor as ERAnimal
        erAJEntity = erAnimal!!.ajEntity
    }

    val eRAJEntity: ERAJEntity?
        get() {
            __init()
            return this.erAJEntity
        }

    val eRAnimal: ERAnimal?
        get() {
            __init()
            return this.erAnimal
        }

    //protected void damage(Player attacker, LivingEntity victim, double dmg){
    //    ERPlayerListener.addAPIAttacker(attacker);
    //    victim.damage(dmg, attacker);
    //}
    protected fun isNotEnd(startTime: Long, durationTicks: Long): Boolean {
        return System.currentTimeMillis() - startTime < durationTicks * 50
    }

    fun isContainedIn(eventType: Class<out ERAnimalEvent>, eventList: MutableList<MonobehaviourEvent>): Boolean {
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
