package org.EternalReturn.ERCharacter.Character.lidailin

import org.EternalReturn.ERCharacter.Character.jackie.Character_Jackie
import org.EternalReturn.ERCharacter.Character.lidailin.event.LiDailinPassiveTimerEvent
import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterAttackEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: CharacterAttackEvent) {
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

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav()
    }
}