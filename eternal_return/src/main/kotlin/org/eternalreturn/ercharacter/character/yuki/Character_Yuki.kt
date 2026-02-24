package org.eternalreturn.ercharacter.character.yuki

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine


class Character_Yuki(engine : EREngine,player: Player) : ERPlayer(player, engine) {
    var buttonCount: Int = 4
    var isReloading: Boolean = false
    var isActiveSkill: Boolean = false

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active())
        registerMonobehaviour(Passive())
    }


    override val name: String
        get() = "yuki"
}