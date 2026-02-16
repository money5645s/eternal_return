package org.eternalreturn.erentity

import org.eternalreturn.system.PluginInstance
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.geometry.Collider

class ERDummy(engine : DPEngine, entity: Entity, collider: Collider) : EREntity(engine, entity, collider) {
    init {
        //(entity as LivingEntity).setAI(false)
        //registerMonobehaviour(new InfinityHealing());
    }
}
