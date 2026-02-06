package org.EternalReturn.ERCharacter.Character.lidailin

import org.EternalReturn.ERCharacter.Character.lidailin.event.LiDailinPassiveTimerEvent
import org.EternalReturn.EREntity.EREntityMonobehaviour
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class LiDailinPassiveTimer : EREntityMonobehaviour<LiDailinPassiveTimerEvent>(){

    var tick = 0;
    var attacker: Player? = null

    override fun start(event: LiDailinPassiveTimerEvent) {
        tick = 0
        this.attacker = event.player
    }


    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val victim : LivingEntity =  this.getEREntity().entity as LivingEntity;
        val player = attacker ?: return
        val loc = victim.location

        tick++
        if(tick == 10){
            victim.noDamageTicks = 0
            victim.damage(5.0)
            victim.world.spawnParticle(Particle.DAMAGE_INDICATOR, loc.clone().add(0.0, 1.0, 0.0), 1, 0.5, 0.5, 0.5, 0.3)
            player.playSound(player.location, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1f, 1f)
            stopMonobehav()
        }
    }
}