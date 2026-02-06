package org.EternalReturn.ERCharacter.Character.lidailin

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterSwapHandEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000 // 전체 지속 시간 (10초)
    private val drinkingTime: Long = 2000    // 술 마시는 데 걸리는 시간 (2초)
    private var isActive = false
    private var isDrinking = false // [추가] 지금 술을 목구멍으로 넘기는 중인가?

    override fun start(event: CharacterSwapHandEvent) {
        startDrinking()
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val liDailin = actor as Character_LiDailin
        val player = getPlayer()
        val currentTime = System.currentTimeMillis()

        for (event in eventList) {
            if (event is CharacterSwapHandEvent) {
                startDrinking()
            }
        }

        if (isDrinking && currentTime - skillActiveTick > drinkingTime) {
            val count = (actor as Character_LiDailin).drinkCount + 1
            (actor as Character_LiDailin).drinkCount = count

            if (count == 1) {
                player.sendMessage("§c[리 다이린] §f취기 1/2")
            } else if (count >= 2) {
                player.sendMessage("§c[리 다이린] §f만취")
                liDailin.isDrunk = true
            }

            isDrinking = false

            this.skillActiveTick = currentTime
            this.isActive = true
        }

        if (isActive && !isDrinking) {
            if (currentTime - skillActiveTick > durationMillis) {
                player.sendMessage("§7[리 다이린] 맨정신")
                isActive = false
                liDailin.isDrunk = false
                (actor as Character_LiDailin).drinkCount = 0
            }
        }
    }

    private fun startDrinking() {
        val liDailin = actor as Character_LiDailin
        val player = getPlayer()
        if (liDailin.isDrunk){
            player.sendMessage("§c[리 다이린] §7만취 상태라 더 이상 술을 마실 수 없습니다!")
        }
        if (liDailin.isDrunk || isDrinking) return //

        getPlayer().sendMessage("§c[리 다이린] §7술 마시는 중...")
        getPlayer().addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 40, 3, false, false))
        getPlayer().addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 40, 100, false, false))

        this.skillActiveTick = System.currentTimeMillis()
        this.isDrinking = true
    }
}