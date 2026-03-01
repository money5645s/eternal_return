package org.eternalreturn.ercharacter.character.hart

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.hart.Active
import org.eternalreturn.ercharacter.character.hart.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Hart(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "hart"
}