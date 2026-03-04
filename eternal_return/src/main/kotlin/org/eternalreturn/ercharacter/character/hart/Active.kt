package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.character.hart.Character_Hart

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000
    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()

        val hart = actor as Character_Hart
        val cd = hart.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }


        player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 100, 0, false, false))

        this.skillActiveTick = System.currentTimeMillis()
        player.sendMessage("§c[하트] §f스킬 발동! 5초간 유지됩니다.")
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        val hart = actor as Character_Hart
        val cd = hart.cooldown

        if (cd.isWaiting("Active")) {
            stopMonobehav()
            return
        }

        val currentTime = System.currentTimeMillis()

        // 시작한 시간으로부터 5초가 지났는지 확인
        if (currentTime - skillActiveTick > durationMillis) {
            getPlayer().sendMessage("§7[하트] 스킬 상태가 종료되었습니다.")
            // 쿨타임 등록
            hart.cooldown.set("Active", hart.ActiveCooldownSeconds)
            stopMonobehav()

        }
    }
}