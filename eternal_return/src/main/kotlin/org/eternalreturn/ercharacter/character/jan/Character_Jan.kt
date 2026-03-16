package org.eternalreturn.ercharacter.character.jan

import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Jan(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var Stack: Int = 0
    var StackSkill: Boolean = false
    var SkillReuse: Int = 0

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