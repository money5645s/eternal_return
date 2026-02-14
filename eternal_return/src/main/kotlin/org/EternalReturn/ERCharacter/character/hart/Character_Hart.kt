package org.EternalReturn.ERCharacter.character.hart

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Hart(player: ERPlayer) : ERCharacter(player) {
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "hart"
}