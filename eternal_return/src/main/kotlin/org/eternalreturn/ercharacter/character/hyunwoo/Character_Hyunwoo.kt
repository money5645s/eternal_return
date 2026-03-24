package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.character.hyunwoo.Active
import org.eternalreturn.ercharacter.character.hyunwoo.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Character_Hyunwoo(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    // 플레이어별 공격 횟수를 저장하는 맵
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 25, 23, 21, 19, 15, 0)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::passiveLevel, 0, 0, 0, 0, 0, 0)

    val activeWallslamDamage = DamageTable(this::activeLevel, 0.0, 3.0, 4.0, 5.0, 6.0, 8.0, 100.0);
    val passiveHealing       = DamageTable(this::passiveLevel,0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 100.0);
    //val activeExtraDamageForEachLevel    = DamageTable(this::passiveLevel,0.0, 7.0, 8.0, 9.0, 10.0, 11.0, 100.0);

    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active(CooldownContext(activeCoolForEachLevel)))
        registerMonobehaviour(Passive(CooldownContext(activeCoolForEachLevel)))
    }


    override val name: String
        get() = "hyunwoo"
}

