package org.EternalReturn.ERCharacter.event

import org.EternalReturn.EREntity.EREntity

class CharacterAttackEvent(var attacker: EREntity, @JvmField var victim: EREntity) : CharacterEvent
