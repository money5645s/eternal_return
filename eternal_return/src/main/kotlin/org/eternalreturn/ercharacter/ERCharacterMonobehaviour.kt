package org.eternalreturn.ercharacter

import org.eternalreturn.erentity.EREntityMonobehaviour
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.erentity.events.EREntityEvent

abstract class ERCharacterMonobehaviour<T : EREntityEvent> : EREntityMonobehaviour<T>() {
    val erCharacter: ERCharacter
        get() = actor as ERCharacter
    val erPlayer: ERPlayer
        get() = actor as ERPlayer
    val player: Player
        get() = erPlayer.entity as Player
}