package org.eternalreturn.projectile.events

import RayHitInfo
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class ProjectileRayCastEvent(val shooter : MonobehaviourActor, val hitList : ArrayList<RayHitInfo>) : MonobehaviourEvent {
}