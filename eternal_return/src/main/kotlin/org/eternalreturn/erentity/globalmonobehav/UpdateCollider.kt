package org.eternalreturn.erentity.globalmonobehav

import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityUpdateColliderEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Collider
import org.bukkit.Location

class UpdateCollider : EREntityMonobehaviour<EREntityUpdateColliderEvent>() {
    override fun start(event: EREntityUpdateColliderEvent) {

    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val loc: Location = entity.location
        val collider: Collider = (actor as EREntity).collider
        collider.setPosition(loc.x, loc.y, loc.z)
        collider.setDirection(0.0, loc.pitch.toDouble(), 0.0)
    }
}