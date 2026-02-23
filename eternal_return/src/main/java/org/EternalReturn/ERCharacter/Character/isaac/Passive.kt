package org.EternalReturn.ERCharacter.Character.isaac

import org.EternalReturn.ERCharacter.Character.isaac.event.PassiveCountEvent
import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterAttackEvent
import org.EternalReturn.EREntity.Event.EREntityStunEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    private var punchTimeMillis: Long = 0

    override fun start(event: CharacterAttackEvent) {
        val isaac = actor as Character_Isaac
        val player = getPlayer()

        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }

        punchTimeMillis = System.currentTimeMillis() + 10 * 50

        if (isaac.isActiveSkill) {
            player.sendMessage("§f[아이작] 경화 발동")
            victimEntity.noDamageTicks = 0
            victimEntity.damage(5.0)
            // 쿨타임 등록
            isaac.cooldown.set("Active", isaac.ActiveCooldownSeconds)
            isaac.isActiveSkill = false
        }

        event.victim.submitEvent(PassiveCountEvent(this.getEREntity().entity as Player))
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav();
    }


}