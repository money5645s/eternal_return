package org.eternalreturn.ercharacter.character.lidailin

import org.eternalreturn.ercharacter.character.lidailin.event.DrunkTimerEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {

    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 2000


    override fun start(event: CharacterSwapHandEvent) {
        val liDailin = actor as Character_LiDailin

        val cd = liDailin.cooldown

        if(liDailin.isDrunk){
            player.sendMessage("§c[리 다이린] §7만취 상태에선 술을 마실 수 없습니다!")
            stopMonobehav();
            return
        }

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }

        player.sendMessage("§c[리 다이린] §7술 마시는 중...")
        player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 40, 3, false, false))
        player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 40, 100, false, false))

        this.skillActiveTick = System.currentTimeMillis()
        this.getEREntity().submitEvent(DrunkTimerEvent())
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val liDailin = actor as Character_LiDailin
        val currentTime = System.currentTimeMillis()

        // 시작한 시간으로부터 2초가 지났는지 확인
        if (currentTime - skillActiveTick > durationMillis) {
            liDailin.player.sendMessage("§7[리 다이린] 술 다 마심.")

            stack()

            // 쿨타임 등록
            erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel[erCharacter.activeLevel];
            stopMonobehav()
        }
    }

    private fun stack() {
        val liDailin = actor as Character_LiDailin
        val count = liDailin.drinkCount + 1
        liDailin.drinkCount = count

        if (count == 1) {
            liDailin.player.sendMessage("§c[리 다이린] §f취기 1/2")
        }
        if (count >= 2) {
            liDailin.player.sendMessage("§c[리 다이린] §f만취")
            liDailin.isDrunk = true
            liDailin.drinkCount = 0
        }
    }

}