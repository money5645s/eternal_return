package org.eternalreturn.eranimal.animals.actors

import org.bukkit.Location
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.system.EREngine

class Boar(engine: EREngine, erAjEntity: ERAJEntity, location: Location) :
    ERAnimal(engine, erAjEntity, 1.0, 1.0, 2.0, 0.0, 0.0, 0.0) {
    init {
        this.aJEntity.registerAnimation("ready", 2.0)
        this.aJEntity.registerAnimation("attack", 2.3)
        this.aJEntity.registerAnimation("move", 3.5)
        this.aJEntity.registerAnimation("skill", 3.25)
        this.aJEntity.registerAnimation("death", 2.0)
    }
}