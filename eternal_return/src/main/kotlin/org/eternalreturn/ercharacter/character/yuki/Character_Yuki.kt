package org.eternalreturn.ercharacter.character.yuki


import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Yuki(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var buttonCount: Int = 4
    var isReloading: Boolean = false
    var isActiveSkill: Boolean = false


    override val activeCoolForEachLevel: LongArray = longArrayOf(30 * 20, 27 * 20, 24 * 20, 21 * 20, 18 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(15 * 20, 15 * 20, 15 * 20, 15 * 20, 15 * 20)

    val passiveExtraDamageForEachLevel : DoubleArray = doubleArrayOf(1.0, 2.0 ,3.0, 4.0, 5.0)
    val activeExtraDamageForEachLevel : DoubleArray = doubleArrayOf(3.0, 4.0, 5.0, 6.0, 8.0)

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active())
        registerMonobehaviour(Passive())
    }


    override val name: String
        get() = "yuki"
}