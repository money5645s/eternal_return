package org.eternalreturn.erentity.globalmonobehav

import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity

class InfinityHealing : EREntityMonobehaviour<EREntityDamagedEvent>() {
    public override fun start(event: EREntityDamagedEvent) {
        val entity = this.entity
        if (entity is LivingEntity) {
            entity.setHealth(20.0)
        }
    }

    public override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
    }
}
