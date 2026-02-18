package org.eternalreturn.ercharacter

import org.eternalreturn.ercharacter.event.CharacterEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.erplayer.ERPlayerListener

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




}