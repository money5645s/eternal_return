package org.eternalreturn.ercharacter.event

// CharacterEvent를 상속받아 시스템 규격을 맞춥니다.
class CooldownEvent(
    val skillName: String,
    val durationSeconds: Long
) : CharacterEvent