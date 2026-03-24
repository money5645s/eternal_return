package org.eternalreturn.ercharacter.character.rio

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.character.sissela.DamageToAll
import org.eternalreturn.ercharacter.character.sissela.HealthMonitor
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.event.CharacterLeftClickEvent
import org.eternalreturn.erentity.EREntityTimerMonobehaviour
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class Rio(engine : EREngine, player : Player) : ERPlayer(player, engine) {

    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 25, 24, 23, 22, 20)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::activeLevel, 10, 10, 10, 10, 10)

    val activeExtraDamageForEachLevel : DoubleArray = doubleArrayOf(4.0 ,6.0 ,8.0, 10.0, 12.0)

    init {
        this.ActiveCooldownSeconds = 60
        this.PassiveCooldownSeconds = 8

    }

    var burserkMode: Int = -1;

    override val name: String
        get() = "sissela"

}

