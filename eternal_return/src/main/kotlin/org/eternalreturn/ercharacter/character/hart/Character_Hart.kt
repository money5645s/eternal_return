package org.eternalreturn.ercharacter.character.hart

import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Hart(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var stack: Int = 0

    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "hart"
}