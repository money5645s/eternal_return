package org.eternalreturn.ercharacter.character.jan

import org.bukkit.entity.LivingEntity
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    override fun start(event: CharacterSwapHandEvent) {
        val engine = org.eternalreturn.system.PluginInstance.getEREngine()

        // 1. 방향 및 중심점 계산
        val dir = player.location.direction.setY(0).normalize() // 수평 방향 벡터
        // 플레이어 발 위치에서 전방으로 1.0칸 이동한 지점이 2x2 사각형의 중심
        val centerLoc = player.location.clone().add(dir.multiply(1.0))

        // 2. 주변 엔티티 감지 (중심점에서 사방 1.0칸 = 가로 2칸, 세로 2칸 범위)
        // getNearbyEntities(x, y, z)는 중심으로부터의 '반지름' 개념이므로 1.0을 넣어야 2x2가 됩니다.
        for (entity in centerLoc.world.getNearbyEntities(centerLoc, 1.0, 1.0, 1.0)) {
            if (entity is org.bukkit.entity.LivingEntity && entity != player) {

                // 3. [피오라 스타일] EREntity 여부 확인
                val victim = engine.getEREntity(entity)
                if (victim == null) continue

                // 4. 공격 적용
                entity.noDamageTicks = 0
                entity.damage(10.0, player)
            }
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
            stopMonobehav()
        }
    }