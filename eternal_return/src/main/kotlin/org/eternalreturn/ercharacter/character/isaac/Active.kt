package org.eternalreturn.ercharacter.character.isaac

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.eternalreturn.ercharacter.character.isaac.Character_Isaac

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000 // 5초 유지

    override fun start(event: CharacterSwapHandEvent) {
        val isaac = actor as Character_Isaac
        val cd = isaac.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        if (isaac.isActiveSkill) return

        this.skillActiveTick = System.currentTimeMillis()
        isaac.isActiveSkill = true;

        getPlayer().sendMessage("§f[아이작] §b경화: §f다음 공격에 추가 데미지를 부여합니다!")
        getPlayer().playSound(getPlayer().location, Sound.BLOCK_SNOW_BREAK, 1f, 1.5f)
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val isaac = actor as Character_Isaac

        if (!isaac.isActiveSkill) {
            stopMonobehav()
            return
        }

        val currentTime = System.currentTimeMillis()

        // 5초가 지났거나 공격 성공으로 스킬이 이미 소모되었다면 비활성화
        if (currentTime - skillActiveTick > durationMillis || !isaac.isActiveSkill) {
            isaac.isActiveSkill = false

            // 쿨타임 등록
            isaac.cooldown.set("Active", isaac.ActiveCooldownSeconds)

            getPlayer().sendMessage("§7[아이작] 스킬 상태가 종료되었습니다.")
            stopMonobehav()
        }
    }
}
