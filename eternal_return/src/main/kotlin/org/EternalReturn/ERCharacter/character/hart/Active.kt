package org.EternalReturn.ERCharacter.character.hart

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.event.CharacterSwapHandEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000
    private var isActive = false
    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()
        if (isActive) return

        player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 100, 0, false, false))

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