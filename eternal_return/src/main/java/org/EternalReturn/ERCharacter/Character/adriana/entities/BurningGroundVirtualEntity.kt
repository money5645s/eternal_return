package org.EternalReturn.ERCharacter.Character.adriana.entities

import org.EternalReturn.ERCharacter.Character.adriana.monobehavs.BurningGroundMonobehav
import org.EternalReturn.EREntity.EREntity
import org.EternalReturn.util.dpengine.DPEngine
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourActor
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourModule
import org.EternalReturn.util.dpengine.geometry.Collider
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class BurningGroundVirtualEntity(
    val dpEngine: DPEngine,
    val caster: Player,
    val location: Location,
    val startTime: Long
) : MonobehaviourActor() {

    override fun update() {
    }

    init {
        this.monobehaviourModule = dpEngine.monobehaviourModule;
        registerMonobehaviour(BurningGroundMonobehav());
    }
}