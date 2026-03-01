package org.eternalreturn.ercharacter.character.nathapon

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.nathapon.Active
import org.eternalreturn.ercharacter.character.nathapon.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Nathapon(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    // 플레이어별 공격 횟수를 저장하는 맵
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "nathapon"
}