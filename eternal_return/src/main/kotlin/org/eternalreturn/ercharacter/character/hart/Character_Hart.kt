package org.eternalreturn.ercharacter.character.hart

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Character_Hart(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    var stack: Int = 0
    override val activeCoolForEachLevel: LongArray = longArrayOf(15 * 20, 14 * 20, 13 * 20, 13 * 20, 12 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(20 * 20, 18 * 20, 16 * 20, 14 * 20, 10 * 20)

    val passiveExtraDamageForEachLevel = doubleArrayOf(5.0 ,7.0 ,9.0, 11.0 , 15.0)
    //val activeExtraDamageForEachLevel = doubleArrayOf(5.0 ,7.0 ,9.0, 11.0 , 15.0)

    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active(CooldownContext(activeCoolForEachLevel, this::activeLevel)))
        registerMonobehaviour(Passive(CooldownContext(passiveCoolForEachLevel, this::passiveLevel)))
    }

    override val name: String
        get() = "hart"
}

class Active(cooldownCtx : CooldownContext) : ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Hart>(cooldownCtx, durationTicks = 100, "ACB") {

    override fun skillStart(event: CharacterSwapHandEvent) {
        dash();
        count--;
    }

    var count : Int = 2
    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(t == 100 || !gotSubscribedEvent || count == 0) return;

        dash();
        count--;

    }

    fun dash(){
        val velocity = player.getDirection() * 2.0;
        velocity.y(0.0)
        player.setVelocity(velocity);
    }

}

class PassiveTimerEvent(val damage : Double, val player: ERPlayer) : EREntityEvent
class Passive(cooldownCtx : CooldownContext) : ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Hart>(cooldownCtx, durationTicks = 0, "PCD") {

    override fun skillStart(event: EREntityAttackEvent) {
        event.victim.submitEvent(PassiveTimerEvent(player.passiveExtraDamageForEachLevel[player.passiveLevel], player))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {}
}