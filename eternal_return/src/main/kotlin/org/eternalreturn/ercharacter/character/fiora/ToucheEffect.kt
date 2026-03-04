package org.eternalreturn.ercharacter.character.fiora

import org.eternalreturn.ercharacter.character.fiora.event.ToucheEffectStartEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity

class ToucheEffect : EREntityMonobehaviour<ToucheEffectStartEvent>(){

    lateinit var loc: Location;
    var startDurationMillis : Long = 0;
    var durationTicks : Long = 0;

    override fun start(event: ToucheEffectStartEvent) {
        loc = event.location;
        startDurationMillis = System.currentTimeMillis();
        durationTicks = event.durationTicks;
    }


    var count = 0;
    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        if(isNotEnd(startDurationMillis, durationTicks)){
            val event : ToucheEffectStartEvent? = eventMap[ToucheEffectStartEvent::class.java] as ToucheEffectStartEvent;
            if(event != null){
                count = event.count;
                println("$count of point");
            }
            drawParticles(loc, count);
            return;
        }
        stopMonobehav();
    }

    private fun drawParticles(loc: Location, count: Int) {
        val dist = 0.7
        val points = arrayOf(
            loc.clone().add(dist, 0.1, 0.0),
            loc.clone().add(-dist, 0.1, 0.0),
            loc.clone().add(0.0, 0.1, dist),
            loc.clone().add(0.0, 0.1, -dist)
        )
        for (i in 0 until count) {
            loc.world!!.spawnParticle(Particle.GLOW, points[i], 1, 0.0, 0.1, 0.0, 0.0)
        }
    }
}