package org.eternalreturn.projectile.events

import RayHitInfo
import org.dpengine.monobehaviour.MonobehaviourActor
import org.dpengine.monobehaviour.MonobehaviourEvent

class ProjectileRayCastEvent(val shooter : MonobehaviourActor, val hitList : ArrayList<RayHitInfo>) :
    MonobehaviourEvent {
}