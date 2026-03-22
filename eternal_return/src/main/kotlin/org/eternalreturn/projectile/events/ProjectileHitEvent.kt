package org.eternalreturn.projectile.events

import org.eternalreturn.erentity.EREntity
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class ProjectileHitEvent(val closestTarget: EREntity) : MonobehaviourEvent {
}