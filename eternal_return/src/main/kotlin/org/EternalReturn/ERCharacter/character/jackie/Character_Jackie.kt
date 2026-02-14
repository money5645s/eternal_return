package org.EternalReturn.ERCharacter.character.jackie

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Jackie(erPlayer: ERPlayer) : ERCharacter(erPlayer) {
    var isBloodSweep: Boolean = false

    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
        registerMonobehaviour(Attack())
    }

    override val name: String
        get() = "Jackie"
}
