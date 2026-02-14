package org.EternalReturn.ERCharacter

import org.EternalReturn.ERCharacter.globalmonobehav.PlayerRayCastingByLeftClicking
import org.EternalReturn.ERCharacter.globalmonobehav.PlayerRayCastingMeleeAttack
import org.EternalReturn.EREntity.EREntity
import org.EternalReturn.ERPlayer.ERPlayer
import org.EternalReturn.System.PluginInstance
import org.bukkit.entity.Player

abstract class ERCharacter(erPlayer: ERPlayer) : EREntity(
    PluginInstance.getEREngine().createOrientedBox(erPlayer.getPlayer().getLocation(), 1.0 / 2, 3.0 / 2, 1.0 / 2)
) {
    var eRPlayer: ERPlayer
        protected set

    @JvmField
    var ActiveCooldownSeconds: Long = 0
    @JvmField
    var PassiveCooldownSeconds: Long = 0
    var cooldown: SkillCooldown = SkillCooldown()

    init {
        this.entity = erPlayer.getPlayer()
        this.eRPlayer = erPlayer

        registerMonobehaviour(PlayerRayCastingByLeftClicking())
        registerMonobehaviour(PlayerRayCastingMeleeAttack())
    }

    abstract val name: String

    val player: Player
        get() = this.entity as Player
}
