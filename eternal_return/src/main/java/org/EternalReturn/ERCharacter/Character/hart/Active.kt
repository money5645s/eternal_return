package org.EternalReturn.ERCharacter.Character.hart

import org.EternalReturn.ERCharacter.Character.jackie.Character_Jackie
import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterSwapHandEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000 // 5초 (5000ms)
    private var isActive = false
    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()
        // 스킬이 이미 켜져 있다면 중복 발동 방지 (선택 사항)
        if (isActive) return

        player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 100, 0, false, false))

        // 현재 시간을 기록하고 활성화 상태로 변경
        this.skillActiveTick = System.currentTimeMillis()
        this.isActive = true
        player.sendMessage("§c[하트] §f스킬 발동! 5초간 유지됩니다.")
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        if (isActive) {
            val currentTime = System.currentTimeMillis()

            // 시작한 시간으로부터 5초가 지났는지 확인
            if (currentTime - skillActiveTick > durationMillis) {
                getPlayer().sendMessage("§7[하트] 스킬 상태가 종료되었습니다.")
                isActive = false
                stopMonobehav()
            }
        }
    }
}