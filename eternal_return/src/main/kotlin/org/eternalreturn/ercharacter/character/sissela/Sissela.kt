package org.eternalreturn.ercharacter.character.sissela

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Sissela(engine : EREngine, player : Player) : ERPlayer(player, engine) {


    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 60, 55, 50, 45, 40)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 0, 0, 0, 0, 0)

    val activeExtraDamage = DamageTable(this::activeLevel, 4.0 ,6.0 ,8.0, 10.0, 12.0)

    init {
        this.ActiveCooldownSeconds = 60
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(HealthMonitor());
        registerMonobehaviour(DamageToAll());

    }

    var burserkMode: Int = -1;

    override val name: String
        get() = "sissela"

}