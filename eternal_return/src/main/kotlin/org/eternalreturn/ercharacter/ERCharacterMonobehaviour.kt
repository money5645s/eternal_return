package org.eternalreturn.ercharacter

import org.eternalreturn.erentity.EREntityMonobehaviour
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.character.adriana.Character_Adriana
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

abstract class ERCharacterMonobehaviour<T : EREntityEvent> : EREntityMonobehaviour<T>() {
    val erCharacter: ERCharacter
        get() = actor as ERCharacter
    val erPlayer: ERPlayer
        get() = actor as ERPlayer
    val player: Player
        get() = erPlayer.entity as Player
}

class CooldownContext(
    val cooldownListForEachLevel : LongArray,
    val getLevel : ()->Int
){
    private var cooldown : Long = -1;
    val currentCooldown : Long get() = cooldown;
    fun resetCooldown(){
        cooldown = cooldownListForEachLevel[getLevel()];
        println("currentLevel = ${getLevel()}")
    }
}


abstract class ERCharacterSkillMonobehaviour<T : EREntityEvent, E : ERCharacter>(val cooldownCtx : CooldownContext, val durationTicks : Int) : EREntityMonobehaviour<T>(){

    @Suppress("UNCHECKED_CAST")
    val player: E
        get() = actor as E

    override fun start(event: T) {
        if(cooldownCtx.currentCooldown > 0 || cooldownCtx.getLevel() == -1){
            stopMonobehav();
            return;
        }
        skillStart(event);
    }abstract fun skillStart(event : T);


    private var skillTicks = 0;
    val t : Int get() = skillTicks;
    override fun update(eventMap : Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        if(skillTicks >= durationTicks){
            stopMonobehav();
            skillTicks = 0;
            cooldownCtx.resetCooldown();
            return;
        }
        skillUpdate(eventMap);
        skillTicks++;
    }abstract fun skillUpdate(eventMap : Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>)

}

