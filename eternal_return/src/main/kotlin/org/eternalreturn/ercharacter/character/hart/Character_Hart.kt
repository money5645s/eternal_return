package org.eternalreturn.ercharacter.character.hart

import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Hart(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var stack: Int = 0
    override val activeCoolForEachLevel: LongArray = longArrayOf(15 * 20, 14 * 20, 13 * 20, 13 * 20, 12 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(20 * 20, 18 * 20, 16 * 20, 14 * 20, 10 * 20)

    val passiveExtraDamageForEachLevel = doubleArrayOf(5.0 ,7.0 ,9.0, 11.0 , 15.0)
    //val activeExtraDamageForEachLevel = doubleArrayOf(5.0 ,7.0 ,9.0, 11.0 , 15.0)

    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "hart"
}