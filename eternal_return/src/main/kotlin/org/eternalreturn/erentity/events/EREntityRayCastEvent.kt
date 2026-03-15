package org.eternalreturn.erentity.events

import RayHitInfo
import org.eternalreturn.erentity.EREntity

class EREntityRayCastEvent(val shooter : EREntity, val hitList : ArrayList<RayHitInfo>) : EREntityEvent{
}