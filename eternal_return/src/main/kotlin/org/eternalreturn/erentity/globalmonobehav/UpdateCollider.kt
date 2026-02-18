package org.eternalreturn.erentity.globalmonobehav

import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityUpdateColliderEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Collider
import org.bukkit.Location
import org.eternalreturn.erentity.ERDummy
import org.eternalreturn.util.dpengine.geometry.OrientedBox

class UpdateCollider : EREntityMonobehaviour<EREntityUpdateColliderEvent>() {
    override fun start(event: EREntityUpdateColliderEvent) {

    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val loc: Location = entity.location
        val collider: Collider = getEREntity().collider
        collider.setPosition(loc.x, loc.y, loc.z)
        collider.setDirection(0.0, loc.yaw.toDouble(), 0.0)
    }
}