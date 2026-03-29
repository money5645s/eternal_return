package org.eternalreturn.projectile.events

import org.dpengine.monobehaviour.MonobehaviourEvent
import org.eternalreturn.erentity.DPEntity

class ProjectileHitEvent(val closestTarget: DPEntity) : MonobehaviourEvent {
}