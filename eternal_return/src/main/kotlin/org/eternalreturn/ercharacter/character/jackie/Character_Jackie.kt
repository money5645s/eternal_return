package org.eternalreturn.ercharacter.character.jackie

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.jackie.Active
import org.eternalreturn.ercharacter.character.jackie.Attack
import org.eternalreturn.ercharacter.character.jackie.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Jackie(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var isBloodSweep: Boolean = false
    override val activeCoolForEachLevel: LongArray = longArrayOf(40 * 20, 37 * 20, 34 * 20, 31 * 20, 25 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(0, 0, 0, 0, 0)

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
