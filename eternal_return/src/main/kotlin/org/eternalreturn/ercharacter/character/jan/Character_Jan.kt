package org.eternalreturn.ercharacter.character.jan

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine

class Character_Jan(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {
    var Stack: Int = 0
    var StackSkill: Boolean = false
    var SkillReuse: Int = 0
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 25, 24, 23, 22, 20)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 10, 10, 10, 10, 10)

    val forwardDamageForEachLevel : DoubleArray = doubleArrayOf(3.0, 3.0, 4.0, 4.0, 5.0);

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
        registerMonobehaviour(JanPassiveTimer());
    }


    override val name: String
        get() = "jan"
}