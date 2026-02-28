package org.EternalReturn.ERCharacter.Character.hyunwoo

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterAttackEvent
import org.EternalReturn.ERCharacter.Event.CharacterStunEvent
import org.EternalReturn.ERCharacter.Event.CharacterSwapHandEvent
import org.EternalReturn.EREntity.EREntity
import org.EternalReturn.EREntity.Event.EREntityStunEvent
import org.EternalReturn.System.EREngine
import org.EternalReturn.System.SystemManager
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    var skillActiveTick: Long = 0
    var direction: Vector? = null
    var hitEntities: HashMap<EREntity, Int?>? = null
    private var isWallSlam = false
    private var isOnGround = false
    private var tick = 0

    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()
        val hyunwoo = actor as Character_Hyunwoo
        val cd = hyunwoo.cooldown

        isOnGround = false

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        if (!player.isOnGround() || player.getLocation().add(0.0, -0.5, 0.0).getBlock().getType().isAir()) {
            player.sendMessage("§c[현우] §f지상에서만 사용할 수 있습니다.")
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f)
            isOnGround = true
            return
        }

        this.hitEntities = HashMap<EREntity, Int?>()
        // 시선과 무관하게 수평 방향 벡터로 고정 (y=0)
        this.direction = player.getLocation().getDirection().setY(0).normalize().multiply(1)
        this.skillActiveTick = System.currentTimeMillis()
        this.isWallSlam = false
        tick = 0
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val player = getPlayer()
        val engine = dpEngine as EREngine // 엔진 캐스팅
        val hyunwoo = actor as Character_Hyunwoo
        val cd = hyunwoo.cooldown

        if (isOnGround) {
            stopMonobehav()
            return
        }

        if (cd.isWaiting("Active")) {
            stopMonobehav()
            return
        }

        tick ++

        if (!isWallSlam && tick < 7){
            val player = getPlayer()

            player.sendMessage("§c[디버깅] §f${tick}")

            // 1. 돌진 물리 적용
            val curVelocity = player.getVelocity()
            direction!!.setY(curVelocity.getY())
            player.setVelocity(direction!!)

            // 2. 주변 적 감지
            for (entity in player.getNearbyEntities(0.8, 0.8, 0.8)) {
                if (entity is LivingEntity && entity != player) {

                    val targetER = engine.getEREntity(entity)

                    if (targetER != null) {
                        hitEntities!!.putIfAbsent(targetER, 0)
                    }
                }
            }

            for (victim in hitEntities!!.keys) {
                val bukkitVictim = victim.entity as LivingEntity
                if (hitEntities!!.get(victim) == 0) {
                    hitEntities!!.put(victim, 1)

                    getERCharacter().submitEvent(
                        CharacterAttackEvent(
                            getERPlayer().getCharacter(),
                            victim
                        )
                    )
                    damage(getPlayer(), bukkitVictim, 2.0)
                }
                // 적을 플레이어 속도에 맞춰 밀어냄
                bukkitVictim.setVelocity(direction!!.clone().multiply(1.2))
            }

            // 4. [중요] 수정된 레이캐스팅 벽꿍 판정
            // 시작점: 플레이어 발 위치(getLocation)에서 배 높이(0.8) 정도 위
            val startRay = player.getLocation().add(0.0, 0.8, 0.0)
            val rayDir = direction!!.clone().normalize()

            // 1.2칸 앞의 블록 탐색 (거리가 너무 짧으면 인식이 안 됨)
            val result = player.getWorld().rayTraceBlocks(startRay, rayDir, 1.2)

            if (result != null && result.getHitBlock() != null) {
                isWallSlam = true
                handleWallSlamSuccess(player)
            }
        }

        if (!isNotEnd(skillActiveTick, 6)){
            player.sendMessage("§c[디버깅] §f돌진 종료")
            // 쿨타임 등록
            hyunwoo.cooldown.set("Active", hyunwoo.ActiveCooldownSeconds)
            stopMonobehav()
        }

        if (isWallSlam){
            player.sendMessage("§c[디버깅] §f돌진 종료")
            // 쿨타임 등록
            hyunwoo.cooldown.set("Active", hyunwoo.ActiveCooldownSeconds)
            stopMonobehav()
        }
    }

    private fun handleWallSlamSuccess(player: Player) {
        for (victim in hitEntities!!.keys) {
            val bukkitVictim = victim.entity as? LivingEntity ?: continue

            // 도그파이트 패시브를 위한 공격 이벤트 제출 (안되는 것 같음)
            getERCharacter().submitEvent(
                CharacterAttackEvent(
                    getERCharacter(),
                    victim
                )
            )

            // 벽꿍 추가 피해 (10.0)
            damage(player, bukkitVictim, 10.0)

            victim.submitEvent(EREntityStunEvent(1 * 20)) // 40틱 = 2초

            player.sendMessage("§b[현우] §f벽꿍 성공!")
            player.playSound(player.location, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1f, 1f)

        }
    }
}