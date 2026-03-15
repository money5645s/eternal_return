package org.eternalreturn.erplayer

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.Gui.InventoryGui.View.IController

open class ERPlayer(p: Entity, engine: EREngine) : ERCharacter(p, engine) {

    var attackCooldown : Float = 0.0F;
    val player : Player
        get() {
            return entity as Player
        }

    fun sendMessage(str: String) {
        entity.sendMessage(str)
    }

    override fun shootRay(){
        super.shootRay();
        attackCooldown = player.attackCooldown;
    }

    //fun playSound()

    override val name: String
        get() = "NullPlayer"
}
