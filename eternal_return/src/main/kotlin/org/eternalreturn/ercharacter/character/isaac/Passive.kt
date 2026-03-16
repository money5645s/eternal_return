package org.eternalreturn.ercharacter.character.isaac

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.isaac.event.PassiveCountEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: EREntityAttackEvent) {
        val isaac = actor as Character_Isaac

        val victim = event.victim
        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        if (isaac.isActiveSkill) {
            player.sendMessage("§f[아이작] 경화 발동")
            victim.damageForce(5.0, isaac)
            // 쿨타임 등록
            isaac.cooldown.set("Active", isaac.ActiveCooldownSeconds)
            isaac.isActiveSkill = false
        }

        event.victim.submitEvent(PassiveCountEvent(this.erPlayer))
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}