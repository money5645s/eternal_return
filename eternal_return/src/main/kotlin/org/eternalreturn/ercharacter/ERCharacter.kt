package org.eternalreturn.ercharacter

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.globalmonobehav.ParabolicFly
import org.eternalreturn.ercharacter.globalmonobehav.PlayerRayCastingByLeftClicking
import org.eternalreturn.ercharacter.globalmonobehav.PlayerRayCastingMeleeAttack
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourModule
import org.eternalreturn.util.dpengine.geometry.Collider
import org.eternalreturn.util.dpengine.geometry.GeometryModule
import org.eternalreturn.util.dpengine.physics.Handle

abstract class ERCharacter(
    val player : Player,
    erEngine: EREngine,
) : EREntity(erEngine, player, 0.5, 1.0, 0.5, 0.0, 1.0, 0.0) {

    @JvmField
    var ActiveCooldownSeconds: Long = 0
    @JvmField
    var PassiveCooldownSeconds: Long = 0
    var cooldown: SkillCooldown = SkillCooldown()

    init {
        registerMonobehaviour(PlayerRayCastingByLeftClicking())
        registerMonobehaviour(PlayerRayCastingMeleeAttack())
        registerMonobehaviour(ParabolicFly())
    }

    abstract val name: String

}
