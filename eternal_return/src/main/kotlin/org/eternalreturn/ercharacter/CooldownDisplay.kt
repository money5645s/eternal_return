package org.eternalreturn.ercharacter

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Bukkit
import org.bukkit.scoreboard.DisplaySlot
import org.eternalreturn.ercharacter.event.CharacterEvent
import org.eternalreturn.ercharacter.event.CooldownEvent

class CooldownDisplay : ERCharacterMonobehaviour<CooldownEvent>() {

    // 1. 등록되자마자 실행되도록 설정
    override fun start(event: CooldownEvent) {
        // 여기서 메시지가 안 뜨면 등록 자체가 안 된 것임
        getPlayer().sendMessage("§e[디버깅] CooldownDisplay 활성화됨")
        runMonobehav()
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val player = getPlayer()
        val character = getERCharacter()

        // 2. 외부에서 던진 CooldownEvent가 있다면 수신하여 데이터 갱신
        val event = eventMap[CooldownEvent::class.java] as CooldownEvent;
        if(eventMap[CooldownEvent::class.java] != null){
            character.cooldown.set(event.skillName, event.durationSeconds)

        }

        // 3. 실시간 스코어보드 렌더링
        val remaining = character.cooldown.getLeftInt("Active")
        renderScoreboard(player, remaining)
    }

    private fun renderScoreboard(player: org.bukkit.entity.Player, seconds: Int) {
        val board = Bukkit.getScoreboardManager()?.mainScoreboard ?: return
        val obj = board.getObjective("CD")
            ?: board.registerNewObjective("CD", "dummy", "쿨타임")

        obj.displaySlot = DisplaySlot.SIDEBAR

        if (seconds > 0) {
            obj.getScore(player.name).score = seconds
        } else {
            board.resetScores(player.name)
        }
    }
}