package org.eternalreturn.ercharacter.character.jackie

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.DPEngine

class Character_Jackie(engine : DPEngine,player: Player) : ERPlayer(player, engine) {
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
