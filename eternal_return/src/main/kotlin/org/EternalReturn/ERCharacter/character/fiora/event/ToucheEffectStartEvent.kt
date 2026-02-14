package org.EternalReturn.ERCharacter.character.fiora.event

import org.EternalReturn.EREntity.Event.EREntityEvent
import org.bukkit.Location

class ToucheEffectStartEvent(val location : Location, val durationTicks : Long, val count : Int) : EREntityEvent {

}