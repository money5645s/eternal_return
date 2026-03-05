package org.eternalreturn.ercharacter

import org.eternalreturn.ercharacter.event.CharacterEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.erplayer.ERPlayerListener
import org.bukkit.Bukkit
import org.bukkit.scoreboard.DisplaySlot

abstract class ERCharacterMonobehaviour<T : CharacterEvent> : EREntityMonobehaviour<T>() {
    private lateinit var erCharacter: ERCharacter
    private lateinit var erPlayer: ERPlayer
    private lateinit var player: Player

    private fun __init() {
        if (this.actor !is ERCharacter) {
            throw ClassCastException("Actor가 ERCharacter가 아닙니다.")
        }
        erCharacter = this.actor as ERCharacter
        erPlayer = erCharacter as ERPlayer
        player = erPlayer.entity as Player
    }

    protected fun getERCharacter(): ERCharacter{
        __init();
        return erCharacter;
    }

    protected fun getERPlayer() : ERPlayer{
        __init();
        return erPlayer;
    }

    protected fun getPlayer(): Player {
        __init()
        return player
    }


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