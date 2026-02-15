package org.EternalReturn.EREntity.GlobalMonobehav

import org.EternalReturn.EREntity.EREntity
import org.EternalReturn.EREntity.EREntityMonobehaviour
import org.EternalReturn.EREntity.Event.EREntityUpdateColliderEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.EternalReturn.util.dpengine.geometry.Collider
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