package org.eternalreturn.projectile.events

import RayHitInfo
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class ProjectileRayCastEvent(val shooter : MonobehaviourActor, val hitList : ArrayList<RayHitInfo>) : MonobehaviourEvent {
}