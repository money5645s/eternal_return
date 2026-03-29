package org.eternalreturn.ercharacter.character.jackie

import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.dpengine.monobehaviour.MonobehaviourEvent

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000 // 5초 (5000ms)
    private var isActive = false

    override fun start(event: CharacterSwapHandEvent) {
        val jackie = actor as Character_Jackie

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }

        // 스킬이 이미 켜져 있다면 중복 발동 방지 (선택 사항)
        if (isActive) return

        // 현재 시간을 기록하고 활성화 상태로 변경
        this.skillActiveTick = System.currentTimeMillis()
        this.isActive = true
        (actor as Character_Jackie).isBloodSweep = true;

        //player.sendMessage("§c[재키] §f스킬 발동! 5초간 유지됩니다.")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        // 스킬이 활성화된 상태에서만 시간 체크
        val jackie = actor as Character_Jackie
        if (!jackie.isBloodSweep) {
            stopMonobehav()
            return
        }
        jackie.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 2, 1, false, true))
        val currentTime = System.currentTimeMillis()

        // 시작한 시간으로부터 5초가 지났는지 확인
        if (currentTime - skillActiveTick > durationMillis) {
            isActive = false // 상태 종료
            (actor as Character_Jackie).isBloodSweep = false
            // 쿨타임 등록
            erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel.get();
            stopMonobehav()
        }
    }
}