package org.eternalreturn.ercharacter.character.lidailin

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.damage.DamageType
import org.eternalreturn.ercharacter.character.lidailin.event.LiDailinPassiveTimerEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.properties.Delegates
import org.eternalreturn.erplayer.ERPlayer

// 만취 했을 때 평타 2번 때리는 클래스
class LiDailinPassiveTimer : EREntityMonobehaviour<LiDailinPassiveTimerEvent>(){

    var tick = 0;
    var attacker: ERPlayer by Delegates.notNull()

    override fun start(event: LiDailinPassiveTimerEvent) {
        tick = 0
        this.attacker = event.player
    }


    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val victim = erEntity
        val bukkitEntity = victim.entity;
        val world = bukkitEntity.world
        val player = attacker.player
        val loc = victim.location

        tick++
        if(tick == 10){
            victim.damageForce(5.0, attacker, DamageType.PLAYER_ATTACK)
            world.spawnParticle(Particle.DAMAGE_INDICATOR, loc.clone().add(0.0, 1.0, 0.0), 1, 0.5, 0.5, 0.5, 0.3)
            player.playSound(player.location, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1f, 1f)
            stopMonobehav()
        }
    }
}