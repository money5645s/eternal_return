package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.dpengine.monobehaviour.Monobehaviour
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.eternalreturn.system.EREngine

abstract class EREntityMonobehaviour<T : MonobehaviourEvent>() : Monobehaviour<T>() {

    fun getEREntity() : DPEntity{
        return actor as DPEntity
    }

    val DPEntity : DPEntity
        get() {
            return actor as DPEntity;
        }

    val entity: Entity
        get() = (super.actor as DPEntity).entity

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