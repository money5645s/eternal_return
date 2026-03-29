package org.eternalreturn.erentity.globalmonobehav

import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.eternalreturn.erentity.DPEntity
import kotlin.properties.Delegates

class Stun : EREntityMonobehaviour<EREntityStunEvent>() {
    private var startStunMillies: Long = 0
    private var duration: Long = 0
    private var stunLocation: Location by Delegates.notNull()

    public override fun start(event: EREntityStunEvent) {
        startStunMillies = event.startStunMillies
        duration = event.duration
        stunLocation = entity.location
        //println("Stun event dispatched")
    }

    public override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        if (isNotEnd(startStunMillies, duration)) {
            (actor as DPEntity).setPosition(vec3(stunLocation.x, stunLocation.y, stunLocation.z))
            stunLocation.getWorld()!!.spawnParticle(
                Particle.ELECTRIC_SPARK,
                stunLocation.clone().add(0.0, 1.0, 0.0),
                3,
                0.3,
                0.3,
                0.3,
                0.05
            )
            return
        }
        stopMonobehav()
    }
}