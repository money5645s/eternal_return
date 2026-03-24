package org.eternalreturn.ercharacter.character.isaac

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.isaac.Active
import org.eternalreturn.ercharacter.character.isaac.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Isaac(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var isActiveSkill: Boolean = false
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 60, 60, 60, 60, 60)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::passiveLevel,0, 0, 0, 0, 0)

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "isaac"
}