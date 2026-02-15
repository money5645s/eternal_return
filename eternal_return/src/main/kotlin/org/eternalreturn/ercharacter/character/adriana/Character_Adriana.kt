package org.eternalreturn.ercharacter.character.adriana

import org.eternalreturn.ercharacter.character.adriana.monobehavs.BurningGround
import org.eternalreturn.ercharacter.character.adriana.monobehavs.LitFireOnAttack
import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer

class Character_Adriana(player: Player) : ERPlayer(player) {
    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 5
        this.registerMonobehaviour(BurningGround())
        this.registerMonobehaviour(LitFireOnAttack())
    }

    override val name: String
        get() = "fiora"
}
