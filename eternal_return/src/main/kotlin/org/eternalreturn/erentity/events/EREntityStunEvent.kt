package org.eternalreturn.erentity.events

class EREntityStunEvent(var duration: Long) : EREntityEvent {
    val startStunMillies = System.currentTimeMillis();
}
