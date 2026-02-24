package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.ERCharacter
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine

class Character_Hyunwoo(engine : EREngine,player: Player) : ERPlayer(player, engine) {
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