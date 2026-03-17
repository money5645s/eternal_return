package org.eternalreturn.util.dpengine.command

import org.bukkit.entity.Entity
import org.bukkit.util.Vector


class AddSpigotEntityVelocity(val entity : Entity, val x : Double, val y : Double, val z : Double) : Command {
    override fun run() {
        val vx0 = this.entity.velocity.x;
        val vy0 = this.entity.velocity.y;
        val vz0 = this.entity.velocity.z;
        this.entity.velocity = Vector(vx0 + x, vy0 + y, vz0 + z);
    }
}