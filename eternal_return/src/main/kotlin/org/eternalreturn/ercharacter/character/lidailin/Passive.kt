package org.eternalreturn.ercharacter.character.lidailin

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.lidailin.event.LiDailinPassiveTimerEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: EREntityAttackEvent) {
        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }
        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        if((actor as Character_LiDailin).isDrunk){
            event.victim.submitEvent(LiDailinPassiveTimerEvent(this.getEREntity().entity as Player))
        }


    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}