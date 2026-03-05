package org.eternalreturn.erplayer

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.Gui.InventoryGui.View.IController

open class ERPlayer(p: Player, engine: EREngine) : ERCharacter(p, engine) {

    fun sendMessage(str: String) {
        entity.sendMessage(str)
    }

    override val name: String
        get() = "NullPlayer"
}
