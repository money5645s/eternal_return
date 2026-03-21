package org.eternalreturn.ercharacter.character.fiora

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.character.fiora.Active
import org.eternalreturn.ercharacter.character.fiora.Passive
import org.eternalreturn.ercharacter.ERCharacter
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.character.fiora.event.ERToucheCountEvent
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent


class Character_Fiora(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    override val activeCoolForEachLevel: LongArray = longArrayOf(30 * 20, 27 * 20, 24 * 20, 21 * 20, 8 * 20)
    override val passiveCoolForEachLevel: LongArray = longArrayOf(0, 0, 0, 0, 0)

    val activeDamageForEachLevel : DoubleArray = doubleArrayOf(3.0, 4.0, 5.0, 6.0, 8.0);
    val toucheDamageForEachLevel : DoubleArray = doubleArrayOf(5.0, 6.0, 7.0, 8.0, 10.0);

    val activeCooldownCtx = CooldownContext(activeCoolForEachLevel, this::activeLevel);
    val passiveCooldownContext = CooldownContext(passiveCoolForEachLevel, this::passiveLevel);

    init {
        this.ActiveCooldownSeconds = 3
        registerMonobehaviour(Passive(activeCooldownCtx))
        registerMonobehaviour(Active(passiveCooldownContext))
    }

    override val name: String
        get() = "fiora"
}

class Active(activeContext : CooldownContext) : ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Fiora>(activeContext, durationTicks = 15) {

    override fun skillStart(event: CharacterSwapHandEvent) {
        player.player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 14, 2, false, false))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        // 1타 (4틱)
        if (t == 4) fioraHit(1.0F)
        // 2타 (10틱)
        if (t == 10) fioraHit(1.4F)
    }

    fun fioraHit(pitch : Float){
        val fiora = player
        player.player.playSound(player.location, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, pitch)
        player.player.world.spawnParticle(Particle.SWEEP_ATTACK, player.player.eyeLocation.add(player.location.direction.multiply(1.5)), 1)
        for(victim in erEngine.entityList){
            if(victim === fiora) continue;
            val distSqr = magnitudeSqr(victim.getPosition() - (fiora.getPosition() + fiora.getDirection() * 1.5));
            if(distSqr <= 2.5 * 2.5){
                fiora.sendMessage("${victim.javaClass.simpleName}")
                victim.damageForce(fiora.activeDamageForEachLevel[fiora.activeLevel], fiora);
                victim.submitEvent(ERToucheCountEvent(fiora, fiora.toucheDamageForEachLevel[fiora.passiveLevel]))
            }
        }
    }
}

class Passive(activeContext : CooldownContext) : ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Fiora>(activeContext, durationTicks = 0) {
    private var punchTimeMillis: Long = 0

    override fun skillStart(event: EREntityAttackEvent) {
        event.victim.submitEvent(ERToucheCountEvent(player, player.toucheDamageForEachLevel[player.passiveLevel]))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {}
}
