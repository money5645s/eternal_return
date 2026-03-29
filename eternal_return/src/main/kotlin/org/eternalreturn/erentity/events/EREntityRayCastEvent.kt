package org.eternalreturn.erentity.events

import RayHitInfo
import org.eternalreturn.erentity.DPEntity

class EREntityRayCastEvent(val shooter : DPEntity, val hitList : ArrayList<RayHitInfo>) : EREntityEvent{
}