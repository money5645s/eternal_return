package org.eternalreturn.ercharacter.character.fiora

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer


class Character_Fiora(player: Player) : ERPlayer(player) {
    init {
        this.ActiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "fiora"
}