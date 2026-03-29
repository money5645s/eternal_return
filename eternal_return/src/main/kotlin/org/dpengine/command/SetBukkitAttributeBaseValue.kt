package org.dpengine.command
import org.bukkit.attribute.AttributeInstance

class SetBukkitAttributeBaseValue(val attributeInstance: AttributeInstance, val value : Double) : Command {
    override fun run() {
        attributeInstance.baseValue = value;
    }
}