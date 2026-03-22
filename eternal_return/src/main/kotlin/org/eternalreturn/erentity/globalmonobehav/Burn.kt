package org.eternalreturn.erentity.globalmonobehav

import org.bukkit.Particle
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent
import kotlin.properties.Delegates

class Burn : EREntityMonobehaviour<EREntityBurnEvent>() {

    var ticksLeft : Long = 0;
    var attacker : EREntity by Delegates.notNull()

    var flameParticle : com.destroystokyo.paper.ParticleBuilder by Delegates.notNull();
    var smokeParticle : com.destroystokyo.paper.ParticleBuilder by Delegates.notNull();

    override fun start(event: EREntityBurnEvent) {
        ticksLeft = event.ticks;
        attacker = event.attacker;

        flameParticle = Particle.FLAME.builder()
            .location(erEntity.location)
            .offset(0.5, 0.5, 0.5)
            .count(3)
            .extra(0.03)
            .receivers(32, true);

        smokeParticle = Particle.SMOKE.builder()
            .location(erEntity.location)
            .offset(0.5, 0.5, 0.5)
            .count(3)
            .extra(0.03)
            .receivers(32, true);

    }


    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val event = eventMap[EREntityBurnEvent::class.java] as EREntityBurnEvent?;
        if(event != null){
            ticksLeft += event.ticks;
        }

        if(ticksLeft > 0){ ticksLeft--;
            val victim = erEntity;

            if(ticksLeft % 5 == 0.toLong()){
                val bukkitEntity = victim.entity;
                val loc = bukkitEntity.location;

                flameParticle.location(loc).spawn();
                smokeParticle.location(loc).spawn();
            }

            if(ticksLeft % 20 == 0.toLong()){
                victim.damageNotSendEventPierce(1.0, attacker);
            }
            return;
        }

        stopMonobehav();
    }
}