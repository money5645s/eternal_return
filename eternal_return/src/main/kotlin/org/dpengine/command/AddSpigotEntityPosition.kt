package org.dpengine.command

import org.bukkit.entity.Entity


class AddSpigotEntityPosition(val entity : Entity, val x : Double, val y : Double, val z : Double) : Command {
    override fun run() {
        this.entity.location.x += x;
        this.entity.location.y += y;
        this.entity.location.z += z;
        this.entity.teleport(entity.location);
    }
}