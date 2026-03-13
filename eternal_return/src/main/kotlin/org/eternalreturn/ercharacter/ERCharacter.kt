package org.eternalreturn.ercharacter

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.globalmonobehav.ParabolicFly
import org.eternalreturn.ercharacter.globalmonobehav.ShootProjectile
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.system.EREngine

abstract class ERCharacter(
    player : Entity,
    erEngine: EREngine,
) : ERHitboxEntity(erEngine, player, 0.5, 1.0, 0.5, 0.0, 1.0, 0.0) {

    @JvmField
    var ActiveCooldownSeconds: Long = 0
    @JvmField
    var PassiveCooldownSeconds: Long = 0
    var cooldown: SkillCooldown = SkillCooldown()

    init {
        registerMonobehaviour(ParabolicFly())
        registerMonobehaviour(ShootProjectile())
    }

    abstract val name: String

}
