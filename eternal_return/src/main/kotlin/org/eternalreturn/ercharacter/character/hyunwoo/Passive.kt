package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import java.util.*
import kotlin.math.min

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    private val hitCountMap: MutableMap<UUID?, Int?> = HashMap<UUID?, Int?>()
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

        val attacker = getPlayer()

        val uuid = attacker.getUniqueId()
        // 현재 공격 횟수를 가져오고 1을 더함
        val count = hitCountMap.getOrDefault(uuid, 0)!! + 1

        if (count >= 5) {
            // 5회 타격 시 체력 3 회복 (2가 하트 1칸이므로 3은 하트 1.5칸)
            val health = attacker.getHealth() + 3
            // 최대 체력을 넘지 않도록 설정
            val maxHealth = attacker.getAttribute(Attribute.MAX_HEALTH)!!.getValue()
            attacker.setHealth(min(health, maxHealth))

            attacker.sendMessage("§b[현우] §f패시브 발동! 체력을 회복했습니다.")
            attacker.playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f)

            // 카운트 초기화
            hitCountMap.put(uuid, 0)
        } else {
            hitCountMap.put(uuid, count)
            // 진행 상황 알림 (선택 사항)
            attacker.sendMessage("§7[현우] §f도그파이트: " + count + "/5")
        }
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        stopMonobehav()
    }
}