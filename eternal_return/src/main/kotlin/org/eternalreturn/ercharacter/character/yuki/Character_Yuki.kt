package org.eternalreturn.ercharacter.character.yuki


import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.yuki.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Yuki(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var buttonCount: Int = 4
    var isReloading: Boolean = false
    var isActiveSkill: Boolean = false
    var stunnedVictims: MutableMap<LivingEntity?, Location?> = HashMap<LivingEntity?, Location?>()

    init {
        this.ActiveCooldownSeconds = 3
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active())
        registerMonobehaviour(Passive())
    }


    override val name: String
        get() = "yuki"
}