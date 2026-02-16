package org.eternalreturn.ercharacter.character.adriana.entities

import org.eternalreturn.ercharacter.character.adriana.monobehavs.BurningGroundMonobehav
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.bukkit.Location
import org.bukkit.entity.Player

class BurningGroundVirtualEntity(
    dpEngine: DPEngine,
    val caster: Player,
    val location: Location,
    val startTime: Long
) : MonobehaviourActor(dpEngine) {


    init {
        registerMonobehaviour(BurningGroundMonobehav());
    }

}