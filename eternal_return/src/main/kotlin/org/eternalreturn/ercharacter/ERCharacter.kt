package org.eternalreturn.ercharacter

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.globalmonobehav.ParabolicFly
import org.eternalreturn.ercharacter.globalmonobehav.PlayerRayCastingByLeftClicking
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine

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
        registerMonobehaviour(ParabolicFly())
        registerMonobehaviour(PlayerRayCastingByLeftClicking())
    }

    abstract val name: String

}
