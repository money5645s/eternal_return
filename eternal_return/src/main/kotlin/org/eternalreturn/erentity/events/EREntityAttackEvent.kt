package org.eternalreturn.erentity.events

import org.eternalreturn.erentity.DPEntity

class EREntityAttackEvent(val attacker: DPEntity, val victim: DPEntity) : EREntityEvent;