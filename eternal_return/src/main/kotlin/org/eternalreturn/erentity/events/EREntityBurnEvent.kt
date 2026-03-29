package org.eternalreturn.erentity.events

import org.eternalreturn.erentity.DPEntity

class EREntityBurnEvent(val attacker : DPEntity, val ticks : Long) : EREntityEvent {
}