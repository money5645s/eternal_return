package org.eternalreturn.ercharacter.character.yuki


import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Yuki(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var buttonCount: Int = 4
    var isReloading: Boolean = false
    var isActiveSkill: Boolean = false


    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel,  30, 27, 24, 21, 18, 0)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::passiveLevel, 7, 6, 5, 4, 3, 0)

    val activeCooldownContext = CooldownContext(activeCoolForEachLevel);

    val passiveExtraDamageForEachLevel   = DamageTable(this::activeLevel, 1.0, 2.0 ,3.0, 4.0, 5.0, 100.0)
    val activeExtraDamageForEachLevel    = DamageTable(this::passiveLevel,3.0, 4.0, 5.0, 6.0, 8.0, 100.0)

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active(activeCooldownContext));
        registerMonobehaviour(Passive())
    }


    override val name: String
        get() = "yuki"
}