package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.DPEngine

class Character_Hart(engine : DPEngine,player: Player) : ERPlayer(player, engine) {
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "hart"
}