package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erplayer.DPlayer
import org.dpengine.monobehaviour.MonobehaviourEvent
import kotlin.math.min

class Passive(
    cooldownContext : CooldownContext
) : ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Hyunwoo>(cooldownContext, 0, "PCD") {
    private val hitCountMap = HashMap<DPEntity, Int>()

    override fun skillStart(event: EREntityAttackEvent) {

        val victim = event.victim;
        val attacker = event.attacker as DPlayer;
        val bukkitAttacker = attacker.player;
        val hyunwoo = player;

        if(!hitCountMap.contains(event.victim)){
            hitCountMap[victim] = 0;
        }

        // 현재 공격 횟수를 가져오고 1을 더함
        val count = hitCountMap[victim]!! + 1;

        if (count >= 5) {
            // 5회 타격 시 체력 3 회복 (2가 하트 1칸이므로 3은 하트 1.5칸)
            val health = bukkitAttacker.health + hyunwoo.passiveHealing.get();
            // 최대 체력을 넘지 않도록 설정
            val maxHealth = bukkitAttacker.getAttribute(Attribute.MAX_HEALTH)!!.value;
            bukkitAttacker.health = min(health, maxHealth);

            bukkitAttacker.playSound(bukkitAttacker.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f);

            // 카운트 초기화
            hitCountMap[victim] = 0;
        } else {
            hitCountMap[victim] = count;
        }
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}