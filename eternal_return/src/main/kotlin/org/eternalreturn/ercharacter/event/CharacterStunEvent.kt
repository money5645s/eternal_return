package org.eternalreturn.ercharacter.event

class CharacterStunEvent(var duration: Long) : CharacterEvent {
    var startStunMillies: Long

    init {
        this.startStunMillies = System.currentTimeMillis()
    }
}
