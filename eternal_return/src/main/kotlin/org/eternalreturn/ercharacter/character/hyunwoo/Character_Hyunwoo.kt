package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer

class Character_Hyunwoo(player: Player) : ERPlayer(player) {
    // 플레이어별 공격 횟수를 저장하는 맵
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(WallSlamDash())
        registerMonobehaviour(StackingPunch())
    }


    override val name: String
        get() = "hyunwoo"
}