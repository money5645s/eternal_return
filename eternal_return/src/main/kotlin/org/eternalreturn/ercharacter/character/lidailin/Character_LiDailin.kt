package org.eternalreturn.ercharacter.character.lidailin

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine

class Character_LiDailin(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {
    var isDrunk: Boolean = false
    var drinkCount: Int = 0
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 25, 24, 23, 22, 20)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 10, 10, 10, 10, 10)

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
        registerMonobehaviour(DrunkTimer());
    }


    override val name: String
        get() = "lidailin"
}