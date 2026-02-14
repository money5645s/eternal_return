package org.EternalReturn.ERCharacter.character.nathapon

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class Character_Nathapon(player: ERPlayer) : ERCharacter(player) {
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