package org.EternalReturn.ERCharacter.event

class CharacterStunEvent(var duration: Long) : CharacterEvent {
    var startStunMillies: Long

    init {
        this.startStunMillies = System.currentTimeMillis()
    }
}
