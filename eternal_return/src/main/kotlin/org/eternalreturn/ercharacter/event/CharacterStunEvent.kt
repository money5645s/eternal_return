package org.eternalreturn.ercharacter.event

class CharacterStunEvent(var duration: Long) : CharacterEvent {
    var startStunMillies: Long = System.currentTimeMillis()
}
