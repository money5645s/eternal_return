package org.eternalreturn.ercharacter.character.fiora

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.damage.DamageType
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent


class Character_Fiora(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {
    override val activeCoolForEachLevel = CoolTableSeconds(this::activeLevel, 30, 27, 24, 21, 8)
    override val passiveCoolForEachLevel = CoolTableSeconds(this::passiveLevel, 0, 0, 0, 0, 0)

    val activeDamage = DamageTable(this::activeLevel, 3.0, 4.0, 5.0, 6.0, 8.0);
    val toucheDamage = DamageTable(this::passiveLevel, 5.0, 6.0, 7.0, 8.0, 10.0);

    val activeCooldownCtx = CooldownContext(activeCoolForEachLevel);
    val passiveCooldownCtx = CooldownContext(passiveCoolForEachLevel);

    init {
        this.ActiveCooldownSeconds = 3
        registerMonobehaviour(Active(activeCooldownCtx))
        registerMonobehaviour(Passive(passiveCooldownCtx))
    }

    override val name: String
        get() = "fiora"
}

class Active(activeContext : CooldownContext) :
    ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Fiora>(activeContext, durationTicks = 15, "ACD") {

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
            val distSqr = magnitudeSqr(victim.getPosition() - (fiora.getPosition() + fiora.getDirection() * 0.5));
            if(distSqr <= 2.5 * 2.5){
                fiora.sendMessage("${victim.javaClass.simpleName}")
                victim.damageForce(fiora.activeDamage.get(), fiora, DamageType.PLAYER_ATTACK);
                victim.submitEvent(ERToucheCountEvent(fiora, fiora.toucheDamage.get()))
            }
        }
    }
}

class Passive(activeContext : CooldownContext) :
    ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Fiora>(activeContext, durationTicks = 0, "PCD") {

    override fun skillStart(event: EREntityAttackEvent) {
        event.victim.submitEvent(ERToucheCountEvent(player, player.toucheDamage.get()))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {}
}
