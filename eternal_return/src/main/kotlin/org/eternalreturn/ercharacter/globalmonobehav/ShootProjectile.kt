package org.eternalreturn.ercharacter.globalmonobehav

import org.eternalreturn.ercharacter.event.CharacterShootProjectileEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.projectile.ERProjectile
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class ShootProjectile : EREntityMonobehaviour<CharacterShootProjectileEvent>() {

    override fun start(event: CharacterShootProjectileEvent) {
        val player = actor as ERPlayer;
        val pos = player.getPosition();
        val dir = player.getDirection() * event.force.toDouble() * 40.0;
        val projectile = ERProjectile(eREngine,
            x(pos), y(pos) + 1.5, z(pos),
            x(dir), y(dir), z(dir),
            player);
        stopMonobehav();
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }

}