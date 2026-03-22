package org.eternalreturn.erentity.events

import org.eternalreturn.erentity.EREntity

class EREntityBurnEvent(val attacker : EREntity, val ticks : Long) : EREntityEvent {
}