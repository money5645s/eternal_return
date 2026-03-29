package org.eternalreturn.ercharacter.character.hart

import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine
import org.dpengine.monobehaviour.MonobehaviourEvent

class Character_Hart(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {
    var stack: Int = 0
    override val activeCoolForEachLevel  = CoolTableSeconds(this::activeLevel, 15, 14, 13, 13, 12)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::passiveLevel, 20, 18, 16, 14, 10)

    val passiveDamage = DamageTable(this::passiveLevel, 5.0, 7.0, 9.0, 11.0, 15.0)

    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 8
        registerMonobehaviour(Active(CooldownContext(activeCoolForEachLevel)))
        registerMonobehaviour(Passive(CooldownContext(passiveCoolForEachLevel)))
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

class PassiveTimerEvent(val damage : Double, val player: DPlayer) : EREntityEvent
class Passive(cooldownCtx : CooldownContext) : ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Hart>(cooldownCtx, durationTicks = 0, "PCD") {

    override fun skillStart(event: EREntityAttackEvent) {
        event.victim.submitEvent(PassiveTimerEvent(player.passiveDamage.get(), player))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {}
}