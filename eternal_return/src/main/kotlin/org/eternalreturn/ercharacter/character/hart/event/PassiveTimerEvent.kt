package org.eternalreturn.ercharacter.character.hart.event

import org.eternalreturn.erentity.events.EREntityEvent
import org.bukkit.Location
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer

class PassiveTimerEvent(val damage : Double, val player: ERPlayer) : EREntityEvent