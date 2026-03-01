package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.hyunwoo.Active
import org.eternalreturn.ercharacter.character.hyunwoo.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Hyunwoo(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    // 플레이어별 공격 횟수를 저장하는 맵
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active())
        registerMonobehaviour(Passive())
    }


    override val name: String
        get() = "hyunwoo"
}