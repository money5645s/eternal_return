package org.eternalreturn.ercharacter.globalmonobehav

import org.bukkit.Bukkit
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterRunTimerEvent

class ERCharacterUpdate : ERCharacterMonobehaviour<CharacterRunTimerEvent>() {

    val activeLevelObj = Bukkit.getScoreboardManager().mainScoreboard.getObjective("active_level")!!;
    val passiveLevelObj = Bukkit.getScoreboardManager().mainScoreboard.getObjective("passive_level")!!;

    override fun start(event: CharacterRunTimerEvent) {}

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val player = erPlayer
        val bukkitPlayer = player.player;

        //액티브 레벨 업데이트
        val newActiveLevel = activeLevelObj.getScoreFor(bukkitPlayer).score;
        if(player.activeLevel != newActiveLevel){
            player.activeLevel = newActiveLevel
        }

        //패시브 레벨 업데이트
        val newPassiveLevel = passiveLevelObj.getScoreFor(bukkitPlayer).score;
        if(player.passiveLevel != newPassiveLevel){
            player.passiveLevel = newPassiveLevel
        }

    }

}