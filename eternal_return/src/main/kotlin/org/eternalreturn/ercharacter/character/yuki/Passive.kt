package org.eternalreturn.ercharacter.character.yuki

import org.bukkit.Sound
import org.bukkit.damage.DamageType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class Passive : ERCharacterMonobehaviour<EREntityAttackEvent>() {
    private var reloadStartTime: Long = 0
    private val reloadDuration: Long = 2000 // 2초
    private var punchTimeMillis: Long = 0

    var nextAttackTick : Long = 0;

    override fun start(event: EREntityAttackEvent) {
        // 자바 클래스인 Character_Yuki로 형변환
        val yuki = actor as Character_Yuki
        val victim = event.victim

        val currentTime = System.currentTimeMillis();
        if(currentTime < nextAttackTick || erCharacter.passiveLevel == -1){
            stopMonobehav();
            return;
        }
        nextAttackTick = currentTime + erCharacter.passiveCoolForEachLevel.get() * 50;



        // 1. 재봉 중이면 공격 취소
        if (yuki.isReloading) {
            player.sendMessage("§f[유키] §7옷매무새 중에는 공격할 수 없습니다.")
            return
        }

        // 2. 단추 소모 및 강화
        if (yuki.buttonCount > 0) {
            yuki.buttonCount--

            punchTimeMillis = System.currentTimeMillis() + 500
            victim.damageForce(yuki.passiveExtraDamageForEachLevel.get(), yuki, DamageType.PLAYER_ATTACK)

            player.sendMessage("§f[유키] §b완벽한 옷매무새: §f남은 단추 (${yuki.buttonCount}/4)")
            player.playSound(player.location, Sound.ENTITY_ITEM_BREAK, 1f, 1.8f)

            if (yuki.isActiveSkill) {
                player.sendMessage("§f[유키] 머리!")
                victim.damageForce(yuki.activeExtraDamageForEachLevel.get(), yuki, DamageType.PLAYER_ATTACK)
                event.victim.submitEvent(EREntityStunEvent(1 * 20))
                erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel.get();
                yuki.isActiveSkill = false
            }

            // 3. 단추가 없으면 재봉 모드 돌입
            if (yuki.buttonCount == 0) {
                yuki.isReloading = true
                reloadStartTime = System.currentTimeMillis()
                player.sendMessage("§f[유키] §e옷매무새 정리 중...")
                player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 40, 100, false, false))
                player.playSound(player.location, Sound.BLOCK_IRON_HIT, 1f, 1.2f)
            }
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val yuki = actor as Character_Yuki
        // 4. 재봉 시간 체크
        if (yuki.isReloading) {
            if (System.currentTimeMillis() - reloadStartTime >= reloadDuration) {
                yuki.isReloading = false
                yuki.buttonCount = 4
                player.sendMessage("§f[유키] §a재봉 완료! §f단추가 다시 채워졌습니다.")
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1.5f)
            }
            return;
        }
        stopMonobehav();
    }
}