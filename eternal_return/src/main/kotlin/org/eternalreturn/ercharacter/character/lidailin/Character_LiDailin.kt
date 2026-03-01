package org.eternalreturn.ercharacter.character.lidailin

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.lidailin.Active
import org.eternalreturn.ercharacter.character.lidailin.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_LiDailin(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var isDrunk: Boolean = false
    var drinkCount: Int = 0

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "lidailin"
}