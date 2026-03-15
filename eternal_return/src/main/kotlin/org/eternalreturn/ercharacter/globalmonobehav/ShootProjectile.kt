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
        val projectile = ERProjectile(erEngine,
            pos.x(), pos.y() + 1.5, pos.z(),
            dir.x(), dir.y(), dir.z(),
            player);
        stopMonobehav();
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }

}