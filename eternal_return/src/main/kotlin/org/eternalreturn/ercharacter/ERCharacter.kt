package org.eternalreturn.ercharacter

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.globalmonobehav.PlayerRayCastingByLeftClicking
import org.eternalreturn.ercharacter.globalmonobehav.PlayerRayCastingMeleeAttack
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourModule
import org.eternalreturn.util.dpengine.geometry.Collider
import org.eternalreturn.util.dpengine.geometry.GeometryModule

abstract class ERCharacter(
    val player : Player,
    collider: Collider,
    dpEngine: DPEngine
) : EREntity(dpEngine, player, collider) {

    @JvmField
    var ActiveCooldownSeconds: Long = 0
    @JvmField
    var PassiveCooldownSeconds: Long = 0
    var cooldown: SkillCooldown = SkillCooldown()

    init {
        registerMonobehaviour(PlayerRayCastingByLeftClicking())
        registerMonobehaviour(PlayerRayCastingMeleeAttack())
    }

    abstract val name: String

}
