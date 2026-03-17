package org.eternalreturn.util.dpengine.command

import org.bukkit.entity.Entity
import org.bukkit.util.Vector


class SetSpigotEntityVelocity(val entity : Entity, val x : Double, val y : Double, val z : Double) : Command{

    override fun run() {
        this.entity.velocity = Vector(x, y, z)
    }
}