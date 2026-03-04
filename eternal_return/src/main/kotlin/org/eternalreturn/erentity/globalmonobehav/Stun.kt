package org.eternalreturn.erentity.globalmonobehav

import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle

class Stun : EREntityMonobehaviour<EREntityStunEvent>() {
    private var startStunMillies: Long = 0
    private var duration: Long = 0
    private var stunLocation: Location? = null

    public override fun start(event: EREntityStunEvent) {
        startStunMillies = event.startStunMillies
        duration = event.duration
        stunLocation = entity.location
        println("Stun event dispatched")
    }

    public override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        if (isNotEnd(startStunMillies, duration)) {
            entity.teleport(stunLocation!!)
            stunLocation!!.getWorld()!!.spawnParticle(
                Particle.ELECTRIC_SPARK,
                stunLocation!!.clone().add(0.0, 1.0, 0.0),
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