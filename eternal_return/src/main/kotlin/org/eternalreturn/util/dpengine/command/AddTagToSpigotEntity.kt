package org.eternalreturn.util.dpengine.command

import org.bukkit.entity.Entity

class AddTagToSpigotEntity(val entity : Entity, val tag : String) : Command {
    override fun run() {
        entity.scoreboardTags.add(tag);
    }
}