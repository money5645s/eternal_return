package org.eternalreturn.ercharacter

import org.eternalreturn.ercharacter.event.CharacterEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.bukkit.Bukkit
import org.bukkit.scoreboard.DisplaySlot
import org.eternalreturn.erentity.events.EREntityEvent

abstract class ERCharacterMonobehaviour<T : EREntityEvent> : EREntityMonobehaviour<T>() {
    val erCharacter: ERCharacter
        get() = actor as ERCharacter
    val erPlayer: ERPlayer
        get() = actor as ERPlayer
    val player: Player
        get() = erPlayer.entity as Player


    fun getMonobehavActorList() : MutableList<MonobehaviourActor>{
        return dpEngine.monobehaviourModule.getMonobehavActors();
    }

    fun syncCooldownToScoreboard(player: org.bukkit.entity.Player, skillName: String, seconds: Int) {
        // 1. 서버 메인 스코어보드 가져오기
        val board = Bukkit.getScoreboardManager()?.mainScoreboard ?: return

        // 2. "Cooldown" 목적(Objective) 찾기 (없으면 생성)
        val objective = board.getObjective("Cooldown")
            ?: board.registerNewObjective("Cooldown", "dummy", "§6쿨타임").apply {
                displaySlot = DisplaySlot.SIDEBAR // 화면 오른쪽에 표시
            }

        // 3. 점수 업데이트
        if (seconds > 0) {
            // 플레이어 이름 옆에 남은 초(Int)를 점수로 기록
            objective.getScore(player.name).score = seconds
        } else {
            // 0초가 되면 스코어보드에서 해당 플레이어 삭제
            board.resetScores(player.name)
        }
    }




}