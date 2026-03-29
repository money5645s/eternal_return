package org.eternalreturn.ercharacter.character.jackie

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine

class Character_Jackie(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {
    var isBloodSweep: Boolean = false
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 40, 37, 34, 31, 25)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 0, 0, 0, 0, 0)

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
