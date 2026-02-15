package org.EternalReturn.ERCharacter

import org.EternalReturn.ERCharacter.globalmonobehav.PlayerRayCastingByLeftClicking
import org.EternalReturn.ERCharacter.globalmonobehav.PlayerRayCastingMeleeAttack
import org.EternalReturn.EREntity.EREntity
import org.EternalReturn.ERPlayer.ERPlayer
import org.EternalReturn.System.PluginInstance
import org.bukkit.entity.Player

abstract class ERCharacter(player : Player, erPlayer: ERPlayer) : EREntity(
    PluginInstance.getEREngine().createOrientedBox(erPlayer.player.location, 1.0 / 2, 3.0 / 2, 1.0 / 2),
    player
) {
    var eRPlayer: ERPlayer
        protected set

    @JvmField
    var ActiveCooldownSeconds: Long = 0
    @JvmField
    var PassiveCooldownSeconds: Long = 0
    var cooldown: SkillCooldown = SkillCooldown()

    init {
        this.entity = player
        this.eRPlayer = erPlayer

        registerMonobehaviour(PlayerRayCastingByLeftClicking())
        registerMonobehaviour(PlayerRayCastingMeleeAttack())
    }

    abstract val name: String

    val player: Player
        get() = this.entity as Player
}
