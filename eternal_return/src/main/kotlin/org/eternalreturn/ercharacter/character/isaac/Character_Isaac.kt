package org.eternalreturn.ercharacter.character.isaac

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine

class Character_Isaac(engine : EREngine,player: Player) : ERPlayer(player, engine) {
    var isActiveSkill: Boolean = false

    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "isaac"
}