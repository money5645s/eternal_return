package org.eternalreturn.ercharacter.event

import org.eternalreturn.erentity.EREntity

class CharacterAttackEvent(val victim: EREntity) : CharacterEvent
