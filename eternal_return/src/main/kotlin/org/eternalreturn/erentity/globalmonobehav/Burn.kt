package org.eternalreturn.erentity.globalmonobehav

import org.bukkit.Particle
import org.bukkit.Particle.Trail
import org.bukkit.entity.LivingEntity
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.properties.Delegates

class Burn : EREntityMonobehaviour<EREntityBurnEvent>() {

    var ticksLeft : Int = 0;
    var attacker : EREntity by Delegates.notNull()

    override fun start(event: EREntityBurnEvent) {
        ticksLeft = event.ticks;
        attacker = event.attacker;
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val event = eventMap[EREntityBurnEvent::class.java] as EREntityBurnEvent?;
        if(event != null){
            ticksLeft += event.ticks;
        }

        if(ticksLeft > 0){ ticksLeft--;
            val victim = erEntity;

            if(ticksLeft % 5 == 0){
                val bukkitEntity = victim.entity;
                val world = bukkitEntity.world;
                val loc = bukkitEntity.location;

                val flameParticle = Particle.FLAME.builder()
                    .location(loc)
                    .offset(0.5, 0.5, 0.5)
                    .count(3)
                    .extra(0.03)
                    .receivers(32, true);


                val smokeParticle = Particle.SMOKE.builder()
                    .location(loc)
                    .offset(0.5, 0.5, 0.5)
                    .count(3)
                    .extra(0.03)
                    .receivers(32, true);


                flameParticle.spawn();
                smokeParticle.spawn();
            }

            if(ticksLeft % 20 == 0){
                victim.damage(1.0, attacker);
            }
            return;
        }

        stopMonobehav();
    }
}