package org.eternalreturn.ercharacter.character.nathapon

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine

class Character_Nathapon(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {
    // 플레이어별 공격 횟수를 저장하는 맵
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 25, 24, 23, 22, 20)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 10, 10, 10, 10, 10)
    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Passive())
        registerMonobehaviour(Active())
    }


    override val name: String
        get() = "nathapon"
}