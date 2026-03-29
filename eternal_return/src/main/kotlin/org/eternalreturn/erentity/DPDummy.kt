package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.eternalreturn.system.EREngine

class DPDummy(engine : EREngine, entity: Entity) : DPHitboxEntity(engine, entity, 0.5, 1.0, 0.5, 0.0, 1.0, 0.0) {
    init {
        //(entity as LivingEntity).setAI(false)
        //registerMonobehaviour(new InfinityHealing());
    }
}
