package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.damage.DamageType
import org.bukkit.util.Vector
import org.eternalreturn.erentity.EREntityTimerMonobehaviour
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.util.dpengine.behaviour.EventLess
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class WallSlamDetector(
    val damage : Double,
    val caster : Character_Hyunwoo, val dx : Double, val dy : Double, val dz : Double, val stunDuration : Long,
    duration : Long) : EREntityTimerMonobehaviour<EventLess>(duration = duration) {


    override fun timerStart(event: EventLess) {
        val victim = erEntity;
        victim.damage(1.0, caster, DamageType.PLAYER_ATTACK);
    }

    override fun timerUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val victim = erEntity;

        val loc = entity.location;
        loc.y += 1.5;
        val dir = Vector(dx, 0.0, dz).normalize();
        val result = entity.world.rayTraceBlocks(loc, dir, 2.0);
        if(result?.hitBlock?.isEmpty == false){
            caster.submitEvent(InterruptActiveEvent());
            victim.damage(damage, caster, DamageType.PLAYER_ATTACK)
            stopMonobehav();
            return;
        }
    }
}