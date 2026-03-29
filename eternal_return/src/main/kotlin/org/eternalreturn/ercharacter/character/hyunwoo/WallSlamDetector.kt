package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.damage.DamageType
import org.bukkit.util.Vector
import org.eternalreturn.erentity.EREntityTimerMonobehaviour
import org.eternalreturn.erentity.DPHitboxEntity
import org.dpengine.monobehaviour.EventLess
import org.dpengine.monobehaviour.MonobehaviourEvent
import kotlin.math.max

class WallSlamDetector(
    val damage : Double,
    val caster : Character_Hyunwoo, val dx : Double, val dy : Double, val dz : Double, val stunDuration : Long,
    duration : Long) : EREntityTimerMonobehaviour<EventLess>(duration = duration) {


    override fun timerStart(event: EventLess) {
        val victim = DPEntity;
        victim.damage(1.0, caster, DamageType.PLAYER_ATTACK);
    }

    override fun timerUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val victim = DPEntity as DPHitboxEntity;

        victim.setVelocity(dx, 0.0, dz);

        val loc = victim.location; loc.y += 1.0;

        val dir = Vector(dx, 0.0, dz).normalize();
        val result = entity.world.rayTraceBlocks(loc, dir, 2.0 + max(victim.obbHalfZ, victim.obbHalfX));
        if(result?.hitBlock?.isEmpty == false){
            caster.submitEvent(InterruptActiveEvent());
            victim.damageForce(damage, caster, DamageType.PLAYER_ATTACK)
            stopMonobehav();
            return;
        }
    }
}