package org.eternalreturn.ercharacter.character.jan

import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.jan.event.JanActiveTimerEvent
import org.eternalreturn.ercharacter.character.jan.event.JanPassiveTimerEvent
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.compareTo
import kotlin.io.normalize
import kotlin.text.set

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()
        val jan = actor as Character_Jan
        val engine = org.eternalreturn.system.PluginInstance.getEREngine()

        val cd = jan.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        if (jan.Stack >= 5){
            jan.StackSkill = true
            jan.Stack = 0
        }

// 1. 방향 및 중심점 계산
        val dir = player.location.direction.setY(0).normalize() // 수평 방향 벡터
// 플레이어 발 위치에서 전방으로 1.0칸 이동한 지점이 2x2 사각형의 중심
        val centerLoc = player.location.clone().add(dir.multiply(1.0)).add(0.0, 0.1, 0.0)

        val dustOptions = Particle.DustOptions(Color.fromRGB(63, 255, 15), 1.0f)

        // world.spawnParticle(파티클, 위치, 개수, x오프셋, y오프셋, z오프셋, 속도, 데이터)
        centerLoc.world?.spawnParticle(
            Particle.DUST,
            centerLoc,
            100,
            0.5,
            0.0,
            0.5,
            0.0,
            dustOptions
        )

        if(!jan.StackSkill) {
            player.playSound(player.location, Sound.ENTITY_PLAYER_BIG_FALL, 1f, 0.6f)
            player.playSound(player.location, Sound.BLOCK_LANTERN_PLACE, 1f, 1f)
        }
        if(jan.StackSkill) {
            player.playSound(player.location, Sound.ENTITY_PLAYER_BIG_FALL, 1f, 1f)
            player.playSound(player.location, Sound.BLOCK_LANTERN_BREAK, 1f, 1f)
        }

// 2. 주변 엔티티 감지 (중심점에서 사방 1.0칸 = 가로 2칸, 세로 2칸 범위)
// getNearbyEntities(x, y, z)는 중심으로부터의 '반지름' 개념이므로 1.0을 넣어야 2x2가 됩니다.
        for (entity in centerLoc.world?.getNearbyEntities(centerLoc, 1.0, 1.0, 1.0)!!) {
            if (entity is org.bukkit.entity.LivingEntity && entity != player) {

                // 3. EREntity 여부 확인
                val victim = engine.getEREntity(entity)
                if (victim == null) continue


                jan.Stack ++
                if (jan.StackSkill && jan.SkillReuse == 0) jan.Stack --

                this.getEREntity().submitEvent(JanPassiveTimerEvent())
                player.sendMessage("현재 스택 ${jan.Stack}/5")

                // 4. 공격 적용
                entity.noDamageTicks = 0
                entity.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 20, 1, false, true))
                if(jan.SkillReuse == 1){
                    victim.submitEvent(EREntityStunEvent(1 * 20)) //2초
                }
            }
        }

        if (!jan.StackSkill){
            jan.cooldown.set("Active", jan.ActiveCooldownSeconds)
        }

        if (jan.StackSkill){
            if (jan.SkillReuse == 0) {
                player.sendMessage("스킬")
                this.getEREntity().submitEvent(JanActiveTimerEvent())
                jan.SkillReuse++
            } else if (jan.SkillReuse == 1) {
                player.sendMessage("재사용")
                jan.SkillReuse = 0
                jan.StackSkill = false
                jan.cooldown.set("Active", jan.ActiveCooldownSeconds)
            }
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}