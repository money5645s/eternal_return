package org.eternalreturn.ercharacter.character.yuki

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000

    override fun start(event: CharacterSwapHandEvent) {
        val yuki = actor as Character_Yuki

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }

        if (yuki.isActiveSkill) return

        this.skillActiveTick = System.currentTimeMillis()
        yuki.isActiveSkill = true

        player.sendMessage("§f[유키] §b머리치기 준비!")
        player.playSound(player.location, Sound.BLOCK_SNOW_BREAK, 1f, 1.5f)
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val yuki = actor as Character_Yuki

        if (!yuki.isActiveSkill) {
            stopMonobehav()
            return
        }

        val currentTime = System.currentTimeMillis()

        if (currentTime - skillActiveTick >= durationMillis || !yuki.isActiveSkill) {
            yuki.isActiveSkill = false

            // 쿨타임 등록
            erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel[erCharacter.activeLevel];
            stopMonobehav()
        }
    }
}