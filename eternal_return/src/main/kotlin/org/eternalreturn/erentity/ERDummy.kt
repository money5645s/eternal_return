package org.eternalreturn.erentity

import org.eternalreturn.system.PluginInstance
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.geometry.Collider

class ERDummy(engine : EREngine, entity: Entity, collider: Collider) : EREntity(engine, entity, 0.5, 1.0, 0.5, 0.0, 1.0 ,0.0) {
    init {
        //(entity as LivingEntity).setAI(false)
        //registerMonobehaviour(new InfinityHealing());
    }
}
