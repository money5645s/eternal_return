package org.eternalreturn.ercharacter.character.yuki

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour

class Active(cooldownContext: CooldownContext) : ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Yuki>(cooldownContext, durationTicks = 5 * 20, "ACD") {
    private var skillActiveTick: Long = 0
    private val durationMillis: Long = 5000

    override fun skillStart(event: CharacterSwapHandEvent) {
        val yuki = player

        if (yuki.isActiveSkill) return

        this.skillActiveTick = System.currentTimeMillis()
        yuki.isActiveSkill = true

        player.sendMessage("§f[유키] §b머리치기 준비!")
        player.player.playSound(player.location, Sound.BLOCK_SNOW_BREAK, 1f, 1.5f)
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        if(t == 100){
            player.isActiveSkill = false;
        }
    }
}