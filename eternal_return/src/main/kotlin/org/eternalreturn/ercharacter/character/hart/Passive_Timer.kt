package org.eternalreturn.ercharacter.character.hart

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.properties.Delegates

/**
 * 해당 Behaviour는 EREntity에 register됨.
 * */
class Passive_Timer : EREntityMonobehaviour<PassiveTimerEvent>(){

    var tick = 0;
    var attacker: ERPlayer by Delegates.notNull()
    var attackerBukkit : Player by Delegates.notNull()
    var damage = 0.0

    override fun start(event: PassiveTimerEvent) {
        tick = 0
        damage = event.damage
        attacker = event.player
        attackerBukkit = attacker.player
        val loc = attackerBukkit.location
        attackerBukkit.playSound(loc, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1f, 1.34f)
    }


    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val victimEREntity = this.erEntity;
        val victimBukkit = victimEREntity.entity
        val player = attacker
        val loc = victimBukkit.location

        tick++
        if(tick == 10){
            victimEREntity.damageNotSendEvent(damage, attacker)
            victimBukkit.world.spawnParticle(Particle.NOTE, loc.clone().add(0.0, 1.0, 0.0), 1, 0.5, 0.5, 0.5, 1.0)
            attackerBukkit.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1f, 1.68f)

        }
    }
}