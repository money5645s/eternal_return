package org.eternalreturn.ercharacter.character.rio

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.character.sissela.DamageToAll
import org.eternalreturn.ercharacter.character.sissela.HealthMonitor
import org.eternalreturn.ercharacter.event.CharacterLeftClickEvent
import org.eternalreturn.erentity.EREntityTimerMonobehaviour
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class Rio(engine : EREngine, player : Player) : ERPlayer(player, engine) {

    override val activeCoolForEachLevel: LongArray = longArrayOf(60 * 20, 55 * 20, 50 * 20, 45 * 20, 40 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(0, 0, 0, 0, 0)

    val activeExtraDamageForEachLevel : DoubleArray = doubleArrayOf(4.0 ,6.0 ,8.0, 10.0, 12.0)

    init {
        this.ActiveCooldownSeconds = 60
        this.PassiveCooldownSeconds = 8

    }

    var burserkMode: Int = -1;

    override val name: String
        get() = "sissela"

}

