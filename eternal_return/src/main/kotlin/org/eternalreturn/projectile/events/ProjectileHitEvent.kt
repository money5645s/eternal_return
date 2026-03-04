package org.eternalreturn.projectile.events

import org.eternalreturn.erentity.EREntity
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class ProjectileHitEvent(val closestTarget: EREntity) : MonobehaviourEvent {
}