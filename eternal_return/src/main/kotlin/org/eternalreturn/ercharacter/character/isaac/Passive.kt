package org.eternalreturn.ercharacter.character.isaac

import org.eternalreturn.ercharacter.character.isaac.event.PassiveCountEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.isaac.Character_Isaac

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

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }


}