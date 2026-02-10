package org.EternalReturn.ERCharacter.Character.adriana

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Adriana(player: ERPlayer) : ERCharacter(player) {
    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 5
        this.registerMonobehaviour(BurningGround())
        this.registerMonobehaviour(LitFireOnAttack())
    }

    override fun getName(): String {
        return "adriana"
    }
}
