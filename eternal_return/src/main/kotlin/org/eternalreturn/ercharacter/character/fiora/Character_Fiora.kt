package org.eternalreturn.ercharacter.character.fiora

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.fiora.Active
import org.eternalreturn.ercharacter.character.fiora.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine


class Character_Fiora(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var isActiveSkill: Boolean = false

    init {
        this.ActiveCooldownSeconds = 3
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "fiora"
}