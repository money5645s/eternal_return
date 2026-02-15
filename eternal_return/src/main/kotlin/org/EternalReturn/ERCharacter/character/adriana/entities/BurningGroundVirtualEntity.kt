package org.EternalReturn.ERCharacter.character.adriana.entities

import org.EternalReturn.ERCharacter.character.adriana.monobehavs.BurningGroundMonobehav
import org.EternalReturn.util.dpengine.DPEngine
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourActor
import org.bukkit.Location
import org.bukkit.entity.Player

class BurningGroundVirtualEntity(
    val dpEngine: DPEngine,
    val caster: Player,
    val location: Location,
    val startTime: Long
) : MonobehaviourActor() {


    init {
        this.monobehaviourModule = dpEngine.monobehaviourModule;
        registerMonobehaviour(BurningGroundMonobehav());
    }

}