package org.EternalReturn.ERCharacter.character.hyunwoo

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Hyunwoo(player: ERPlayer) : ERCharacter(player) {
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