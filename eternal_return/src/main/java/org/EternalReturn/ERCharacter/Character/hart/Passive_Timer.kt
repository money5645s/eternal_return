package org.EternalReturn.ERCharacter.Character.hart

import org.EternalReturn.ERCharacter.Character.hart.event.PassiveTimerEvent
import org.EternalReturn.EREntity.EREntityMonobehaviour
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Passive_Timer : EREntityMonobehaviour<PassiveTimerEvent>(){

    var tick = 0;
    var attacker: Player? = null

    override fun start(event: PassiveTimerEvent) {
        tick = 0
        this.attacker = event.player
        event.player.playSound(event.player.location, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1f, 1.34f)
    }


    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val victim : LivingEntity =  this.getEREntity().entity as LivingEntity;
        val player = attacker ?: return
        val loc = victim.location

        tick++
        if(tick == 10){
            victim.noDamageTicks = 0
            victim.damage(5.0)
            victim.world.spawnParticle(Particle.NOTE, loc.clone().add(0.0, 1.0, 0.0), 1, 0.5, 0.5, 0.5, 1.0)
            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1f, 1.68f)

        }
        if(tick == 20){
            victim.noDamageTicks = 0
            victim.damage(5.0)
            victim.world.spawnParticle(Particle.NOTE, loc.clone().add(0.0, 1.0, 0.0), 1, 0.5, 0.5, 0.5, 1.0)
            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1f, 2f)
            stopMonobehav()
        }
    }
}