package org.eternalreturn.ercharacter.character.lidailin

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer

class Character_LiDailin(player: Player) : ERPlayer(player) {
    var isDrunk: Boolean = false
    var drinkCount: Int = 0

    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "lidailin"
}