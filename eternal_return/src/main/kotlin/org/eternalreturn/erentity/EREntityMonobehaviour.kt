package org.eternalreturn.erentity

import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.Monobehaviour
import org.bukkit.entity.Entity
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

abstract class EREntityMonobehaviour<T : MonobehaviourEvent>() : Monobehaviour<T>() {

    fun getEREntity() : EREntity{
        return actor as EREntity
    }

    val erEntity : EREntity
        get() {
            return actor as EREntity;
        }

    val entity: Entity
        get() = (super.actor as EREntity).entity

    fun isNotEnd(startTimeMillis: Long, durationTicks: Long): Boolean {
        return System.currentTimeMillis() - startTimeMillis < durationTicks * 50
    }

    val erEngine: EREngine
        get() = this.dpEngine as EREngine
}

abstract class EREntityTimerMonobehaviour<T : MonobehaviourEvent>(val duration : Long) : EREntityMonobehaviour<T>(){

    override fun start(event: T) {
        timerStart(event);
    } abstract fun timerStart(event : T);


    var tickTimer : Long = 0;
    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(tickTimer >= duration){
            stopMonobehav();
            tickTimer = 0
            return;
        }
        timerUpdate(eventMap);
        tickTimer++;
    }abstract fun timerUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>);

    fun resetTimer(){
        tickTimer = 0;
    }

}