package org.eternalreturn.erentity.events

import org.eternalreturn.erentity.EREntity

class EREntityAttackEvent(val attacker: EREntity, val victim: EREntity) : EREntityEvent;