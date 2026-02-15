package org.eternalreturn.erentity

import org.eternalreturn.system.PluginInstance
import org.bukkit.entity.Entity
import org.eternalreturn.util.dpengine.geometry.Collider

class ERDummy(
    entity: Entity,
    collider: Collider
) : EREntity(
    entity,
    collider
) {
    init {
        //(entity as LivingEntity).setAI(false)
        //registerMonobehaviour(new InfinityHealing());
    }
}
