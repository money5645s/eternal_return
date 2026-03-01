package org.eternalreturn.ercharacter.character.adriana

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.adriana.monobehavs.Active
import org.eternalreturn.ercharacter.character.adriana.monobehavs.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Adriana(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 5
        this.registerMonobehaviour(Active())
        this.registerMonobehaviour(Passive())
    }

    override val name: String
        get() = "adriana"
}
