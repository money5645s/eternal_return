package org.EternalReturn.ERCharacter.character.yuki

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer
import org.bukkit.Location
import org.bukkit.entity.LivingEntity


class Character_Yuki(player: ERPlayer) : ERCharacter(player) {
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