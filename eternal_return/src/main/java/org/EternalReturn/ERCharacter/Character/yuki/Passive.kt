package org.EternalReturn.ERCharacter.Character.yuki

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterAttackEvent
import org.EternalReturn.Util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Passive : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    private var reloadStartTime: Long = 0
    private val reloadDuration: Long = 2000 // 2초
    private var punchTimeMillis: Long = 0
    private val stunnedVictims: MutableMap<LivingEntity?, Location?> = HashMap<LivingEntity?, Location?>()
    private var stunTimer = 0
    private var isActiveAttack = false

    override fun start(event: CharacterAttackEvent) {
        // 자바 클래스인 Character_Yuki로 형변환
        val yuki = actor as Character_Yuki
        val player = getPlayer()

        val victimEntity = event.victim.entity

        if (victimEntity !is LivingEntity) {
            return
        }

        if (System.currentTimeMillis() < punchTimeMillis) {
            return
        }
        punchTimeMillis = System.currentTimeMillis() + 10 * 50


        // 1. 재봉 중이면 공격 취소
        if (yuki.isReloading) {
            player.sendMessage("§f[유키] §7옷매무새 중에는 공격할 수 없습니다.")
            return
        }


        // 2. 단추 소모 및 강화
        if (yuki.buttonCount > 0) {
            yuki.buttonCount--

            punchTimeMillis = System.currentTimeMillis() + 500
            victimEntity.noDamageTicks = 0
            victimEntity.damage(2.0, player)

            player.sendMessage("§f[유키] §b완벽한 옷매무새: §f남은 단추 (${yuki.buttonCount}/4)")
            player.playSound(player.location, Sound.ENTITY_ITEM_BREAK, 1f, 1.8f)

            if (yuki.isActiveSkill) {
                player.sendMessage("§f[유키] 머리!")
                stunnedVictims.clear()
                stunnedVictims[victimEntity] = victimEntity.location
                isActiveAttack = true
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

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val yuki = actor as Character_Yuki
        if (isActiveAttack && stunTimer < 40) {
            // 40틱 동안 유지
            stunTimer++
            getPlayer().sendMessage("§f[유키] 스턴")

            for (entry in stunnedVictims.entries) {
                val victim: LivingEntity = entry.key!!
                val loc: Location = entry.value!!

                if (victim.isValid()) {
                    // 위치 고정 및 파티클 생성
                    victim.teleport(loc)
                    victim.getWorld()
                        .spawnParticle(Particle.ELECTRIC_SPARK, loc.clone().add(0.0, 1.0, 0.0), 3, 0.3, 0.3, 0.3, 0.05)
                }
            }
        } else if (stunTimer >= 40) {
            // [핵심] 40틱이 지나 기절이 끝나면 상태를 초기화하여 재사용 가능하게 함
            isActiveAttack = false
            stunTimer = 0
            stunnedVictims.clear()
        }

        // 4. 재봉 시간 체크
        if (yuki.isReloading) {
            if (System.currentTimeMillis() - reloadStartTime >= reloadDuration) {
                yuki.isReloading = false
                yuki.buttonCount = 4
                getPlayer().sendMessage("§f[유키] §a재봉 완료! §f단추가 다시 채워졌습니다.")
                getPlayer().playSound(getPlayer().location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1.5f)
            }
        }
    }
}