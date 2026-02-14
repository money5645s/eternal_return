package org.EternalReturn.ERCharacter.character.fiora

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer


class Character_Fiora(player: ERPlayer) : ERCharacter(player) {
    init {
        this.ActiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "fiora"
}