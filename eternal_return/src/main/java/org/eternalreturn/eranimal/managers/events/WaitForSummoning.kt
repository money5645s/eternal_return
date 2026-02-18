package org.eternalreturn.eranimal.managers.events

import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class WaitForSummoning(val listReference: MutableList<ERAJEntity>) : MonobehaviourEvent {
}