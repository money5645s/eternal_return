package org.eternalreturn.ercharacter.event

import org.eternalreturn.erentity.EREntity

class CharacterAttackEvent(var attacker: EREntity, @JvmField var victim: EREntity) : CharacterEvent
