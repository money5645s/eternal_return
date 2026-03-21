package org.eternalreturn.ercharacter.character.hyunwoo

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erplayer.ERPlayer
import java.util.*
import kotlin.math.min

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private val hitCountMap = HashMap<EREntity, Int>()

    override fun start(event: EREntityAttackEvent) {

        val victim = event.victim;
        val attacker = event.attacker as ERPlayer;
        val bukkitAttacker = attacker.player;
        val hyunwoo = erPlayer as Character_Hyunwoo

        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            stopMonobehav();
            return;
        }

        erCharacter.passiveCooldown = erCharacter.passiveCoolForEachLevel[erCharacter.passiveLevel];

        if(!hitCountMap.contains(event.victim)){
            hitCountMap[victim] = 0;
        }

        // 현재 공격 횟수를 가져오고 1을 더함
        val count = hitCountMap[victim]!! + 1;

        if (count >= 5) {
            // 5회 타격 시 체력 3 회복 (2가 하트 1칸이므로 3은 하트 1.5칸)
            val health = bukkitAttacker.health + hyunwoo.passiveHealingForEachLevel[hyunwoo.passiveLevel];
            // 최대 체력을 넘지 않도록 설정
            val maxHealth = bukkitAttacker.getAttribute(Attribute.MAX_HEALTH)!!.value
            bukkitAttacker.health = min(health, maxHealth)

            bukkitAttacker.playSound(bukkitAttacker.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f)

            // 카운트 초기화
            hitCountMap[victim] = 0;
        } else {
            hitCountMap[victim] = count;
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}