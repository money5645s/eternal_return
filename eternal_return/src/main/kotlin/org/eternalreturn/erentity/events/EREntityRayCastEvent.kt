package org.eternalreturn.erentity.events

import org.eternalreturn.erentity.EREntity

class EREntityRayCastEvent(val shooter : EREntity, val hitList : ArrayList<EREntity>) : EREntityEvent{
}