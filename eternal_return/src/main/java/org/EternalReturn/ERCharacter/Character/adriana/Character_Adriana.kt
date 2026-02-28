package org.EternalReturn.ERCharacter.Character.adriana

import org.EternalReturn.ERCharacter.Character.adriana.monobehavs.Active
import org.EternalReturn.ERCharacter.Character.adriana.monobehavs.Passive
import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Adriana(player: ERPlayer) : ERCharacter(player) {
    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 5
        this.registerMonobehaviour(Active())
        this.registerMonobehaviour(Passive())
    }

    override fun getName(): String {
        return "adriana"
    }
}
