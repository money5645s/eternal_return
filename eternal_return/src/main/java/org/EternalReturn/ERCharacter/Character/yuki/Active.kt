package org.EternalReturn.ERCharacter.Character.yuki

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterSwapHandEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000

    override fun start(event: CharacterSwapHandEvent) {
        val yuki = actor as Character_Yuki
        val cd = yuki.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        if (yuki.isActiveSkill) return

        this.skillActiveTick = System.currentTimeMillis()
        yuki.isActiveSkill = true

        getPlayer().sendMessage("§f[유키] §b머리치기 준비!")
        getPlayer().playSound(getPlayer().location, Sound.BLOCK_SNOW_BREAK, 1f, 1.5f)
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val yuki = actor as Character_Yuki

        if (!yuki.isActiveSkill) {
            stopMonobehav()
            return
        }

        val currentTime = System.currentTimeMillis()

        if (currentTime - skillActiveTick >= durationMillis || !yuki.isActiveSkill) {
            yuki.isActiveSkill = false

            // 쿨타임 등록
            yuki.cooldown.set("Active", yuki.ActiveCooldownSeconds)

            getPlayer().sendMessage("§7[유키] 스킬 상태가 종료되었습니다.")
            stopMonobehav()
        }
    }
}