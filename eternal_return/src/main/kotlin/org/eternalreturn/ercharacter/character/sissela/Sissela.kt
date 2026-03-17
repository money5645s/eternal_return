package org.eternalreturn.ercharacter.character.sissela

import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine

class Sissela(engine : EREngine, player : Player) : ERPlayer(player, engine) {

    init {
        this.ActiveCooldownSeconds = 8
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(HealthMonitor());
        registerMonobehaviour(DamageToAll());

    }

    var burserkMode: Int = -1;

    override val name: String
        get() = "sissela"

}