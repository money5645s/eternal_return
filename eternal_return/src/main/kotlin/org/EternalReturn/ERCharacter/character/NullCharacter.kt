package org.EternalReturn.ERCharacter.character

import org.EternalReturn.ERCharacter.ERCharacter
import org.EternalReturn.ERPlayer.ERPlayer

class NullCharacter(erPlayer: ERPlayer) : ERCharacter(erPlayer) {
    override val name: String
        get() = "NullCharacter."
}

