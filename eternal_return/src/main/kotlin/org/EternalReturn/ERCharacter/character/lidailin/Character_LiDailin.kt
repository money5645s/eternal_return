package org.EternalReturn.ERCharacter.character.lidailin

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_LiDailin(player: ERPlayer) : ERCharacter(player) {
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